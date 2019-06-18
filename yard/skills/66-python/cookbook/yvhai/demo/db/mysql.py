#!/usr/bin/env python3
# MySQL 数据管理Demo

import mysql.connector
import pymysql

from yvhai.demo.base import YHDemo

# SQL语句
SQL_EXIST_DB  = '''
    SELECT * from information_schema.SCHEMATA where SCHEMA_NAME='{0}'
'''
SQL_CREATE_DB = '''
    CREATE DATABASE {0} DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
'''
SQL_DESTROY_DB = '''
    DROP DATABASE {0}
'''

SQL_CREATE_TABLE = '''
    CREATE TABLE yh_etc_passwd (
         id  INT NOT NULL,
         user CHAR(64),
         uid INT,
         gid INT,  
         home CHAR(128),
         brief CHAR(128)
    )
'''
SQL_DESTROY_TABLE = '''  '''



'''
官文库: mysql-connector
'''


class MySQLOrgDemo(YHDemo):
    def __init__(self):
        super(MySQLOrgDemo, self).__init__('MySQLConnector')
        self.client = None
        self.db = None

    def connect(self, params):
        self.client = mysql.connector.connect(
            host=params['host'],
            user=params['user'],
            passwd=params['passwd']
        )

    def list_all_dbs(self):
        cursor = self.client.cursor()
        cursor.execute('show databases;')
        for x in cursor:
            print(x)

    # 判断数据库是否已经存在
    def verify_exist_db(self, db_name):
        sql_exist_db = SQL_EXIST_DB.format(db_name)
        cursor = self.client.cursor()
        res = cursor.execute(sql_exist_db)
        # 先来个搓办法
        cnt = 0
        for it in cursor:
            cnt += 1
        return cnt > 0

    # 创建数据库
    def create_db(self, db_name):
        # 判定存在性, 确认为空，方可创建
        if not self.verify_exist_db(db_name):
            sql_create_db = SQL_CREATE_DB.format(db_name)
            cursor = self.client.cursor()
            res = cursor.execute(sql_create_db)
            # 拉出来溜溜
            self.list_all_dbs()

    # 删除数据库
    def destory_db(self, db_name):
        sql_destory_db = SQL_DESTROY_DB.format(db_name)
        self.client.cursor().execute(sql_destory_db)
        # 确认一下
        self.list_all_dbs()


class PyMySQLDemo(YHDemo):
    def __init__(self):
        super(PyMySQLDemo, self).__init__('PyMySQL')


class MySQLDemo(YHDemo):
    def __init__(self):
        super(MySQLDemo, self).__init__('MySQL')

    def connect(self):
        pass

    def insert(self, rows):
        pass

    @staticmethod
    def demo(args=[]):
        # 获得配置信息
        user = MySQLDemo.get_conf_value('MYSQL_USER', 'root')
        passwd = MySQLDemo.get_conf_value('MYSQL_PSWD', '')
        db_name = MySQLDemo.get_conf_value('DB_NAME', 'yh_os_fs')
        tab_name = MySQLDemo.get_conf_value('DB_TAB_NAME', 'etc_passwd')

        con_params = {'host': 'localhost', 'user': user, 'passwd': passwd}

        # mysql-connector 测试
        m1 = MySQLOrgDemo()
        m1.connect(con_params)
        m1.create_db(db_name)
        m1.destory_db(db_name)

        x = MySQLDemo()
        print(x)
