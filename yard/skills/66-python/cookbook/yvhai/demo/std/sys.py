#!/usr/bin/env python3
# sys

import sys

from yvhai.demo.base import YHDemo


class SysDemo(YHDemo):
    def __init__(self):
        super(SysDemo, self).__init__('Sys')

    # 错误流
    @staticmethod
    def test_stderr():
        _sec = SysDemo.mark_section('标准错误流演示')
        sys.stderr.write('Warning, log file not found starting a new one\n')

    # 命令行参数
    @staticmethod
    def test_cmdline_args():
        _sec = SysDemo.mark_section('命令行参数')
        print(' -- sys.argv: ', sys.argv)

    # 退出
    @staticmethod
    def test_danger():
        sys.exit()

    @staticmethod
    def demo(args=[]):
        SysDemo.test_stderr()
        SysDemo.test_cmdline_args()
        # SysDemo.test_danger()
