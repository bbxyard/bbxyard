/*
 * =====================================================================================
 *
 *       Filename:  posix_util.c
 *
 *    Description:  posix util impl file
 *
 *        Version:  1.0
 *        Created:  03/09/16 00:36:11
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#include "posix_util.h"


void cbox_reg_signal_handler(int signal, Proc_signal_handler handler)
{
    /* Set signal handlers */
    sigset_t sigset;
    sigemptyset(&sigset);
    struct sigaction siginfo = {
        .sa_handler = handler,
        .sa_mask = sigset,
        .sa_flags = SA_RESTART,
    };
    sigaction(signal, &siginfo, NULL);
}
