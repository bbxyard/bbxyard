#!/usr/bin/env python3
# string demo


from yvhai.demo.base import YHDemo


class StrDemo(YHDemo):
    def __init__(self):
        super(StrDemo, self).__init__('String')

    @classmethod
    def convert(cls):
        _sec = StrDemo.mark_section('场景-字符串规整')
        s = ' hallo welt '.strip()
        print(' -- s: ', s)
        print(' -- s.strip(): ', s.strip())
        print(' -- s.capitalize(): ', s.capitalize())
        print(' -- s.upper(): ', s.upper())
        print(' -- s.title(): ', s.title())
        print(' -- s.lower(): ', s.title().lower())

    @staticmethod
    def demo(args=[]):
        StrDemo.convert()
