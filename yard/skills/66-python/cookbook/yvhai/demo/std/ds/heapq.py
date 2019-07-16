import heapq
import random
from yvhai.demo.base import YHDemo


class PriorityQueue(object):
    def __init__(self):
        self._queue = []
        self._index = 0

    def push(self, item, weight):
        heapq.heappush(self._queue, (weight, self._index, item))
        self._index += 1

    def pop(self):
        if len(self._queue) == 0:
            return None
        item = self._queue[-1]
        heapq.heappop(self._queue)
        return item

    def display(self):
        print(self._queue)


class HeapqDemo(YHDemo):
    def __init__(self):
        super(HeapqDemo, self).__init__("heapq")

    @staticmethod
    def demo(args=[]):
        xr = [{"name": random.choice(["Apple", "Banana", "Chilly", "Duck"]), "age": random.randrange(18, 72)} for i in
              range(10)]
        q = PriorityQueue()
        # 入队
        for i in range(10):
            age = random.randrange(18, 75)
            q.push({"name": "foobar-{}".format(i), "age": age}, age)
        q.display()

        # 出队
        out_list = []
        while True:
            item = q.pop()
            if not item:
                break
            out_list.append(item)
        print(out_list)
