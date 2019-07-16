from yvhai.demo.base import YHDemo
from collections import deque


class DequeDemo(YHDemo):
    def __init__(self):
        super(DequeDemo, self).__init__("deque")

    @classmethod
    def test_num(cls):
        d = deque(maxlen=3)
        for x in range(10):
            d.append(x)
            print(d)

    @classmethod
    def test_read_file(cls):
        q = deque(maxlen=4)
        with open("/etc/passwd", "r") as f:
            while True:
                line = f.readline().strip('\n')
                if not line:
                    break;
                q.append(line)
                # print(q)
        print(q)

    @staticmethod
    def demo(args=[]):
        DequeDemo.test_num()
        DequeDemo.test_read_file()
