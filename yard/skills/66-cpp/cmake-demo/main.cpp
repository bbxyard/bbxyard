/*
 * =====================================================================================
 *
 *       Filename:  testnum.cpp
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2014/08/13 00时44分17秒
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2014, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */

#include <stdio.h>

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  test9
 *       Author:  bbxyard
 *      Created:  2014/08/13 00时44分31秒
 *  Description:  find num to 9
 * =====================================================================================
 */
void test9 ()
{
    for (int i = 1; i < 10; ++i)
    {
        int x = ((i * 3) + 3) * 3;
        fprintf(stdout, "%d: %d\n", i, x);
    }
} /* -----  end of function test9  ----- */



#include <stdlib.h>


void before_main() __attribute__((constructor));
void after_main() __attribute__((destructor));

void before_main()
{
    fprintf(stdout, "before main\n");
}
 
void after_main()
{
    fprintf(stdout, "after main\n");
}

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  2014/08/13 00时48分35秒
 *  Description:  
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    test9();
    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
