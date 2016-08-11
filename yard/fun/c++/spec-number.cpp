/*
 * =====================================================================================
 *
 *       Filename:  calc.cpp
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2016年02月22日 18时09分06秒
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#include <stdio.h>

int main()
{
    for (int i = 1000; i < 10000; ++i)
    {
        bool b = (i + 1) % 2 == 0 &&
            (i + 2) % 3 == 0 &&
            (i + 3) % 4 == 0 &&
            (i + 4) % 5 == 0 &&
            (i + 5) % 6 == 0 &&
            (i + 6) % 7 == 0 &&
            (i + 7) % 8 == 0 &&
            (i + 8) % 9 == 0 &&
            (i + 9) % 10 == 0;
        if (b)
        {
            fprintf(stdout, "the number is: %d\n", i + 4);
        }

    }

    return 0;
}
