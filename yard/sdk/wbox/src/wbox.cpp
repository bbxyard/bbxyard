/*
 * =====================================================================================
 *
 *       Filename:  wbox.cpp
 *
 *    Description:  www box impliment file
 *
 *        Version:  1.0
 *        Created:  03/12/16 16:25:43
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#include "wbox.h"

// 各种实现
int wbox_evhttp_mt_run(int port, int worker_cnt, wbox_http_handler_node handlers[], int handler_cnt, const char* params);


int wbox_run(int port, int worker_cnt, wbox_http_handler_node handlers[], int handler_cnt, const char* params)
{
    int ret = wbox_evhttp_mt_run(port, worker_cnt, handlers, handler_cnt, params);
    return ret;
}
