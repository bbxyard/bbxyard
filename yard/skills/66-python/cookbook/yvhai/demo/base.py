#!/usr/bin/env python3
# 公共约束

import os


class YHDemo:
    def __init__(self, name='YHDemo'):
        self.name = name
        print('YHDemo/%s Created' % self.name)

    def __del__(self):
        print('YHDemo/%s Destroyed' % self.name)

    def set_name(self, name):
        self.name = name

    # 查询配置
    @staticmethod
    def get_conf_value(key, default_value=None):
        out_value = os.getenv(key)
        if default_value and not out_value:
            out_value = default_value
        return out_value

    # 主题跟踪
    @staticmethod
    def mark_section(subject_name):
        return SectionMarker(subject_name)

    @staticmethod
    def demo(args=[]):
        pass


# 段标记
class SectionMarker:
    def __init__(self, name):
        self.name = name
        print('\n=== %s [ENTER] ===' % name)

    def __del__(self):
        print('=== %s [LEAVE] ===' % self.name)
