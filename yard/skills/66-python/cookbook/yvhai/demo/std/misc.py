#!/usr/bin/env python3
# 杂项

import math
import glob
from timeit import Timer

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
        print(' -- math.log(1024, 2): ', math.log(1024, 2))
        print(' -- math.cos(math.pi / 4): ', math.cos(math.pi / 4))
        print(' -- math.asin(3 / 5): ', math.asin(3 / 5))

    @staticmethod
    def test_timer():
        _sec = MiscDemo.mark_section('Timer')
        print(Timer('t=a; a=b; b=t', 'a=1;b=2').timeit())
        print(Timer('a,b=b,a', 'a=1;b=2').timeit())

    @staticmethod
    def test_random():
        _sec = MiscDemo.mark_section('随机数测试')
        import random
        for x in range(3):
            print(' -- random.choice: ', random.choice(['mysql', 'mongo', 'redis', 'ssdb']))
        print(' -- random.sample(range(100), 10): ', random.sample(range(100), 10))
        print(' -- random.random(): ', random.random() )
        print(' -- int(random.random() * 100): ', int(random.random() * 100))
        print(' -- random.randrange(100): ', random.randrange(100))

    @staticmethod
    def enum_files():
        _sec = MiscDemo.mark_section('枚举文件列表')
        print(' -- glob.glob("*.py"): ', glob.glob('*.py'))
        print(' -- glob.glob("*.py", recursive=True): ', glob.glob('*.py', recursive=True))

    @staticmethod
    def demo(args=[]):
        MiscDemo.test_math()
        MiscDemo.test_timer()
        MiscDemo.test_random()
        MiscDemo.enum_files()
        pass
