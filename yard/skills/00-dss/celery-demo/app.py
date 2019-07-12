#!/usr/bin/env python3

from celery_app import task1
from celery_app import task2

import time
import random

if __name__ == '__main__':
    for x in range(1, 10):
        task1.add.delay(3, 5)
        task2.multiply.delay(4, 5)
        print('[%d] main thread post: ' % x)
        time.sleep(2 + int(random.random() * 5))
    print('end')
