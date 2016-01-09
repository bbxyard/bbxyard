/*
 * =====================================================================================
 *
 *       Filename:  apr-md5-test.cpp
 *
 *    Description:  apr md5 test 
 *
 *        Version:  1.0
 *        Created:  2014/08/14 22时06分07秒
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2014, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */


#include <stdlib.h>

#include "apr.h"
#include "apr_md5.h"

void apr_md5_test(const void* buff, int size)
{
    apr_md5_ctx_t ctx;
    apr_md5_init();
    apr_md5_update();
    apr_md5_final();

}

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  2014/08/14 22时09分50秒
 *  Description:  
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    
    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
