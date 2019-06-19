#!/usr/bin/env python3
# python 线程测试


import _thread
import time

from yvhai.demo.base import YHDemo


def print_time(thread_name, interval, times):
    for cnt in range(times):
        time.sleep(interval)
        print(" -- %s: %s" % (thread_name, time.ctime(time.time())))


class RawThreadDemo(YHDemo):
    def __init__(self):
        super(RawThreadDemo, self).__init__('_thread')

    @staticmethod
    def main():
        try:
            _thread.start_new_thread(print_time, ("Thread-01", 1, 10))
            _thread.start_new_thread(print_time, ("Thread-02", 2, 6))
        except:
            print("Error: 无法启动线程")

        # 主线程无限等待
        while 1:
            pass

    @staticmethod
    def demo(args=[]):
        RawThreadDemo.main()


if __name__ == '__main__':
    RawThreadDemo.demo()
