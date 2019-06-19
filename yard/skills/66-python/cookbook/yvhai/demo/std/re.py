#!/usr/bin/env python3
# 正则


import re

from yvhai.demo.base import YHDemo


class RegexDemo(YHDemo):
    def __init__(self):
        super(RegexDemo, self).__init__('Regex')

    @staticmethod
    def test_match():
        _sec = RegexDemo.mark_section('re.match用法')
        print(' ==> re.match 尝试从字符串的起始位置匹配一个模式，如果不是起始位置匹配成功的话，match()就返回none。')
        print(re.match('www', 'www.yvhai.com').span())  # 在起始位置匹配
        print(re.match('www', 'www.yvhai.com'))  #
        print(re.match('com', 'www.yvhai.com'))  # 不在起始位置匹配

        line = "Cats are smarter than dogs"
        res_match = re.match(r'(.*) are (.*?) .*', line, re.M | re.I)
        if res_match:
            print(' -- res_match.group(): ', res_match.group())
            print(' -- res_match.group(1): ', res_match.group(1))
            print(' -- res_match.group(2): ', res_match.group(2))
        else:
            print(' -- No Match!')

    @staticmethod
    def test_search():
        _sec = RegexDemo.mark_section('re.search用法')
        print(' ==> re.search 扫描整个字符串并返回第一个成功的匹配。')
        print(re.search('www', 'www.yvhai.com').span())  # 在起始位置匹配
        print(re.search('www', 'www.yvhai.com').span())  #
        print(re.search('com', 'www.yvhai.com').span())  # 不在起始位置匹配

        line = "Cats are smarter than dogs"
        res_search = re.search(r'(.*) are (.*?) .*', line, re.M | re.I)
        if res_search:
            print(' -- res_search.group(): ', res_search.group())
            print(' -- res_search.group(1): ', res_search.group(1))
            print(' -- res_search.group(2): ', res_search.group(2))
        else:
            print(' -- Noting Found!')

    @staticmethod
    def test_match_vs_search():
        _sec = RegexDemo.mark_section('re.match vs re.search')
        print('re.match只匹配字符串的开始，如果字符串开始不符合正则表达式，则匹配失败，函数返回None；而re.search匹配整个字符串，直到找到一个匹配。')
        line = "Cats are smarter than dogs";
        res_match = re.match(r'dogs', line, re.M | re.I)
        res_search = re.search(r'dogs', line, re.M | re.I)

        if res_match:
            print(' - matched --> res_match.group(): ', res_match.group())
        else:
            print('No Match!')

        if res_search:
            print(' - searched --> res_search.group(): ', res_search.group())
        else:
            print('Nothing Found!')

    @staticmethod
    def test_sub():
        _sec = RegexDemo.mark_section('正则替换')
        phone = "166-7554-6320 # Das ist Mine Phone"
        num1 = re.sub(r'#.*$', '', phone)  # 删除注释
        num2 = re.sub(r'\D', '', phone)  # 删除非数字
        print(" -- 电话号码(原始): ", phone)
        print(" -- 电话号码(删除注释): ", num1)
        print(" -- 电话号码(删除非数字): ", num2)

    @staticmethod
    def test_sub_by_fun():
        _sec = RegexDemo.mark_section('正则替换-函数')

        # 将匹配的数字扩大2倍
        def double(matched):
            value = int(matched.group('value'))
            return str(value * 2)

        s = 'A23G4HFD567'
        print(' -- 原串：', s)
        print(' -- 扩大2倍', re.sub('(?P<value>\d+)', double, s))

    @staticmethod
    def test_replace():
        _sec = RegexDemo.mark_section('正则测试')
        print(re.findall(r'\bf[a-z]*', 'which foot or hand fell fastest'))
        print(re.sub(r'(\b[a-z]+) \1', r'\1', 'cat in the the hat'))
        print('tea for too'.replace('too', 'two'))

    @staticmethod
    def test_group():
        _sec = RegexDemo.mark_section('正则编译及分组测试-1')
        pattern = re.compile(r'\d+')
        str = 'one12twothree34four'
        print(' -- 原串: ', str)
        print(' -- pattern.match(str)): ', pattern.match(str))
        print(' -- pattern.match(str, 2, 10)): ', pattern.match(str, 2, 10))
        print(' -- pattern.match(str, 3, 10)): ', pattern.match(str, 3, 10))
        res_match = pattern.match(str, 3, 10)
        print(' -- group(), start(), end(), span(): ', res_match.group(), res_match.start(), res_match.end(),
              res_match.span())

    @staticmethod
    def test_group2():
        _sec = RegexDemo.mark_section('正则编译及分组测试-2')
        pattern = re.compile(r'([a-z]+) ([a-z]+)', re.I)
        str = 'Hello World Wide Web'
        m = pattern.match(str)
        print(' -- 原串: ', str)
        print(' -- res.group()/res.group(0)/res.group(1)/res.group(2): %s/%s/%s/%s' % (
        m.group(), m.group(0), m.group(1), m.group(2)))
        print(' -- res.span()/res.span(0)/res.span(1)/res.span(2): %s/%s/%s/%s' % (
        m.span(), m.span(0), m.span(1), m.span(2)))
        print(' -- res.start()/res.start(0)/res.start(1)/res.start(2): %s/%s/%s/%s' % (
        m.start(), m.start(0), m.start(1), m.start(2)))
        print(' -- res.end()/res.end(0)/res.end(1)/res.end(2): %s/%s/%s/%s' % (m.end(), m.end(0), m.end(1), m.end(2)))

    @staticmethod
    def test_findall():
        _sec = RegexDemo.mark_section('匹配所有findall/finditer')
        str = '格里高利历(公历)修订时间 1582年10月4日 -> 1582年10月15日'
        print(' -- 原串: ', str)
        print(' -- 查找所有数字：', re.compile(r'\d+').findall(str))
        print(' -- 查找所有非数字：', re.compile(r'\D+').findall(str))
        print(' -- 使用迭代器: ')
        for m in re.compile(r'\d+').finditer(str):
            print('\t', m.group())

    @staticmethod
    def test_split():
        _sec = RegexDemo.mark_section('split')
        str = 'runoob, runoob; runoob.'
        print(' -- 原串: ', str)
        print(' -- 切分##\W+##: ', re.compile(r'\W+').split(str))
        print(' -- 切分##\W+##: ', re.split(r'\W+', str))
        print(' -- 切分##(\W+)##: ', re.compile(r'(\W+)').split(str))
        print(' -- 切分##\W+#1#: ', re.split(r'\W+', str, 1))
        print(' -- 切分##\W+#2#: ', re.split(r'\W+', str, 2))
        print(' -- 切分##\W+#3#: ', re.split(r'\W+', str, 3))
        print(' -- 切分##o*##: ', re.split(r'o*', str))
        print(' -- 切分##oo*##: ', re.split(r'oo*', str))
        print(' -- 不切分####: ', re.split(r'oZo*', str))

    @staticmethod
    def demo(args=[]):
        RegexDemo.test_match()
        RegexDemo.test_search()
        RegexDemo.test_match_vs_search()
        RegexDemo.test_sub()
        RegexDemo.test_sub_by_fun()
        RegexDemo.test_group()
        RegexDemo.test_group2()
        RegexDemo.test_findall()
        RegexDemo.test_split()
        RegexDemo.test_replace()
