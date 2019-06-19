#!/usr/bin/env python3
# 系统标准库测试

from yvhai.demo.std.os import OSDemo
from yvhai.demo.std.misc import MiscDemo
from yvhai.demo.std.shutil import ShUtilDemo


if __name__ == '__main__':
    OSDemo.demo()
    MiscDemo.demo()
    ShUtilDemo.demo()
