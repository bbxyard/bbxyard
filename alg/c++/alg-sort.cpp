/*
 * =====================================================================================
 *
 *       Filename:  alg-sort.cpp
 *
 *    Description:  sort method
 *
 *        Version:  1.0
 *        Created:  2014年07月05日 14时27分41秒
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


class sorter
{
public:
    static void demo()
    {
        int a[] = { 5, 1, 8, 90, 18, 7, 85 };
        int n = (int)(sizeof(a) / sizeof(a[0]));
        show("org: \t\t", a, n);
        insert_sort2(a, n);
        show("insert_sort: \t", a, n);
    }

    static void show(const char* prompt, int a[], int n)
    {
        fprintf(stdout, "%s", prompt);
        for (int i = 0; i < n; ++i)
        {
            fprintf(stdout, "%d", a[i]);
            fprintf(stdout, "%c", ((i + 1 != n)? '\t': '\n'));
        }
    }
    

public:
    static void insert_sort(int a[], int n)
    {
        for (int i = 1; i < n; ++i)
        {
            int temp = a[i];
            int j = i;
            for (; j > 0 && a[j - 1] > temp; --j)
            {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    static void insert_sort2(int a[], int n)
    {
        if (n > 1)
        {
            insert_sort2(a, n - 1);
            int temp = a[n - 1];
            int j = n - 1;
            for (; j > 0 && a[j - 1] > temp; --j)
            {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

};

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  2014年07月05日 14时28分08秒
 *  Description:  
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    sorter::demo();
    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
