/*
 * =====================================================================================
 *
 *       Filename:  wbox.h
 *
 *    Description:  web box as http container
 *
 *        Version:  1.1
 *        Created:  03/09/16 00:25:57
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#ifndef __WBOX_H__
#define __WBOX_H__


typedef unsigned int uint32_t;
typedef unsigned char byte_t;
typedef unsigned short port_t;

enum
{
    WBOX_MAX_HANDLER_CNT = 64
};

typedef enum wbox_cmd_type  // value same as EVHTTP
{
	WBOX_REQ_GET     = 1 << 0,
	WBOX_REQ_POST    = 1 << 1,
	WBOX_REQ_HEAD    = 1 << 2,
	WBOX_REQ_PUT     = 1 << 3,
	WBOX_REQ_DELETE  = 1 << 4,
	WBOX_REQ_OPTIONS = 1 << 5,
	WBOX_REQ_TRACE   = 1 << 6,
	WBOX_REQ_CONNECT = 1 << 7,
	WBOX_REQ_PATCH   = 1 << 8
} wbox_cmd_type;

#ifdef __cplusplus
extern "C" {
#endif

// 前向申明

// 服务框架在内部完成封装
struct wbox_http_ctx
{
    // request infomation
    // from URI
    virtual const char* uri() const = 0;
    virtual const char* query(const char* key) const = 0;
    virtual wbox_cmd_type get_cmd_type() const = 0;

    // from HEAD
    virtual const char* get_hander(const char* key) const = 0;

    // from DATA
    virtual const byte_t* get_chunk(uint32_t* sz) const = 0;

    // response infomation
    virtual int  add_header(const char* key, const char* value) = 0;
    virtual int  add_header_printf(const char* key, const char* fmt, ...) = 0;
    virtual int  add_data(const byte_t* data, uint32_t sz) = 0;
    virtual int  add_data_printf(const char *fmt, ...) = 0;
    // send
    virtual int  send_reply_with_file(const char* file) = 0; // 自动设置headers
    virtual void send_reply(int code, const char *reason) = 0;

    // more info
    virtual const char* get_remote_host() const = 0;
    virtual port_t get_remote_port() const = 0;
    virtual const char* get_fragment() const = 0;
};

// 用户实现
typedef void (*wbox_fn_http_handler)(wbox_http_ctx* ctx);
typedef struct _wbox_http_handler_node
{
    char uri[512];
    char name[64];
    wbox_fn_http_handler handler;
} wbox_http_handler_node;


/**
 *
 */
typedef int (*wbox_fn_run)(int port, int worker_cnt, wbox_http_handler_node handlers[], int handler_cnt, const char* params);
int wbox_run(int port, int worker_cnt, wbox_http_handler_node handlers[], int handler_cnt, const char* params);

#ifdef __cplusplus
}
#endif

#endif // __WBOX_H__
