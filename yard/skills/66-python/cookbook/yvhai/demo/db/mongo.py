#!/usr/bin/env python3
# mongodb 管理类

import pymongo

from yvhai.demo.base import YHDemo
from yvhai.demo.input.os_data import OSData


class MongoDemo(YHDemo):
    def __init__(self):
        super(MongoDemo, self).__init__('Mongo')

    def connect(self, params):
        self.client = pymongo.MongoClient('localhost', 27017)
        self.db = self.client[params['db']]

    def show_info(self):
        dblist = self.client.list_database_names()
        print('dblist: ', dblist)

    def write(self, tab_name, rows):
        tab = self.db[tab_name]
        # idx = pymongo.IndexModel([('uid', pymongo.ASCENDING)], unique=True)
        # tab.create_index([idx])

        mid = int(len(rows) / 2)
        left_rows = rows[0:mid]
        right_rows = rows[mid:]

        # 单个插入
        left_ids = []
        for row in left_rows:
            document = row
            document['_id'] = row['uid']
            id = tab.insert_one(document)
            left_ids.append(id)
        # 批量插入
        right_ids = tab.insert_many(right_rows)

        # 结果信息
        tab_name_list = self.db.list_collection_names()
        print('left_ids: ', left_ids)
        print('right_ids: ', right_ids)
        print('tablist: ', tab_name_list)

    @staticmethod
    def demo(args=[]):
        rows = OSData.parse_passwd_file('dict')
        m = MongoDemo()
        m.connect({'db': 'yh_os_fs'})
        m.show_info()
        m.write('etc_passwd_2', rows)
        print(m)
