#!/usr/bin/python
# -*- coding: UTF-8 -*-

import jieba;

import sys;
reload(sys);
sys.setdefaultencoding("utf8")

print "你好，世界";

ustr = u"你好，世界, UTF8"
print ustr.encode("utf8");

seg_list = jieba.cut("我来到北京清华大学", cut_all=True, HMM=False)
# print "/ ".join(seg_list).decode("gbk")
