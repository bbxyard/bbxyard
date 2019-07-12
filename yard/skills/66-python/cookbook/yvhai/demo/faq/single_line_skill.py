#!/usr/bin/env python3
# single line skill

from yvhai.demo.base import YHDemo

"""
字符串操作技巧
"""




"""
列表操作技巧
"""


def show_list_faq():
    # 去重
    _seg = YHDemo.mark_section('show_list_faq')
    print(' -- [去重] list(set(list)): ', list(set([1, 2, 3, 'A', '2', 2, 'B', 1, 'A'])))
    # 排序
    print(" -- [排序] sorted(list): ", sorted(['dog', 'cat', 'panda', 'apple', 'beer', 'lion']))
    # 变换
    print(" -- [str2list] split(str): ", "1,2,3".split(","))


class StrFaqDemo(YHDemo):
    def __init__(self):
        super(StrFaqDemo, self).__init__('StrFaq')

    @classmethod
    def demo_str_transform(cls, s="hallo welt and Hello woRld"):
        _seg = YHDemo.mark_section('show_str_faq')
        print(" -- [原串]: ", s)
        print(" -- [首字母大写]: ", s.capitalize())
        print(" -- [全部大写]: ", s.upper())
        print(" -- [全部小写]: ", s.lower())
        print(" -- [所有单词首字母大写]: ", s.title())
        pass

    @classmethod
    def demo_str_type(cls, s=''):
        _seg = YHDemo.mark_section('字符串类型')
        res = dict()
        res['str'] = s
        res['isalnum'] = s.isalnum()
        res['isalpha'] = s.isalpha()
        res['isdecimal'] = s.isdecimal()
        res['isdigit'] = s.isdigit()
        res['isidentifier'] = s.isidentifier()
        res['islower'] = s.islower()
        res['isnumeric'] = s.isnumeric()
        res['isprintable'] = s.isprintable()
        res['isspace'] = s.isspace()
        res['istitle'] = s.istitle()
        res['isupper'] = s.isupper()
        print(" -- [类型判断] isxxx: ", res)


    @staticmethod
    def demo(args=[]):
        StrFaqDemo.demo_str_transform()
        StrFaqDemo.demo_str_type('1949')
        StrFaqDemo.demo_str_type('PRC')
        StrFaqDemo.demo_str_type('PR China')
        StrFaqDemo.demo_str_type('PR China 1949')


class SingleLineSkillDemo(YHDemo):
    def __init__(self):
        super(SingleLineSkillDemo, self).__init__('SingleLineSkill')

    @staticmethod
    def demo(args=[]):
        StrFaqDemo.demo()
        show_list_faq()
