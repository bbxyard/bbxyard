#!/usr/bin/env python3
# 公共约束

import os


class YHDemo:
    def __init__(self, name='YHDemo'):
        self.name = name
        print('YHDemo/%s Created' % self.name)

    def __del__(self):
        print('YHDemo/%s Destroyed' % self.name)

    # 查询配置
    @staticmethod
    def get_conf_value(key, default_value=None):
        out_value = os.getenv(key)
        if default_value and not out_value:
            out_value = default_value
        return out_value

    @staticmethod
    def demo(args=[]):
        pass
