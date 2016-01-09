/*
 * =====================================================================================
 *
 *       Filename:  hello.cpp
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2016年01月06日 14时44分26秒
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */


#include <stdlib.h>
#include <stdio.h>

int myinc(int x)
{
    return ++x;
}
int myadd(int* lhs, int* rhs, int* result)
{
    *result = *lhs + *rhs;
    return *result;
}
static void demo()
{
    typedef int (*FUNC1)(int in);
    typedef int (*FUNC2)(int*, int*, int*);
    FUNC1 f1 = myinc;
    FUNC2 f2 = myadd;
    int y = 2015;
    int m = 1;
    int d = 6;
    int result = 0;
    fprintf(stdout, "this year is: %d\n", f1(y));
    fprintf(stdout, "test point result is: %d\n", f2(&m, &d, &result));
}


/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  2016年01月06日 14时44分34秒
 *  Description:  
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    demo();
    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
