#!/usr/bin/env python3
# 各种转换

from yvhai.demo.base import YHDemo
from pprint import pprint


class NumberConvertDemo(YHDemo):
    def __init__(self):
        super(NumberConvertDemo, self).__init__('NumberConvert')

    @classmethod
    def num2all(cls, num):
        res = {
            "num": num,
            "bin": bin(num),
            "oct": oct(num),
            "hex": hex(num)
        }
        return res

    @staticmethod
    def demo(args=[]):
        print(NumberConvertDemo.num2all(0b1000001))
        print(NumberConvertDemo.num2all(0o101))
        print(NumberConvertDemo.num2all(65))
        print(NumberConvertDemo.num2all(0x41))


class ToListDemo(YHDemo):
    def __init__(self):
        super(ToListDemo, self).__init__('ToList')

    @staticmethod
    def demo(args=[]):
        dict1 = {"name": "PRC", "date": "1949-10-01", "place": "北京天安门", "year": 30}


class DictExporter:
    @classmethod
    def export(cls, dict1):
        # 导出列表
        key_list = [k for k in dict1.keys()]
        value_list = [v for v in dict1.values()]
        # 导出元组
        key_tuple = tuple([k for k in dict1.keys()])
        value_tuple = tuple([v for v in dict1.values()])
        # 键值对
        vk_list = {v: k for k, v in dict1.items()}
        res = {
            "key_list": key_list,
            "value_list": value_list,
            "key_tuple": key_tuple,
            "value_tuple": value_tuple,
            "kv_list": dict1,
            "vk_list": vk_list
        }
        return res


class ConvertDemo(YHDemo):
    def __init__(self):
        super(ConvertDemo, self).__init__('Convert')

    @staticmethod
    def demo(args=[]):
        dict1 = {"name": "PRC", "date": "1949-10-01", "place": "北京天安门", "year": 30}
        NumberConvertDemo.demo(args)
        ToListDemo.demo(args)
        pprint(DictExporter.export(dict1))
