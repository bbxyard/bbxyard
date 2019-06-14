# -*- coding: utf-8 -*-
import scrapy

from pic.items import PicItem

class XhSpider(scrapy.Spider):
    name = 'xh'
    allowed_domains = ['xiaohuar.com']
    start_urls = ['http://xiaohuar.com/list-1-0.html']

    def parse(self, response):
        # 获取所有图片的a标签
        allPics = response.xpath('//div[@class="img"]/a')
        for pic in allPics:
            # 提取每张图片信息
            item = PicItem()
            name = pic.xpath('./img/@alt').extract()[0]
            addr = pic.xpath('./img/@src').extract()[0]
            addr = 'http://www.xiaohuar.com' + addr
            item['name'] = name
            item['addr'] = addr
            # print (item)
            # 返回爬取数据
            yield item

        # 获取下一页
        # self.fetchNext(response)
        navPageList = response.xpath('//div[@id="page"]/div[@class="page_num"]/a')
        # print(navPageList)
        for navPage in navPageList:
            txt = navPage.xpath('./text()').extract()[0]
            url = navPage.xpath('./@href').extract()[0]
            # print(txt)
            if txt == '下一页':
                print(url)
                yield scrapy.Request(url, callback=self.parse)
                break;

    def fetchNext(self, response):
        navPageList = response.xpath('//div[@id="page"]/div[@class="page_num"]/a')
        print(navPageList)
        for navPage in navPageList:
            txt = navPage.xpath('./text()').extract()[0]
            url = navPage.xpath('./@href').extract()[0]
            print(txt)
            if txt == '下一页':
                print(url)
                yield scrapy.Request(url, callback=self.parse)
                break;
