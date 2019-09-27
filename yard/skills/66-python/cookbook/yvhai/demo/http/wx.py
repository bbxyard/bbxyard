#!/usr/bin/env python3

# 公众号文章下载

import os
import requests
import json
import time
import hashlib
from pymongo import MongoClient
from yvhai.res.http.header import get_random_user_agent

url = 'http://mp.weixin.qq.com/mp/profile_ext'

# Mongo配置
conn = MongoClient('127.0.0.1', 27017)
db = conn.wx  # 连接wx数据库，没有则自动创建
mongo_wx = db.article  # 使用article集合，没有则自动创建


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

            md5 = hashlib.md5((meta_info["wx_id"] + "|" + title).encode(encoding="utf8")).hexdigest()
            mongo_wx.insert({
                'wx_name': meta_info["wx_name"],
                'wx_id': meta_info["wx_id"],
                'title': title,
                'md5': md5,
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


"""
获取公众号，所有文章列表，并存入数据库
"""


def fetch_list():
    # meta_info = {
    #     "wx_name": "Python3X",
    #     "wx_id": "python3xxx",
    #     "biz": "Mzg4MTA2Nzg0NA==",
    #     "uin": "MTEyNzgzMjU4Ng==",
    #     "key": "2dfd68df0f968e754e9f4b66f39407d798297df8859d40c12ecc2a454d267bf041bdc6cf7c53e716bfab3f91b1281bfb96396d001a1c84cf3b28b73553980777400468bc2ab8b33306fd88f10fb6605d"
    # }
    meta_info = {
        "wx_name": "跨境法律服务专业智汇",
        "wx_id": "Cross-borderLegal",
        "biz": "MzU2NTQ5NjU0Ng==",
        "uin": "MTEyNzgzMjU4Ng==",
        "key": "56aa8a3ff1cbe0d9a563d2dcf05d9574e797a3fd15420844f2065249eef41a0f3df963415690f28ccc958ab545f7b1eea0a857bfe558d5b074573cf3977c0727beaeaeaaface54b6557208a5f8cd0181"
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


def download_all():
    # 输出目录
    out_dir = "wx-articles"
    out_htm_dir = out_dir + os.sep + "html"
    out_txt_dir = out_dir + os.sep + "txt"
    os.mkdir(out_dir)
    os.mkdir(out_htm_dir)
    os.mkdir(out_txt_dir)
    # 下载列表
    items = mongo_wx.find()
    for item in items:
        title, url = item["title"], item["content_url"]
        print(f'title={title}, url={url}')
        if len(url) == 0:
            continue

        res = {}
        params = {}
        headers = {'User-Agent': get_random_user_agent()}
        resp = requests.get(url=url, params=params, headers=headers)
        if not resp:
            continue
        res["html"] = resp.content.decode("utf-8")
        res["outfile"] = out_htm_dir + os.sep + title + ".html"
        with open(res["outfile"], "wb") as f:
            f.write(resp.content)
        time.sleep(2)


if __name__ == '__main__':
    # fetch_list()
    download_all()
    pass
