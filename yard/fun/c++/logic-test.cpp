/*
 * =====================================================================================
 *
 *       Filename:  logic-test.cpp
 *
 *    Description:  logic test
 *
 *        Version:  1.0
 *        Created:  12/27/17 22:32:14
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2017, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */


#include <stdlib.h>
#include <stdio.h>

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  12/27/17 22:32:24
 *  Description:  
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    for (int i = 1; i <= 7; ++i) {
        int cnt = ((i + 7 - 1) % 7 == 3) +
                ((i + 1) % 7 == 2) +
                ((i + 1) % 7 == 3) +
                ((i + 2) % 7 == 2) +
                (i == 2) +
                (i < 7 && i > 2) +
                (i != 6);
        printf("try i = %d, cnt = %d %s\n", i, cnt, (cnt == 1)? "done": "");
    }
    
    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
