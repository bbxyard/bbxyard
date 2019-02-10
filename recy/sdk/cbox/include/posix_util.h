/*
 * =====================================================================================
 *
 *       Filename:  posix_util.h
 *
 *    Description:  posix util
 *
 *        Version:  1.0
 *        Created:  03/09/16 00:27:36
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#ifndef __POSIX_UTIL_H__
#define __POSIX_UTIL_H__


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <unistd.h>
#include <signal.h>


#ifdef __cplusplus
extern "C" {
#endif


/**
 * 注册信号处理
 */
typedef void (*Proc_signal_handler)(int sig);
void cbox_reg_signal_handler(int signal, Proc_signal_handler handler);


#ifdef __cplusplus
}
#endif


#endif // __POSIX_UTIL_H__
