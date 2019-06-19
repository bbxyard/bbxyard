#!/usr/bin/env python3
# datetime

import datetime

from datetime import date
from datetime import time

from yvhai.demo.base import YHDemo


class DTDemo(YHDemo):
    def __init__(self):
        super(DTDemo, self).__init__('datetime')

    # 语义化时间
    @classmethod
    def show_time(self):
        _sec = DTDemo.mark_section('语义化时间')
        today = datetime.date.today()
        print(' -- datetime.date.today(): ', datetime.date.today())
        print(' -- datetime.date.today() - datetime.timedelta(days=1): ', datetime.date.today() - datetime.timedelta(days=1) )
        print(' -- datetime.time.hour datetime.time.minute datetime.time.second: ', datetime.time.hour, datetime.time.minute, datetime.time.second)
        print(' -- datetime.date.today().strftime("%Y.%m.%d"): ', datetime.date.today().strftime('%Y.%m.%d'))
        # print(datetime.time.strftime('%H.%M.%S'))
        # 格里高利
        print(' -- 格里高利历(公历)修订时间 datetime.date(1582, 10, 4): ', datetime.date(1582, 10, 4))
        print(' -- Bug datetime.date(1582, 10, 15) - datetime.date(1582, 10, 4): ', date(1582, 10, 15) - date(1582, 10, 4))
        # 流逝
        print(' -- now - birthday: %d days' % (date.today() - date(1985, 7, 18)).days)

    @classmethod
    def demo(args=[]):
        DTDemo.show_time()
