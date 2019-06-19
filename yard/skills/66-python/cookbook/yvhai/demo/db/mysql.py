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
SQL_TABLE_DELETE = '''DELETE FROM yh_etc_passwd WHERE uid > 200'''
SQL_TABLE_SELECT = ''' '''


'''
官文库: mysql-connector
'''


class MySQLMan(YHDemo):
    def __init__(self, name):
        super(MySQLMan, self).__init__(name)
        self.client = None
        self.db = None
        self.db_name = ''

    def list_all_dbs(self):
        for x in self._run_sql('show databases;'):
            print(x)

    # 判断数据库是否已经存在
    def verify_exist_db(self):
        cnt = 0
        for it in self._run_sql(SQL_EXIST_DB.format(self.db_name)):
            cnt += 1
        return cnt > 0

    # 确保数据表存在
    def verify_table(self):
        self._run_sql(SQL_TABLE_CREATE)

    # 创建数据库
    def auto_create_db(self):
        # 判定存在性, 确认为空，方可创建
        if not self.verify_exist_db():
            cursor = self._run_sql(SQL_CREATE_DB.format(self.db_name))
            # 拉出来溜溜
            self.list_all_dbs()
        # 切换为当前数据库
        self._run_sql(SQL_USE_DB.format(self.db_name))
        # 确保数据表存在
        self.verify_table()

    # 删除数据库
    def destroy_db(self):
        self._run_sql(SQL_DESTROY_DB.format(self.db_name))
        # 确认一下
        self.list_all_dbs()

    # 插入数据
    def upsert(self, rows):
        cnt = 0
        try:
            # 执行sql语句
            for row in rows:
                values = (row['name'], row['uid'], row['gid'], row['home'], row['shell'], row['brief'])
                cursor = self._run_sql(SQL_TABLE_INSERT, values)
                cnt += cursor.rowcount
            # 提交到数据库执行
            self.client.commit()
        except:
            # 如果发生错误则回滚
            self.client.rollback()
            cnt = 0
        print("共计插入 - %d - 条记录" % cnt)
        return cnt

    # 执行一条SQL语句
    def _run_sql(self, sql, values=None):
        # 使用 cursor() 方法创建一个游标对象 cursor
        cursor = self.client.cursor()
        # 使用 execute()  方法执行 SQL 查询
        sql = sql.strip('\n').strip()
        if values:
            cursor.execute(sql, values)
        else:
            cursor.execute(sql)
        return cursor


class MySQLOrgDemo(MySQLMan):
    def __init__(self):
        super(MySQLOrgDemo, self).__init__('MySQLConnector')
        self.client = None
        self.db = None
        self.db_name = ''

    def connect(self, params):
        self.db_name = params['database']
        self.client = mysql.connector.connect(
            host=params['host'],
            user=params['user'],
            passwd=params['passwd']
        )
        # 确保数据库存在
        self.auto_create_db()


class PyMySQLDemo(MySQLMan):
    def __init__(self):
        super(PyMySQLDemo, self).__init__('PyMySQL')

    def __del__(self):
        if self.client:
            # 关闭数据库连接
            self.client.close()
            self.client = None

    def connect(self, params):
        self.db_name = params['database']
        self.client = pymysql.connect(params['host'], params['user'], params['passwd'], '')
        # 确保数据库存在
        self.auto_create_db()

    def _fetch_one(self, sql):
        return self._run_sql(sql)


class MySQLDemo(MySQLMan):
    def __init__(self):
        super(MySQLDemo, self).__init__('MySQL')

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
        con_params = {'host': 'localhost', 'user': user, 'passwd': passwd, 'database': db_name}

        # for m in [PyMySQLDemo()]:
        for m in (MySQLOrgDemo(), PyMySQLDemo()):
            m.connect(con_params)
            m.upsert(rows)
            m.destroy_db()

        x = MySQLDemo()
        print(x)
