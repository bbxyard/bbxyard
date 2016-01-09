/*
 * =====================================================================================
 *
 *       Filename:  apr-test.cpp
 *
 *    Description:  apr test 
 *
 *        Version:  1.0
 *        Created:  2014/07/27 13时37分28秒
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2014, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */

#include "apr.h"
#include "apu.h"


#include <stdio.h>
#include <stdlib.h>

static void apr_pool_test()
{
    apr_pool_t pool;
    apr_pool_create();
    
}

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  2014/07/27 13时38分03秒
 *  Description:  
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    fprintf(stdout, "this is apr test case\n");
    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
