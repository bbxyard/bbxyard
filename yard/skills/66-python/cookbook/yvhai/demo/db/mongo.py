#!/usr/bin/env python3
# mongodb 管理类

import pymongo

from yvhai.demo.base import YHDemo
from yvhai.demo.input.os_data import OSData


class MongoDemo(YHDemo):
    def __init__(self):
        super(MongoDemo, self).__init__('Mongo')
        self.client = None
        self.db = None

    def connect(self, params):
        self.client = pymongo.MongoClient('localhost', 27017)
        self.db = self.client[params['db']]

    def show_info(self, tab_name):
        dblist = self.client.list_database_names()
        print('dblist: ', dblist)
        # 遍历表
        tab = self.db[tab_name]
        MongoDemo.visit_cursor('整表数据, name降序: ', tab.find().sort('name', 1))

    def query(self, tab_name):
        tab = self.db[tab_name]
        # 字段包含、排除
        MongoDemo.visit_cursor('仅显示uid, gid字段, ASC',
                               tab.find({'_id': {'$gt': 100}}, {'uid': 1, 'name': 1}).limit(3).sort('name', 1))
        MongoDemo.visit_cursor('不显示gid字段, DESC', tab.find({'name': 'daemon'}, {'gid': 0}).limit(5).sort('name', -1))
        # 查询-字典序
        # MongoDemo.visit_cursor('查询name<z', tab.find({'name': {'$lt', 'z'}}))
        # 查询-数值
        MongoDemo.visit_cursor('查询uid=0', tab.find({'uid': 0}))
        MongoDemo.visit_cursor('查询uid<=10, 降序', tab.find({'uid': {'$lte': 10}}).sort('name', -1))
        # 查询-正则
        MongoDemo.visit_cursor('查询-正则', tab.find({'name': {'$regex': '^[a-z]'}}))

    @staticmethod
    def visit_cursor(subject, cursor):
        print(subject)
        x = 0
        for it in cursor:
            print('%04d, item: ' % x, it)
            x += 1

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
            id = tab.insert_one(row).inserted_id
            left_ids.append(id)
        # 批量插入
        right_ids = tab.insert_many(right_rows)

        # 结果信息
        tab_name_list = self.db.list_collection_names()
        print('left_ids: ', left_ids)
        print('right_ids: ', right_ids.inserted_ids)
        print('tablist: ', tab_name_list)

    def write_or_update(self, tab_name, rows):
        tab = self.db[tab_name]
        res = []
        for row in rows:
            cursor = tab.find({'_id': row['_id']}, {'_id': 1})
            if cursor.count() > 0:
                r = tab.update_one({'_id': row['_id']}, {'$set': row})
                res.append((r.upserted_id, r.matched_count, r.modified_count))
            else:
                r = tab.insert_one(row)
                res.append((r.inserted_id, 1, 1))
        print('write_or_update result: ', res)

    def write_by_upsert(self, tab_name, rows):
        tab = self.db[tab_name]
        res = []
        for row in rows:
            r = tab.update_one({'_id': row['_id']}, {'$set': row}, upsert=True)
            res.append((r.upserted_id, r.matched_count, r.modified_count))
        print('write_by_upsert: ', res)

    def update(self, tab_name, cond, patch_item):
        tab = self.db[tab_name]
        # 查询、更新、再查
        MongoDemo.visit_cursor('更新前: ', tab.find(cond))
        tab.update_one(cond, patch_item)
        MongoDemo.visit_cursor('更新后: ', tab.find(cond))

    @staticmethod
    def demo(args=[]):
        tab_name = 'etc_passwd_8'

        # 文档化(加入_id)
        rows = OSData.parse_passwd_file('dict')
        for row in rows:
            row['_id'] = row['uid']

        # 写入数据
        m = MongoDemo()
        m.connect({'db': 'yh_os_fs'})
        # m.write(tab_name, rows)
        m.write_or_update(tab_name, rows)
        m.write_by_upsert(tab_name, rows)

        # 查询数据
        m.show_info(tab_name)
        m.query(tab_name)
        # 测试更新
        m.update(tab_name, {'uid': 0}, {'$set': {'name': 'admin'}})
        m.update(tab_name, {'_id': 0}, {'$set': {'name': 'root'}})
        print(m)
