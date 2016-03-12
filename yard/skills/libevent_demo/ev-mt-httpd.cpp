#include "wbox.h"
#include <pthread.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>

#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>

#include <event.h>
#include <evhttp.h>

#include <string.h>
#include <stdarg.h>
#include <map>
#include <string>


static const int MAX_THREAD_CNT = 256;

#define FORMAT_PRINT_BUF(fmt, buf)  \
    buf[0] = 0;                     \
    va_list vl;                     \
    va_start(vl, fmt);              \
    vsprintf(buf, fmt, vl);         \
    va_end(vl);                     \

namespace wbox {

typedef std::map<std::string, std::string>  str_map;

/**
 *
 */
class wbox_http_ctx_impl : public wbox_http_ctx
{
public:
    wbox_http_ctx_impl(struct evhttp_request* req)
        : req_(req)
    {
        resp_buf_ = evbuffer_new();
        // URI
        uri_ = evhttp_request_uri(req_);
        evh_uri_ = evhttp_request_get_evhttp_uri(req_);
        raw_query_ = evhttp_uri_get_query(evh_uri_);
        // headers
        headers_ = evhttp_request_get_input_headers(req_);
        // 解析URI的参数(即GET方法的参数)
        evhttp_parse_query_str(raw_query_, querys_);
    }
    virtual ~wbox_http_ctx_impl()
    {
        evbuffer_free(resp_buf_);
    }

    // from URI
    virtual const char* uri() const
    {
        return uri_;
    }
    virtual const char* query(const char* key) const
    {
        if (NULL == key || 0 == *key)
            return raw_query_;
        const char* value = evhttp_find_header(querys_, key);
        return value;
    }
    virtual wbox_cmd_type get_cmd_type() const
    {
        return (wbox_cmd_type)evhttp_request_get_command(req_);
    }

    // from HEAD
    virtual const char* get_hander(const char* key) const
    {
        const char* value = evhttp_find_header(headers_, key);
        return value;
    }

    // from DATA
    virtual const byte_t* get_chunk(uint32_t* sz) const
    {
        *sz = 0;
        return NULL;
    }


    // reply
    virtual int  add_header(const char* key, const char* value)
    {
        int ret = evhttp_add_header(req_->output_headers, key, value);
        return ret;
    }
    virtual int  add_printf(const char *fmt, ...)
    {
        char tmp[4 * 1024] = {0};
        FORMAT_PRINT_BUF(fmt, tmp);
        return add_data((const byte_t*)tmp, strlen(tmp));
    }
    virtual int  add_data(const byte_t* data, uint32_t sz)
    {
        int ret = evbuffer_add(resp_buf_, data, sz);
        return ret;
    }
    virtual void send_reply(int code, const char *reason)
    {
        evhttp_send_reply(req_, code, reason, resp_buf_);
    }

    // more info
    virtual const char* get_remote_host() const
    {
        return req_->remote_host;
    }
    virtual port_t get_remote_port() const
    {
        return req_->remote_port;
    }
    virtual const char* get_fragment() const
    {
        return evhttp_uri_get_fragment(evh_uri_);
    }

private:


private:
    struct evhttp_request*      req_;
    const struct evhttp_uri*    evh_uri_;   // 格式化后URI
    const  char*                uri_;
    const  char*                raw_query_; // 原始的查询串
    struct evbuffer*            resp_buf_;
    struct evkeyvalq*           headers_;
    struct evkeyvalq*           querys_;    // 查询串－健值对
};


class HTTPServer
{
public:
    HTTPServer() {}
    ~HTTPServer() {}
    int run(int port, int nthreads, wbox_http_handler_node handlers[], int handler_cnt);
protected:
    static void* Dispatch(void *arg);
    static void GenericHandler(struct evhttp_request *req, void *arg);
    void ProcessRequest(struct evhttp_request *request);
    int BindSocket(int port);
private:
    wbox_http_handler_node handlers_[WBOX_MAX_HANDLER_CNT];
    int handler_cnt_;
};

int HTTPServer::BindSocket(int port)
{
    int ret = 0;
    int nfd = socket(AF_INET, SOCK_STREAM, 0);
    do
    {
        if (nfd < 0)
        {
            ret = -1;
            perror("SOCK_STREAM");
            break;
        }

        // 端口立即可用
        int one = 1;
        ret = setsockopt(nfd, SOL_SOCKET, SO_REUSEADDR, (char *)&one, sizeof(int));

        // 绑定端口
        struct sockaddr_in addr;
        memset(&addr, 0, sizeof(addr));
        addr.sin_family = AF_INET;
        addr.sin_addr.s_addr = INADDR_ANY;
        addr.sin_port = htons(port);
        ret = bind(nfd, (struct sockaddr*)&addr, sizeof(addr));
        if (ret < 0)
        {
            perror("socket-bind");
            break;
        }

        // Listen
        ret = listen(nfd, 10240);
        if (ret < 0)
        {
            perror("socket-listen");
            break;
        }

        // 设置为非阻塞模式
        int flags = fcntl(nfd, F_GETFL, 0);
        if (flags < 0 || fcntl(nfd, F_SETFL, flags | O_NONBLOCK) < 0)
        {
            perror("socket-fcntl-nonblock");
            ret = -2;
            break;
        }
    } while (0);

    // 若socket有效但参数设置等失败，则将句柄置无效.
    if (ret != 0 && nfd > 0)
        nfd = -1;
    return nfd;
}

int HTTPServer::run(int port, int nthreads, wbox_http_handler_node handlers[], int handler_cnt)
{
    int ret = 0;
    int nfd = BindSocket(port);
    if (nfd < 0 || handler_cnt == 0)
    {
        return -1;
    }

    // copy handler;
    memcpy(handlers_, handlers, handler_cnt * sizeof(handlers[0]));

    pthread_t thrds[MAX_THREAD_CNT] = {0};
    for (int i = 0; i < nthreads; i++)
    {
        do
        {
            ret = -1;
            struct event_base* base = event_init();
            if (NULL == base)
                break;
            struct evhttp* httpd = evhttp_new(base);
            if (NULL == httpd)
                break;
            ret = evhttp_accept_socket(httpd, nfd);
            if (ret != 0)
                break;
            evhttp_set_gencb(httpd, HTTPServer::GenericHandler, this);
            // 创建工作线程
            ret = pthread_create(thrds + i, NULL, HTTPServer::Dispatch, base);
            if (ret != 0)
                break;
        } while (0);

        if (ret != 0)
        {
            // destory base and httpd
            break;
        }
    }

    // 等待线程执行结束.
    for (int i = 0; i < nthreads; i++)
    {
        pthread_join(thrds[i], NULL);
    }
    return ret;
}

void* HTTPServer::Dispatch(void *arg)
{
    event_base_dispatch((struct event_base*)arg);
    return NULL;
}

void HTTPServer::GenericHandler(struct evhttp_request *req, void *arg)
{
    ((HTTPServer*)arg)->ProcessRequest(req);
}

void HTTPServer::ProcessRequest(struct evhttp_request *req)
{
    //sleep(30); for test
    pthread_t worker_id = pthread_self();

    // 根据uri选择handler
    wbox_fn_http_handler handler = NULL;
    const char* uri = evhttp_request_uri(req);
    for (int i = 0; i < handler_cnt_; ++i)
    {
        if (strncasecmp(handlers_[i].uri, uri, strlen(handlers_[i].uri)) == 0)
        {
            handler = handlers_[i].handler;
            break;
        }
    }

    // 处理具体请求
    if (NULL == handler)
    {
        fprintf(stderr, "unknown request %s - worker-id=%ld\n", uri, worker_id);
        struct evbuffer* buf = evbuffer_new();
        evhttp_send_reply(req, HTTP_OK, "could not find content for uri", buf);
        evbuffer_free(buf);
    }
    else
    {
        wbox_http_ctx_impl ctx(req);
        //#if _DEBUG
        ctx.add_printf("Requested: %s and execute in thread[%ld]\n", evhttp_request_uri(req), worker_id);
        //#endif
        handler(&ctx);
    }
}

} // namespace wbox


int test_main(int argc, char* argv[])
{
    wbox::HTTPServer s;
    int ret = s.run(4487, 10, NULL, 0);
    return ret;
}


int wbox_run(int port, int worker_cnt, wbox_http_handler_node handlers[], int handler_cnt)
{
    wbox::HTTPServer s;
    int ret = s.run(port, worker_cnt, handlers, handler_cnt);
    return ret;
}
