# 实现multdict

from collections import defaultdict, OrderedDict
from yvhai.demo.base import YHDemo


class OrderedDictDemo(YHDemo):
    """OrderedDict"""

    def __init__(self):
        super(OrderedDictDemo, self).__init__("OrderDict")

    @staticmethod
    def demo(args=[]):
        od = OrderedDict()
        od['foo'] = 1
        od['bar'] = 2
        od['barfoo'] = 3
        od['foobar'] = 4
        print(od)


class DefaultDictDemo(YHDemo):
    """multdict"""

    def __init__(self):
        super(DefaultDictDemo, self).__init__('DefaultDict')

    @staticmethod
    def demo_mult_dict_by_set():
        md = defaultdict(set)
        md["apple"].add("苹果")
        md["apple"].add("iPhone")
        md["apple"].add("乔布斯")
        md["windows"].add("窗户")
        md["windows"].add("MS")
        md["windows"].add("Bill Gates")
        return md

    @staticmethod
    def demo_mult_dict_by_list():
        md1 = DefaultDictDemo.demo_mult_dict_by_set()
        md2 = defaultdict(list)
        for key, vset in md1.items():
            for value in vset:
                md2[key].append(value)
        print(md1)
        print(md2)

    @staticmethod
    def demo(args=[]):
        DefaultDictDemo.demo_mult_dict_by_set()
        DefaultDictDemo.demo_mult_dict_by_list()


class WordCount(YHDemo):
    """词频统计"""

    @classmethod
    def stat_by_dict(cls, word_list):
        cls.mark_section("WC: 通过d.setdefault, 规避判空")
        d = {}
        for word in word_list:
            d.setdefault(word, 0)
            d[word] += 1
        print(d)

    @classmethod
    def stat_by_default_dict(cls, word_list):
        cls.mark_section("WC: 通过defaultdict")
        d = defaultdict(int)
        for word in word_list:
            d[word] += 1
        print(d)

    @classmethod
    def demo(cls):
        word_list = ["Linux", "Windows", "MacOS", "Linux", "Linux", "Cygwin", "MacOS", "Windows"]
        cls.stat_by_dict(word_list)
        cls.stat_by_default_dict(word_list)


class NestedDict(YHDemo):
    """嵌套dict"""

    @classmethod
    def gen_default(cls):
        return {"name": "", "nums": 0}

    @classmethod
    def demo(cls, args=[]):
        d = defaultdict(cls.gen_default)
        d["group1"]["name"] = "g1"
        d["group1"]["nums"] += 1
        d["group1"]["nums"] += 1
        d["group2"]["name"] = "g2"
        d["group2"]["nums"] += 10
        d["group2"]["nums"] += 1
        print(d)


class DictDemo(YHDemo):
    def __init__(self):
        super(DictDemo, self).__init__('Dict')

    @staticmethod
    def demo(args=[]):
        OrderedDictDemo.demo()
        DefaultDictDemo.demo()
        WordCount.demo()
        NestedDict.demo()
