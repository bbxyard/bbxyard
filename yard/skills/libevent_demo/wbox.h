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

enum
{
    WBOX_MAX_HANDLER_CNT = 64
};

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
    virtual const char* get_params(const char* key) const = 0;

    // from HEAD
    virtual const char* get_hander(const char* key) const = 0;

    // from DATA
    virtual const byte_t* get_chunk(uint32_t* sz) const = 0;

    // response infomation
    virtual int  add_printf(const char *fmt, ...) = 0;
    virtual int  add_data(const byte_t* data, uint32_t sz) = 0;
    virtual void send_reply(int code, const char *reason) = 0;
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
typedef int (*wbox_fn_run)(int port, int worker_cnt, wbox_http_handler_node handlers[], int handler_cnt);
int wbox_run(int port, int worker_cnt, wbox_http_handler_node handlers[], int handler_cnt);

#ifdef __cplusplus
}
#endif

#endif // __WBOX_H__
