from collections import namedtuple as NT
from yvhai.demo.base import YHDemo

# 快速定义结构体
Movie = NT("Movie", ["name", "alias", "brief", "tags"])
User = NT("User", ["name", "age", "gender", "role"])


class NamedTupleDemo(YHDemo):
    def __init__(self):
        super(NamedTupleDemo, self).__init__("namedtuple")

    @classmethod
    def demo_basic(cls):
        _sec = cls.mark_section("类似结构体，基本用法")
        m1 = Movie("大话西游", "*", "一个神奇的故事", "西夏 沙湖 影视城")
        m2 = Movie("哈利波特", "Happy Potter", "魔幻", "UK Magic")
        print(" -- m1: ", type(m1), m1)
        print(" -- m2: ", type(m2), m2)
        # 拆包
        name, alias, *other = m2
        print(" -- unpack: ", name, alias, other)

    @classmethod
    def demo_extend_col(cls):
        _sec = cls.mark_section("原有数据表，增加一列演示")
        exist_row = ("boxu", 34, "male")
        exist_kv2 = {
            "name": "pufan66",
            "age": 24,
            "gender": "male"
        }
        user1 = User(*exist_row, "dev")
        dict1 = user1._asdict()
        user2 =
        print(" -- user1: ", user1.name, user1.age, user1.gender, user1.role)
        print(" -- dict1: ", dict1)

    @classmethod
    def test_args(cls, *args, **kwargs):
        print("args: ", args)
        print("kwargs: ", kwargs)

    @classmethod
    def demo(cls, args=[]):
        cls.demo_basic()
        cls.demo_extend_col()
        cls.test_args("arg1", "arg2", "arg3")
        cls.test_args(arg1="参数1", arg2=1024, arg3="参数3", argn="参数n")
        cls.test_args("arg1", "arg2", "arg3", kv1="kv1", kv2="kv2")
