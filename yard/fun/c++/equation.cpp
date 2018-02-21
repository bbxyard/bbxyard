/*
 * =====================================================================================
 *
 *       Filename:  equation.cpp
 *
 *    Description:  solove equations
 *
 *        Version:  1.0
 *        Created:  02/21/18 00:40:14
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com
 *      Copyright:  copyright (c) 2018, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#include <stdlib.h>
#include <stdio.h>

bool soloveBinaryLinearEquationGroup(float e1[3], float e2[3], float result[2])
{
    float m = 1.0 * e2[0] / e1[0];
    float ap22 = e2[1] - m * e1[1];
    float bp2 = e2[2] - m * e1[2];
    float y = 1.0 * bp2 / ap22;
    float x = (e1[2] - e1[1] * y) / e1[0];
    result[0] = x;
    result[1] = y;
    return true;
}

/*
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  02/21/18 00:40:38
 *  Description:
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    float e1[3] = {0};
    float e2[3] = {0};
    float result[2] = {0};

    fprintf(stdout, "like ax+by=c input a, b, c please\n");
    fprintf(stdout, "equation1 a,b,c: ");
    scanf("%f,%f,%f", e1, e1 + 1, e1 + 2); 
    fprintf(stdout, "equation2 a,b,c: ");
    scanf("%f,%f,%f", e2, e2 + 1, e2 + 2);
    soloveBinaryLinearEquationGroup(e1, e2, result);
    fprintf(stdout, "result (x,y)=(%.2f, %.2f)\n", result[0], result[1]);
    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
