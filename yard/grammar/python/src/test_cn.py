#!/usr/bin/python
#-*- coding: utf-8 -*-
import os,sys

reload(sys)
sys.setdefaultencoding("utf8")

#. 中文测试
def cn_test():
    s0 = "中国"
    print "%s: len=%d" % (s0, len(s0))
    s = u"中国"
    print "%s: len=%d" % (s.encode("utf8"), len(s))
    ws = s.encode("utf8")
    print "%s: len=%d" % (ws, len(ws))
    print "hello world"
    pass

#. main
if __name__ == "__main__":
    cn_test()