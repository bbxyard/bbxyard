#!/usr/bin/env python3
# MySQL 数据管理Demo

from yvhai.demo.base import YHDemo


class MySQLDemo(YHDemo):
    def __init__(self):
        super(MySQLDemo, self).__init__('MySQL')

    def connect(self):
        pass

    def insert(self, rows):
        pass

    @staticmethod
    def demo(args=[]):
        x = MySQLDemo()
        print(x)
