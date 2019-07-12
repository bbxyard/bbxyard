#!/usr/bin/env python3
# 系统标准库测试

from yvhai.demo.std.os import OSDemo
from yvhai.demo.std.misc import MiscDemo
from yvhai.demo.std.shutil import ShUtilDemo
from yvhai.demo.std.sys import SysDemo
from yvhai.demo.std.datetime import DTDemo
from yvhai.demo.std.re import RegexDemo
from yvhai.demo.std.logging import LogerDemo
from yvhai.demo.std.str import StrDemo


if __name__ == '__main__':
    OSDemo.demo()
    MiscDemo.demo()
    ShUtilDemo.demo()
    SysDemo.demo()
    DTDemo.demo()
    RegexDemo.demo()
    LogerDemo.demo()
    StrDemo.demo()
