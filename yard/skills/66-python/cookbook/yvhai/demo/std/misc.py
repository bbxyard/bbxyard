#!/usr/bin/env python3
# 杂项

import math

from yvhai.demo.base import YHDemo


class MiscDemo(YHDemo):
    def __init__(self):
        super(MiscDemo, self).__init__('Misc')

    @staticmethod
    def test_math():
        _sec = MiscDemo.mark_section('数学库')
        print(' -- math.pi: ', math.pi)
        print(' -- math.e: ', math.e)
        print(' -- math.pow(2, 10): ', math.pow(2, 10))

    @staticmethod
    def demo(args=[]):
        MiscDemo.test_math()
        pass
