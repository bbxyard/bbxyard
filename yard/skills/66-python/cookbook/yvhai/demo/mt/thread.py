#!/usr/bin/env python3
# 线程测试


import threading
import time
import random
import queue

from yvhai.demo.base import YHDemo


class ThreadDemo(YHDemo):
    def __init__(self):
        super(ThreadDemo, self).__init__('threading')

    @staticmethod
    def demo(args=[]):
        # MyThreadA.main(has_lock=False)
        # MyThreadA.main(has_lock=True)
        PVThreadDemo.main()


# 全局线程退出标志
g_exit_flag = 0


# 测试1
class MyThreadA(threading.Thread):
    def __init__(self, mark_id, name, interval, times):
        threading.Thread.__init__(self)
        self.mark_id = mark_id
        self.name = name
        self.interval = interval
        self.times = times
        self.lock = None

    def create_lock(self):
        self.lock = threading.Lock()

    def run(self):
        _sec = YHDemo.mark_section('MyThreadA %s ' % self.name)
        if self.lock:
            self.lock.acquire()
            MyThreadA.print_time(self.name, self.interval, self.times)
            self.lock.release()
        else:
            MyThreadA.print_time(self.name, self.interval, self.times)

    @staticmethod
    def print_time(thread_name, interval, times):
        for cnt in range(times):
            if g_exit_flag:
                break
            time.sleep(interval)
            print(" -- %s: %s" % (thread_name, time.ctime(time.time())))

    @staticmethod
    def main(has_lock=False):
        # 创建线程组+启动
        thrd_group = []
        for idx in range(5):
            thrd = MyThreadA(idx + 1, "thread-{0}".format(idx + 1), random.randrange(3), 10)
            # 如果有锁，则按需创建锁
            if has_lock:
                thrd.create_lock()
            thrd.start()
            thrd_group.append(thrd)
        # 等待完成
        for thrd in thrd_group:
            thrd.join()
        print(' -- 主线程等大家完成了，再结束')


# 测试 - 生产者、消费者模型
class PVThreadDemo:
    @staticmethod
    def main():
        job_queue_lock = threading.Lock()
        job_queue = queue.Queue(100)
        # 启动boss线程
        boss_thread = BossThread(job_queue, job_queue_lock)
        boss_thread.start()
        # 启动worker线程
        worker_threads = []
        for k in range(5):
            t = WorkerThread('worker-thread-{0}'.format(k + 1), job_queue, job_queue_lock)
            t.start()
            worker_threads.append(t)
        # 等待工作线程结束
        for t in worker_threads:
            t.join()
        # 主线程结束
        print(' -- All Tasks Done.')


class BossThread(threading.Thread):
    def __init__(self, job_queue, job_queue_lock):
        threading.Thread.__init__(self)
        self.job_queue = job_queue
        self.job_queue_lock = job_queue_lock

    # 分配资源
    def run(self):
        self.job_queue_lock.acquire()
        for x in range(16):
            prefix = random.choice(['apple', 'banana', 'orange', 'pear', 'beer'])
            job = {'name': '{0}-{1}'.format(prefix, x)}
            self.job_queue.put(job)
        self.job_queue_lock.release()


class WorkerThread(threading.Thread):
    def __init__(self, name, job_queue, job_queue_lock):
        threading.Thread.__init__(self)
        self.worker_name = name
        self.job_queue = job_queue
        self.job_queue_lock = job_queue_lock

    # 处理任务
    def run(self):
        while True:
            job = None
            # 获得一个任务
            self.job_queue_lock.acquire()
            if not self.job_queue.empty():
                job = self.job_queue.get()
            self.job_queue_lock.release()
            # 处理一个任务
            if job:
                self.handle_one(job)
            time.sleep(1)

    def handle_one(self, job):
        print(' -- procesing: {0} / {1} / {2}'.format(self.name, self.worker_name, job['name']))

