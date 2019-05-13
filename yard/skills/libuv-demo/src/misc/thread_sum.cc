/*
 * =====================================================================================
 *
 *       Filename:  thread_sum.c
 *
 *    Description:  thread sum
 *
 *        Version:  1.0
 *        Created:  05/12/2019 08:21:38
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com 
 *      Copyright:  copyright (c) 2019, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */


/*
 * test_thread_plus.cc
 * uv_thread_t is pthread_t, and is type unsigned long int.
 * Created on: 2015年2月5日
 */
 
#include <stdlib.h>
#include <stdio.h>
#include <uv.h>
 
struct thread_data {
	int start;
	int end;	//结束数字
	int result;	//子线程计算结果
};
 
/**
 * this method will run in subthread.
 * user can translate data from arg.
 * @param arg data from main thread or you can pass subthread's data to main thread.
 */
static void plus_thread(void* arg) {
	thread_data *data = (thread_data *)arg;
	data->result = 0;
	for(int i = data->start; i <= data->end; i++)
	{
		data->result += i;
	}
}
 
int main()
{
	uv_thread_t threads[10];
	thread_data dataArray[10];
	for(int i = 0; i < 10; i++)
	{
		thread_data* data = dataArray + i;
		data->start = i*10 + 1;
		data->end = (i + 1)*10;
		uv_thread_create(threads + i, plus_thread, data);	//创建子线程
	}
 
	int sum = 0;
	for(int i = 0; i < 10; i++)
	{
		uv_thread_join(threads + i);	//thread join，阻塞，直到目标子线程执行完成
		thread_data* data = dataArray + i;
		sum += data->result;
	}
 
	printf("the sum is:%d.\n", sum);
	return 0;
}

