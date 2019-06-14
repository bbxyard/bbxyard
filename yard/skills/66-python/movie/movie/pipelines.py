# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html


class MoviePipeline(object):
    def process_item(self, item, spider):
        with open('out/my_meiju.txt', 'a', encoding='UTF-8') as fp:
            fp.write(str(item['name'].strip()) + '\n')
