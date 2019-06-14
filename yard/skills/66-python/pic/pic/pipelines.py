# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

import os
import json
import urllib.request

class PicPipeline(object):
    def process_item(self, item, spider):
        # print(item['addr'])
        # 保存item
        self.saveItem(item)
        # 保存图片
        self.savePic(item)

    def saveItem(self, item):
        # 保存为json
        idxpath = os.path.join('out', 'idx.jl')
        with open(idxpath, 'a') as fp:
            jItem = { 'name': item['name'], 'url': item['addr'] }
            print(json.dumps(jItem))
            fp.write(json.dumps(jItem))
            # json.dump(jItem, fp, indent=0)
            fp.write('\n')

    # 保存图片
    def savePic(self, item):
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0'
        }
        req = urllib.request.Request(url=item['addr'], headers=headers)
        res = urllib.request.urlopen(req)
        filepath = os.path.join('download', item['name'] + '.jpg')
        with open(filepath, 'wb') as fp:
            fp.write(res.read())
