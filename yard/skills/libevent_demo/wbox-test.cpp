/*
 * =====================================================================================
 *
 *       Filename:  wbox-test.cpp
 *
 *    Description:  test wbox svc
 *
 *        Version:  1.0
 *        Created:  2016年03月11日 16时58分59秒
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include "wbox.h"


static void http_request_on_dump(wbox_http_ctx* ctx)
{
    // query info
    ctx->add_printf("dump from %s\r\n", ctx->uri());
    const char* name = ctx->query("name");
    const char* id   = ctx->query("id");
    ctx->add_printf("name=%s\r\n", name);
    ctx->add_printf("id=%s\r\n", id);
    // output head info
    ctx->add_header("Server", "by libevent");
    ctx->add_header("Author", "Brian");
    ctx->add_header("Content-Type", "text/plain; charset=UTF-8");
    ctx->add_header("Connection", "close");
    // sent reply data
    ctx->send_reply(200, "dump finished!");
}

static void http_request_on_txt(wbox_http_ctx* ctx)
{
    ctx->add_printf("text from %s\n", ctx->uri());
    ctx->send_reply(200, "text request");
}



/*
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  2016年03月11日 17时00分06秒
 *  Description:
 * =====================================================================================
 */
int main (int argc, char* argv[])
{
    const char* libname = "libwbox.so";
    int ret = 0;
    wbox_http_handler_node handles[WBOX_MAX_HANDLER_CNT] =
    {
        { "/dump", "dump", http_request_on_dump},
        { "/text", "text", http_request_on_txt}
    };

    // load so
    if (argc >= 2)
    {
        libname = argv[1];
    }
    void* h = dlopen(libname, RTLD_LAZY);
    if (NULL == h)
    {
        perror("load libwbox error");
        return -2;
    }

    wbox_fn_run wrun = (wbox_fn_run)dlsym(h, "wbox_run");
    if (wrun != NULL)
    {
        ret = wrun(4487, 10, handles, 2);
    }

    dlclose(h);
    return ret;
} /* -----  end of function main  ----- */
