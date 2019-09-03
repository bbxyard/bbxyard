/*
 * =====================================================================================
 *
 *       Filename:  curl-demo.cpp
 *
 *    Description:  curl demo
 *
 *        Version:  1.0
 *        Created:  2014/08/08 20时36分30秒
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2014, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */

#include "curl/curl.h"
#include "curl/easy.h"


#include <stdlib.h>
#include <stdio.h>

static size_t writedata_callback(void* buffer, size_t size, size_t cnt, void* user)
{
    FILE* fp = (FILE*)user;
    fwrite(buffer, size, cnt, fp);
}

static void simple_test(const char* uri, FILE* fp)
{
   CURL* h = curl_easy_init();

   // uri
   curl_easy_setopt(h, CURLOPT_URL, uri);
   // show http protocal head
   curl_easy_setopt(h, CURLOPT_HEADER, true);
   // set timeout
   curl_easy_setopt(h, CURLOPT_TIMEOUT, 1500);
   // data and data-function callback
   curl_easy_setopt(h, CURLOPT_WRITEFUNCTION, writedata_callback);
   curl_easy_setopt(h, CURLOPT_WRITEDATA, fp);
}


/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *       Author:  bbxyard
 *      Created:  2014/08/08 20时36分53秒
 *  Description:  
 * =====================================================================================
 */
int main (int argc, char *argv[])
{
    const char* uri = "www.baidu.com";
    if (argc >= 2)
    {
        uri = argv[1];
    }
    simple_test(uri, stdout);
    return EXIT_SUCCESS;
} /* ----------  end of function main  ---------- */
