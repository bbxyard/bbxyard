/*
 * =====================================================================================
 *
 *       Filename:  wbox_ctx.hpp
 *
 *    Description:  www box context for request convert in to out
 *
 *        Version:  1.0
 *        Created:  03/12/16 16:12:57
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#include "wbox.h"

#include <event.h>
#include <evhttp.h>

#include <string.h>
#include <stdarg.h>
#include <stdio.h>
#include <time.h>

#include <map>
#include <string>


namespace wbox {

#define FORMAT_PRINT_BUF(fmt, buf)  \
    buf[0] = 0;                     \
    va_list vl;                     \
    va_start(vl, fmt);              \
    vsprintf(buf, fmt, vl);         \
    va_end(vl);                     \

#define __QUOTE(x)                  # x
#define  _QUOTE(x)                  __QUOTE(x)

inline const char* get_basename(const char* file)
{
    // libgen.h not found the basename in some os, so we do itself.
    const char* psz = strrchr(file, '/');
    return (psz != NULL)? psz + 1: NULL;
}

#define DEBUG_PRINT(fmt, ...) do {                                                               \
    time_t      t  = time(NULL);                                                                 \
    struct tm * dm = localtime(&t);                                                              \
    fprintf(stdout, "[%02d:%02d:%02d] %s:[" _QUOTE(__LINE__) "][%s]: "                           \
            fmt "\n", dm->tm_hour, dm->tm_min, dm->tm_sec,                                       \
            get_basename(__FILE__), __func__, ## __VA_ARGS__);                                   \
    fflush(stdout);                                                                              \
} while (0)

typedef std::map<std::string, std::string>  str_map;

struct mem_node_t
{
    byte_t*     mem;
    uint32_t    sz;
    uint32_t    cap;
};

static const struct cmd_name_entry
{
    wbox_cmd_type   cmd;
    const char*     name;
} gsc_cmd_name_table [] =
{
    { WBOX_REQ_GET,     "GET"     },
    { WBOX_REQ_POST,    "POST"    },
    { WBOX_REQ_HEAD,    "HEAD"    },
    { WBOX_REQ_PUT,     "PUT"     },
    { WBOX_REQ_DELETE,  "DELETE"  },
    { WBOX_REQ_OPTIONS, "OPTIONS" },
    { WBOX_REQ_TRACE,   "TRACE"   },
    { WBOX_REQ_CONNECT, "CONNECT" },
    { WBOX_REQ_PATCH,   "PATCH"   },
};


/**
 * 上下文转换类
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
        evhttp_parse_query_str(raw_query_, &querys_);
        // 获得POST数据
        evbuffer* in_ev_buf = evhttp_request_get_input_buffer(req_);
        if (in_ev_buf != NULL && evbuffer_get_length(in_ev_buf) > 0)
        {
            input_data_.sz = input_data_.cap = evbuffer_get_length(in_ev_buf);
            input_data_.mem = new byte_t[input_data_.cap];
            evbuffer_copyout(in_ev_buf, input_data_.mem, input_data_.sz);
        }
        else
        {
            input_data_.sz  = input_data_.cap = 0;
            input_data_.mem = NULL;
        }
    }
    virtual ~wbox_http_ctx_impl()
    {
        evbuffer_free(resp_buf_);
        if (NULL != input_data_.mem)
        {
            delete[] input_data_.mem;
        }
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
        const char* value = evhttp_find_header(&querys_, key);
        return value;
    }
    virtual const char* get_cmd_stype() const
    {
        wbox_cmd_type cmd = get_cmd_type();
        for (int i = 0; i < sizeof(gsc_cmd_name_table)/sizeof(gsc_cmd_name_table[0]); ++i)
        {
            if (cmd == gsc_cmd_name_table[i].cmd)
                return gsc_cmd_name_table[i].name;
        }
        return NULL;
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
    virtual const byte_t* get_input_data(uint32_t* sz) const
    {
        if (sz != NULL)
            *sz = input_data_.sz;
        return input_data_.mem;
    }

    // enums
    virtual void enum_all_querys (const char* keys[], const char* values[], uint32_t* cnt) const
    {
        enum_all_from_evkeyvalq(&querys_, keys, values, cnt);
    }
    virtual void enum_all_headers(const char* keys[], const char* values[], uint32_t* cnt) const
    {
        enum_all_from_evkeyvalq(headers_, keys, values, cnt);
    }


    // reply
    virtual int  add_header(const char* key, const char* value)
    {
        int ret = evhttp_add_header(req_->output_headers, key, value);
        return ret;
    }
    virtual int  add_header_printf(const char* key, const char *fmt, ...)
    {
        char tmp[4 * 1024] = {0};
        FORMAT_PRINT_BUF(fmt, tmp);
        return add_header(key, tmp);
    }
    virtual int  add_data(const byte_t* data, uint32_t sz)
    {
        int ret = evbuffer_add(resp_buf_, data, sz);
        return ret;
    }
    virtual int  add_data_printf(const char *fmt, ...)
    {
        char tmp[4 * 1024] = {0};
        FORMAT_PRINT_BUF(fmt, tmp);
        return add_data((const byte_t*)tmp, strlen(tmp));
    }

    virtual int send_reply_with_file(const char* file)
    {
        uint32_t fsz = 0;
        const char* fname = get_basename(file);
        DEBUG_PRINT("open file %s for download\n", file);
        FILE* fp = fopen(file, "rb");
        if (NULL == fp)
        {
            send_reply(HTTP_NOTFOUND, 0);
            return HTTP_NOTFOUND;
        }
        fseek(fp, 0, SEEK_END);
        fsz = (uint32_t)ftell(fp);
        fseek(fp, 0, SEEK_SET);

        //输入文件标签
        add_header("Content-type", "application/octet-stream");
        add_header("Accept-Ranges", "bytes");
        add_header_printf("Accept-Length", "%d", fsz);
        add_header_printf("Content-Disposition", "attachment; filename=%s", fname);
        // output data
        byte_t buf[4096];
        int rd = 0;
        while ((rd = fread(buf, 1, sizeof(buf), fp)) > 0)
        {
            add_data(buf, rd);
        }
        fclose(fp);

        // sent reply data
        send_reply(200, "OK");
        return 0;
    }

    virtual void send_reply(int code, const char *reason)
    {
        if (code != HTTP_OK)
        {
            evhttp_send_error(req_, code, reason);
        }
        else
        {
            evhttp_send_reply(req_, code, reason, resp_buf_);
        }
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
    virtual void print_request(FILE* fp) const
    {
        // # print uri info
        fprintf(fp, "REQUEST TYPE [%s]\n", get_cmd_stype() );
        fprintf(fp, "===============URI-INFO===============\n");
        print_uri_info(fp, evh_uri_);

        // # print peer info
        fprintf(fp, "remote host:post = %s:%d\n", get_remote_host(), get_remote_port());

        const char* keys[WBOX_MAX_HANDLER_CNT] = {0};
        const char* values[WBOX_MAX_HANDLER_CNT] = {0};
        // # print headers
        uint32_t max_cnt = WBOX_MAX_HANDLER_CNT;
        enum_all_headers(keys, values, &max_cnt);
        fprintf(fp, "============headers(%d)===============\n", max_cnt);
        print_kvs(fp, keys, values, max_cnt);

        // # print querys
        max_cnt = WBOX_MAX_HANDLER_CNT;
        enum_all_querys(keys, values, &max_cnt);
        fprintf(fp, "============querys(%d)===============\n", max_cnt);
        print_kvs(fp, keys, values, max_cnt);

        // print input data
        fprintf(fp, "============input-data(%d)===============\n", input_data_.sz);
        if (input_data_.mem != NULL)
        {
            fwrite(input_data_.mem, sizeof(byte_t), input_data_.sz, fp);
        }
        fprintf(fp, "\n");

        // # end
        fprintf(fp, "==================END===============\n");
    }
private:
    static void enum_all_from_evkeyvalq(const evkeyvalq* kvq, const char* keys[], const char* values[], uint32_t* cnt)
    {
        int max_cnt = 1024;
        if (NULL != cnt)    // 若为空假定为无限大
            max_cnt = *cnt;

        int i = 0;
        for (const struct evkeyval* node = kvq->tqh_first; node != NULL; node = node->next.tqe_next, ++i)
        {
            if (i >= max_cnt)
                break;
            keys[i] = node->key;
            values[i] = node->value;
        }
        if (NULL != cnt)
            *cnt = i;
    }
    static void print_kvs(FILE* fp, const char* keys[], const char* values[], int max_cnt)
    {
        for (int i = 0; i < max_cnt; ++i)
        {
            fprintf(fp, "%s : %s\n", keys[i], values[i]);
        }
    }
    static void print_uri_info(FILE* fp, const struct evhttp_uri * http_uri)
    {
        fprintf(fp, "scheme:%s\n", evhttp_uri_get_scheme(http_uri));
        fprintf(fp, "host:%s\n", evhttp_uri_get_host(http_uri));
        fprintf(fp, "path:%s\n", evhttp_uri_get_path(http_uri));
        fprintf(fp, "port:%d\n", evhttp_uri_get_port(http_uri));
        fprintf(fp, "query:%s\n", evhttp_uri_get_query(http_uri));
        fprintf(fp, "userinfo:%s\n", evhttp_uri_get_userinfo(http_uri));
        fprintf(fp, "fragment:%s\n", evhttp_uri_get_fragment(http_uri));
    }

private:
    struct evhttp_request*      req_;
    const struct evhttp_uri*    evh_uri_;   // 格式化后URI
    const  char*                uri_;
    const  char*                raw_query_; // 原始的查询串
    struct evbuffer*            resp_buf_;
    const struct evkeyvalq*     headers_;
    struct evkeyvalq            querys_;    // 查询串－健值对
    mem_node_t                  input_data_; // POST上传的数据
};

} // namespace wbox
