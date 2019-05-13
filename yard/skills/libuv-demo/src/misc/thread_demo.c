/*
 * =====================================================================================
 *
 *       Filename:  thread_demo.c
 *
 *    Description:  thread test
 *
 *        Version:  1.0
 *        Created:  05/12/2019 07:52:12
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2019, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */


/*
 * test_thread_equal.cc
 * uv_thread_t is pthread_t, and is type unsigned long int.
 * Created on: 2015年2月5日
 */
 
#include <stdlib.h>
#include <stdio.h>
#include <uv.h>
 
uv_thread_t main_thread_id;	//主线程id
uv_thread_t subthreads[2];	//传递给子线程的数据
 
/**
 * this method will run in subthread.
 * user can translate data from arg.
 * @param arg data from main thread or you can pass subthread's data to main thread.
 */
static void check_thread(void* arg) {
	uv_thread_t *thread_id = (uv_thread_t *)arg;
	uv_thread_t self_id = uv_thread_self();
	printf("main_thread_id:%lu\n", main_thread_id);
	printf("sub_thread_id:%lu\n", self_id);
	*thread_id = uv_thread_self();
}
 
int main()
{
	uv_thread_t threads[2];
	main_thread_id = uv_thread_self();	//获得当前线程的id
 
	uv_thread_create(threads + 0, check_thread, subthreads + 0);	//创建子线程
	uv_thread_create(threads + 1, check_thread, subthreads + 1);
 
	uv_thread_join(threads + 0);	//thread join，阻塞，直到目标子线程执行完成
	uv_thread_join(threads + 1);
 
	return 0;
}

