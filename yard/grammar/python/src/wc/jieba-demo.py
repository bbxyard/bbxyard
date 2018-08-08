#!/usr/bin/env python
# -*- coding:utf-8 -*-

import jieba;
import sys;
reload(sys);
sys.setdefaultencoding("utf8")

# jieba.cut的默认参数只有三个,jieba源码如下
# cut(self, sentence, cut_all=False, HMM=True)
# 分别为:输入文本 是否为全模式分词 与是否开启HMM进行中文分词
def print_cn_list(list, prefix = ""):
  print prefix + "/ ".join(list).encode('utf8')

def quick_demo():
  seg_list = jieba.cut("我来到北京清华大学", cut_all=True, HMM=False)
  print_cn_list(seg_list, "Full Mode: ") # 全模式

  seg_list = jieba.cut("我来到北京清华大学", cut_all=False, HMM=True)
  print_cn_list(seg_list, "Default Mode: ") # 默认模式

  seg_list = jieba.cut("他来到了网易杭研大厦", HMM=False)
  print_cn_list(seg_list)

  seg_list = jieba.cut_for_search("小明硕士毕业于中国科学院计算所，后在日本京都大学深造", HMM=False)  # 搜索引擎模式
  print_cn_list(seg_list, "cut_for_search: ")


def main():
  quick_demo()


if __name__ == '__main__':
  main()
