#!/usr/bin/env python3
# 高级文件目录管理

import shutil

from yvhai.demo.base import YHDemo


class ShUtilDemo(YHDemo):
    def __init__(self):
        super(ShUtilDemo, self).__init__('ShUtil')

    @staticmethod
    def test_copy():
        _sec = ShUtilDemo.mark_section('场景-文件拷贝、移动')
        shutil.copyfile('/etc/passwd', '/tmp/etc_passwd.bak')
        shutil.move('/tmp/etc_passwd.bak', '/tmp/etc_passwd.txt')

    @staticmethod
    def demo(args=[]):
        ShUtilDemo.test_copy()
