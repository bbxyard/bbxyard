#!/usr/bin/env python3
# MySQL 数据管理Demo

import mysql.connector
import pymysql

from yvhai.demo.base import YHDemo
from yvhai.demo.input.os_data import OSData


# SQL语句

# # 数据库管理
SQL_EXIST_DB = '''
    SELECT * from information_schema.SCHEMATA where SCHEMA_NAME='{0}'
'''
SQL_CREATE_DB = '''
    CREATE DATABASE {0} DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
'''
SQL_DESTROY_DB = ''' DROP DATABASE {0} '''
SQL_USE_DB = ''' USE {0} '''

# # 表管理
SQL_TABLE_CREATE = '''
    CREATE TABLE IF NOT EXISTS yh_etc_passwd (
         id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         name CHAR(64) NOT NULL default '',
         uid INT NOT NULL default 0,
         gid INT NOT NULL default 0,
         home CHAR(128) NOT NULL default '',
         shell CHAR(128) NOT NULL default '',
         brief CHAR(128) NOT NULL default '',
         ctime timestamp not null default current_timestamp
    )
'''
SQL_TABLE_DESTROY = '''  '''

# # 增删改查
SQL_TABLE_INSERT = "INSERT INTO yh_etc_passwd (name, uid, gid, home, shell, brief) VALUES (%s, %s, %s, %s, %s, %s) "
SQL_TABLE_UPDATE = ''' '''
SQL_TABLE_DELETE = ''' '''
SQL_TABLE_SELECT = ''' '''


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
        # cnt = cursor.rowcount
        cnt = 0
        for x in cursor: cnt += 1
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
        # 切换为当前数据库
        self.client.cursor().execute(SQL_USE_DB.format(db_name))
        # 确保数据表存在
        self.verify_table()

    # 删除数据库
    def destory_db(self, db_name):
        sql_destory_db = SQL_DESTROY_DB.format(db_name)
        self.client.cursor().execute(sql_destory_db)
        # 确认一下
        self.list_all_dbs()

    # 确保数据表存在
    def verify_table(self):
        self.client.cursor().execute(SQL_TABLE_CREATE)

    # 插入数据
    def upsert(self, rows):
        cnt = 0
        for row in rows:
            values = (row['name'], row['uid'], row['gid'], row['home'], row['shell'], row['brief'])
            cursor = self.client.cursor()
            cursor.execute(SQL_TABLE_INSERT, values)
            cnt += cursor.rowcount
        self.client.commit()  # 数据表内容有更新，必须使用到该语句
        print("共计插入 - %d - 条记录" % cnt)


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
        # tab_name = MySQLDemo.get_conf_value('DB_TAB_NAME', 'etc_passwd')

        # 数据准备
        rows = OSData.parse_passwd_file('dict')

        # 汇编-连接参数
        con_params = {'host': 'localhost', 'user': user, 'passwd': passwd}

        # mysql-connector 测试
        m1 = MySQLOrgDemo()
        m1.connect(con_params)
        m1.create_db(db_name)
        m1.upsert(rows)
        m1.destory_db(db_name)

        x = MySQLDemo()
        print(x)
