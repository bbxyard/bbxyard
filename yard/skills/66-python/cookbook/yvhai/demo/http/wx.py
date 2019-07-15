#!/usr/bin/env python3

# 公众号文章下载

import requests
import json
import time
from pymongo import MongoClient

url = 'http://mp.weixin.qq.com/mp/profile_ext'

# Mongo配置
conn = MongoClient('127.0.0.1', 27017)
db = conn.wx  #连接wx数据库，没有则自动创建
mongo_wx = db.article  #使用article集合，没有则自动创建


def get_wx_article(meta_info, index=0, count=10):
    offset = (index + 1) * count
    params = {
        '__biz': meta_info['biz'],
        'uin': meta_info['uin'],
        'key': meta_info['key'],
        'offset': offset,
        'count': count,
        'action': 'getmsg',
        'f': 'json'
    }

    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36'
    }

    response = requests.get(url=url, params=params, headers=headers)
    resp_json = response.json()
    if resp_json.get('errmsg') == 'ok':
        resp_json = response.json()
        # 是否还有分页数据， 用于判断return的值
        can_msg_continue = resp_json['can_msg_continue']
        # 当前分页文章数
        msg_count = resp_json['msg_count']
        general_msg_list = json.loads(resp_json['general_msg_list'])
        list = general_msg_list.get('list')
        print(list, "**************")
        for i in list:
            app_msg_ext_info = i['app_msg_ext_info']
            # 标题
            title = app_msg_ext_info['title']
            # 文章地址
            content_url = app_msg_ext_info['content_url']
            # 封面图
            cover = app_msg_ext_info['cover']

            # 发布时间
            datetime = i['comm_msg_info']['datetime']
            datetime = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(datetime))

            mongo_wx.insert({
                'wx_name': meta_info["wx_name"],
                'wx_id': meta_info["wx_id"],
                'title': title,
                'content_url': content_url,
                'cover': cover,
                'datetime': datetime
            })
        if can_msg_continue == 1:
            return True
        return False
    else:
        print('获取文章异常...')
        return False


if __name__ == '__main__':
    meta_info = {
        "wx_name": "Python3X",
        "wx_id": "python3xxx",
        "biz": "Mzg4MTA2Nzg0NA==",
        "uin": "MTEyNzgzMjU4Ng==",
        "key": "2dfd68df0f968e754e9f4b66f39407d798297df8859d40c12ecc2a454d267bf041bdc6cf7c53e716bfab3f91b1281bfb96396d001a1c84cf3b28b73553980777400468bc2ab8b33306fd88f10fb6605d"
    }
    index = 0
    while 1:
        print(f'开始抓取公众号第{index + 1} 页文章.')
        flag = get_wx_article(meta_info, index=index)
        # 防止和谐，暂停8秒
        time.sleep(8)
        index += 1
        if not flag:
            print('公众号文章已全部抓取完毕，退出程序.')
            break

        print(f'..........准备抓取公众号第{index + 1} 页文章.')
