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
#include <stdlib.h>
#include <time.h>

#include <cassert>
#include <map>
#include <string>


namespace wbox {

#define FORMAT_PRINT_BUF(fmt, buf)  \
    buf[0] = 0;                     \
    va_list vl;                     \
    va_start(vl, fmt);              \
    vsprintf(buf, fmt, vl);         \
    va_end(vl);                     \

#define VERIFY_AND_BREAK(cond)     if (!(cond))   break;

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
        enum_all_from_evkeyvalq(headers_, input_header_kvpairs_, input_header_kvpair_cnt_);
        // 解析URI的参数(即GET方法的参数)
        evhttp_parse_query_str(raw_query_, &querys_);
        enum_all_from_evkeyvalq(&querys_, input_query_kvpairs_, input_query_kvpair_cnt_);
        // 获得POST数据
        evbuffer* in_ev_buf = evhttp_request_get_input_buffer(req_);
        memset(&input_content_node_, 0, sizeof(input_content_node_));
        memset(&input_data_, 0, sizeof(input_data_));
        if (in_ev_buf != NULL && evbuffer_get_length(in_ev_buf) > 0)
        {
            input_data_.sz = input_data_.cap = evbuffer_get_length(in_ev_buf);
            input_data_.mem = new byte_t[input_data_.cap];
            evbuffer_copyout(in_ev_buf, input_data_.mem, input_data_.sz);
        }
        // 解析POST数据
        parse_content();
    }
    virtual ~wbox_http_ctx_impl()
    {
        evbuffer_free(resp_buf_);
        if (NULL != input_data_.mem)
        {
            delete[] input_data_.mem;
        }
        if (NULL != input_content_node_.mem)
        {
            delete[] input_content_node_.mem;
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
    virtual const char* get_input_header(const char* key) const
    {
        const char* value = evhttp_find_header(headers_, key);
        return value;
    }
    virtual const char* get_input_content_type() const
    {
        return get_input_header("Content-Type");
    }
    virtual const wbox_kvpair_t* get_all_querys(uint32_t* cnt) const
    {
        if (cnt != NULL) *cnt = input_query_kvpair_cnt_;
        return input_query_kvpairs_;
    }
    virtual const wbox_kvpair_t* get_all_input_headers(uint32_t* cnt) const
    {
        if (cnt != NULL) *cnt = input_header_kvpair_cnt_;
        return input_header_kvpairs_;
    }

    // from DATA
    virtual const byte_t* get_input_data(uint32_t* bytes) const
    {
        if (bytes != NULL) *bytes = input_data_.sz;
        return input_data_.mem;
    }
    virtual const wbox_in_item_t* get_all_input_items(uint32_t* cnt) const
    {
        *cnt = input_content_item_cnt_;
        return input_content_items_;
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
        fprintf(fp, "Content Type [%s]\n", get_input_content_type() );
        fprintf(fp, "===============URI-INFO===============\n");
        print_uri_info(fp, evh_uri_);

        // # print peer info
        fprintf(fp, "remote host:post = %s:%d\n", get_remote_host(), get_remote_port());

        // # print headers
        uint32_t cnt = 0;
        const wbox_kvpair_t* all_headers = get_all_input_headers(&cnt);
        fprintf(fp, "============headers(%d)===============//by get_all_input_headers(&cnt)\n", cnt);
        print_kvs(fp, all_headers, cnt);

        // # print querys
        const wbox_kvpair_t* all_querys = get_all_querys(&cnt);
        fprintf(fp, "============querys(%d)===============//by get_all_querys(&cnt)\n", cnt);
        print_kvs(fp, all_querys, cnt);

        // print input data
        uint32_t data_size = 0;
        const byte_t* data = get_input_data(&data_size);
        fprintf(fp, "============input-data(%d)===============//by get_input_data(&data_size)\n", data_size);
        if (data != NULL)
        {
            fwrite(data, sizeof(byte_t), data_size, fp);
        }
        fprintf(fp, "\n");

        // print input data parsed result
        if (input_content_item_cnt_ > 0)
        {
            fprintf(fp, "============input-data-parsed(%d)===============//by get_all_input_items(&item_cnt)\n", input_content_item_cnt_);
            uint32_t item_cnt = 0;
            const wbox_in_item_t* items = get_all_input_items(&item_cnt);
            for (uint32_t i = 0; i < item_cnt; ++i)
            {
                const wbox_in_item_t& item = items[i];
                fprintf(fp, "##[%d]##{%s:%s, %s:%s, %s:%s, size:%d}\n", i,
                                        "name", item.name, "attrs", item.attrs,
                                        "content_type", item.content_type, item.content_bytes);
                fwrite(item.content, 1, item.content_bytes, fp);
                fprintf(fp, "\n-----------------------\n");
            }
        }

        // # end
        fprintf(fp, "==================END===============\n");
    }
private:
    static void enum_all_from_evkeyvalq(const evkeyvalq* kvq, wbox_kvpair_t kvs[], uint32_t& cnt)
    {
        int i = 0;
        for (const struct evkeyval* node = kvq->tqh_first; node != NULL; node = node->next.tqe_next, ++i)
        {
            if (i >= WBOX_MAX_HANDLER_CNT)
                break;
            kvs[i].key = node->key;
            kvs[i].value = node->value;
        }
        cnt = i;
    }
    static void print_kvs(FILE* fp, const wbox_kvpair_t kvs[], int max_cnt)
    {
        for (int i = 0; i < max_cnt; ++i)
        {
            const wbox_kvpair_t& kv = kvs[i];
            fprintf(fp, "%s : %s\n", kv.key, kv.value);
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
    // parse
    // Content-Type : multipart/form-data; boundary=---------------------------12677192581372307319394741653
    // Content-Length : 2057
    void parse_content()
    {
        // parse head
        memset(input_content_items_, 0, sizeof(input_content_items_));
        input_content_item_cnt_ = 0;
        *input_content_type_ = 0;
        *input_content_boundary_ = 0;
        const char* raw_content_type = get_input_header("Content-Type");
        if (NULL != raw_content_type)
        {
            const char* BOUND_MARK = "; boundary=";
            const char* psz = strstr(raw_content_type, BOUND_MARK);
            if (NULL != psz)
            {
                strncpy(input_content_type_, raw_content_type, psz - raw_content_type);
                strcpy(input_content_boundary_, psz + strlen(BOUND_MARK));
            }
            else
            {
                strcpy(input_content_type_, raw_content_type);
            }
        }
        // parse content
        if (strcmp(input_content_type_, "multipart/form-data") == 0)
        {
            int content_size = (int)atoi(get_input_header("Content-Length"));
            if (content_size > 0)
            {
                // 分配内存块
                input_content_node_.sz  = input_data_.sz;
                input_content_node_.cap = input_data_.cap;
                input_content_node_.mem = new byte_t[input_content_node_.cap];
                memcpy(input_content_node_.mem, input_data_.mem, input_content_node_.sz);
                // 正式解析
                assert(content_size == input_data_.sz && "这个必须要相等哟!!");
                parse_content_i(input_content_node_, input_content_boundary_, input_content_items_, input_content_item_cnt_);
            }
        }
    }


    static void parse_content_i(mem_node_t& input_data, const char* content_boundary, wbox_in_item_t items[], uint32_t& cnt)
    {
        static const char* BOUND_DISPOSITION_MARK        = "Content-Disposition: form-data; name=";
        static const char* BOUND_TYPE_MARK               = "Content-Type: ";
        static const uint32_t BOUND_DISPOSITION_MARK_LEN = (uint32_t)strlen(BOUND_DISPOSITION_MARK);
        static const uint32_t BOUND_TYPE_MARK_LEN        = (uint32_t)strlen(BOUND_TYPE_MARK);

        char* data_begin           = (char*)(input_data.mem);
        const char* BOUND_DATA_END = (char*)(input_data.mem + input_data.sz);
        const uint32_t CONTENT_BOUNDARY_LEN = (uint32_t)strlen(content_boundary);
        cnt = 0;
        while (true)
        {
            wbox_in_item_t item;
            memset(&item, 0, sizeof(item));
            char* psz = strstr(data_begin, content_boundary);
            VERIFY_AND_BREAK(NULL != psz && psz < BOUND_DATA_END && "除非结束啦!");
            psz += CONTENT_BOUNDARY_LEN;
            VERIFY_AND_BREAK (psz < BOUND_DATA_END && "正常＝2,应该是到结尾了--");

            // 开始解析一块数据区.
            // Content-Disposition: form-data; name="fin"; filename="README.md"
            psz = strstr(psz, BOUND_DISPOSITION_MARK);
            VERIFY_AND_BREAK(NULL != psz);
            psz += BOUND_DISPOSITION_MARK_LEN;
            item.name = item.attrs = psz;
            replace_crlf_2_zero_and_monvon(psz);
            parse_content_name_and_other((char*&)item.name, (char*&)item.name, (char*&)item.attrs);

            // Content-Type: text/markdown
            if (is_blank_line(psz))
            {
                item.content_type = "";
                replace_crlf_2_zero_and_monvon(psz);
            }
            else if (strncmp(psz, BOUND_TYPE_MARK, BOUND_TYPE_MARK_LEN) == 0)
            {
                psz += BOUND_TYPE_MARK_LEN;
                item.content_type = psz;
                replace_crlf_2_zero_and_monvon(psz); // 将回车置为0并前进
                // 后面还有一行空白，这里也要移除
                if (is_blank_line(psz)) replace_crlf_2_zero_and_monvon(psz);
            }
            else
            {
                // 除非包出错啦!!
                fprintf(stderr, "Invalid format!! RETURN! last_pos=%d\n", (byte_t*)psz - input_data.mem);
                break;
            }

            // 以下就是纯数据区啦!
            item.content = (byte_t*)psz;
            for (; psz < BOUND_DATA_END; ++psz)
            {
                // search '\r\n'
                if (*psz != '\r')
                    continue;
                // test boundary length (1字节回车、1字节换行、2字节补数)
                if (psz + 2 + 2 + CONTENT_BOUNDARY_LEN >= BOUND_DATA_END)
                {
                    fprintf(stderr, "Invalid package!! RETURN! last_pos=%d\n", (byte_t*)psz - input_data.mem);
                    break;
                }
                if (*(psz + 1) != '\n')
                    continue;
                // 找到BOUND了，此处就是数据终点啦
                if (memcmp(psz + 2 + 2, content_boundary, CONTENT_BOUNDARY_LEN) == 0)
                {
                    item.content_bytes = (byte_t*)psz - item.content;
                    replace_crlf_2_zero_and_monvon(psz);
                    break;
                }
            }

            data_begin = psz;   // 下一轮循环
            items[cnt++] = item;
        }
    }

    // 将回车和换行，换成字符0,方便字符串显示
    static char* replace_crlf_2_zero_and_monvon(char*& psz)
    {
        static const char* crlf        = "\r\n";
        static const uint32_t crlf_len = (uint32_t)strlen(crlf);
        char* p = strstr(psz, crlf);
        if (p != NULL)
        {
            p[0] = p[1] = 0;    // 将回车置为0
            psz = p + crlf_len;
        }
        return psz;
    }
    static bool is_blank_line(char* psz)
    {
        bool bret = (strncmp(psz, "\r\n", 2) == 0);
        return bret;
    }

    // parse Content-Disposition: form-data; name="fin"; filename="README.md"
    // key need to parse: "fin"; filename="README.md"
    static int parse_content_name_and_other(char* psz, char*& part1, char*& part2)
    {
        part1 = part2 = "";
        int cnt = 0;
        char* left = strchr(psz, '\"');
        char* right = strchr(psz + 1, '\"');
        if (NULL != left && NULL != right)
        {
            part1 = left + 1;       // fin
            *right = 0;             // 要引号置为字符串终止符0
            ++cnt;
            // 解析第二段
            psz = right + 1;
            if (*psz != 0 && (left = strstr(psz, "; ")) != NULL)
            {
                part2 = left + 2;   // filename="README.md"
                ++cnt;
            }
        }
        return cnt;
    }

private:
    struct evhttp_request*      req_;
    const struct evhttp_uri*    evh_uri_;   // 格式化后URI
    const  char*                uri_;
    const  char*                raw_query_; // 原始的查询串
    struct evbuffer*            resp_buf_;
    const struct evkeyvalq*     headers_;
    struct evkeyvalq            querys_;    // 查询串－健值对
    mem_node_t                  input_data_;        // POST上传的数据
    char                        input_content_type_[WBOX_MAX_CONTENT_TYPE_LEN];
    char                        input_content_boundary_[WBOX_MAX_CONTENT_BOUNDARY_LEN];

    // SDK 返回需要数据
    wbox_kvpair_t               input_query_kvpairs_[WBOX_MAX_HANDLER_CNT];   // 存储所有query属性对
    wbox_kvpair_t               input_header_kvpairs_[WBOX_MAX_HANDLER_CNT];  // 存储所有hander属性对
    uint32_t                    input_query_kvpair_cnt_;
    uint32_t                    input_header_kvpair_cnt_;

    mem_node_t                  input_content_node_;    // 内容buff
    wbox_in_item_t              input_content_items_[WBOX_MAX_IN_ITEM_CNT];
    uint32_t                    input_content_item_cnt_;
};

} // namespace wbox


// // enums
// virtual void enum_all_querys (const char* keys[], const char* values[], uint32_t* cnt) const
// {
//     enum_all_from_evkeyvalq(&querys_, keys, values, cnt);
// }
// virtual void enum_all_headers(const char* keys[], const char* values[], uint32_t* cnt) const
// {
//     enum_all_from_evkeyvalq(headers_, keys, values, cnt);
// }
