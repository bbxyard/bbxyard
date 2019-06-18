#!/usr/bin/env python3
# 数据库Demo


from yvhai.demo.db.mysql import MySQLDemo
from yvhai.demo.db.mongo import MongoDemo


def main():
    # MySQL、
    MySQLDemo.demo()
    # Mongo、Redis、SSDB
    MongoDemo.demo()


if __name__ == '__main__':
    main()
