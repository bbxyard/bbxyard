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
#include <map>
#include <string>


namespace wbox {

#define FORMAT_PRINT_BUF(fmt, buf)  \
    buf[0] = 0;                     \
    va_list vl;                     \
    va_start(vl, fmt);              \
    vsprintf(buf, fmt, vl);         \
    va_end(vl);                     \

typedef std::map<std::string, std::string>  str_map;

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

} // namespace wbox
