/*
 * =====================================================================================
 *
 *       Filename:  sqrt.cpp
 *
 *    Description:  get the sqr root of N
 *
 *        Version:  1.0
 *        Created:  10/22/14 00:38:21
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2014, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */


#include <stdlib.h>
#include <stdio.h>

#define DIFF 0.001

float get_sqrt0(float N, float Xn)
{
    float Xn1 = (Xn + N / Xn) * 0.5;
    if (Xn1 - Xn < 0.000001 || Xn - Xn1 < 0.000001)
    {
        return Xn1;
    }
    return get_sqrt0(N, Xn1);
}

float get_sqrt(float N, float Xn)
{
    if (N - Xn * Xn < DIFF && N - Xn * Xn > 0)
    {
        return Xn;
    }
    fprintf(stdout, "**** %f\n", Xn);

    Xn = (Xn + N / Xn) * 0.5;
    return get_sqrt(N, Xn);
}

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  10/22/14 00:38:48
 *  Description:  
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    for (int i = 2; i < 6; ++i)
    {
        fprintf(stdout, "sqrt(%d) = %f\n", i, get_sqrt(i, 1.0f));
    }

    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
