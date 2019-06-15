#!/usr/bin/env python
# -*- coding: utf-8 -*-

import logging

import scrapy
from stackoverflow.spiders.items import StackoverflowItem


formatter = logging.Formatter(
    '%(asctime)s - %(name)s - %(levelname)s - %(message)s')
logger = logging.getLogger('monitor')
logger.setLevel(logging.INFO)

fh = logging.FileHandler('monitor.log')
fh.setLevel(logging.INFO)

fh.setFormatter(formatter)
logger.addHandler(fh)


class StackoverflowSpider(scrapy.Spider):

    name = "stackoverflow"

    def __init__(self):
        self.count = 1

    def start_requests(self):
        _url = 'https://stackoverflow.com/questions?page={page}&sort=votes&pagesize=50'
        urls = [_url.format(page=page) for page in range(1, 100001)]
        for url in urls:
            yield scrapy.Request(url=url, callback=self.parse)

    def parse(self, response):

        nodeList = response.xpath('//*[@id="questions"]/*[@class="question-summary"]')
        for sel in nodeList:
            self.count += 1
            if self.count % 100 == 0:
                logger.info(self.count)

            item = StackoverflowItem()
            item['votes'] = sel.xpath('./*/div[@class="stats"]/div[@class="vote"]/div[@class="votes"]/span/strong/text()').extract()[0]
            item['answers'] = sel.xpath('./*/div[@class="stats"]/div[2]/strong/text()').extract()[0]
            item['views'] = sel.xpath('./*/div[@class="views supernova"]/@title').extract()[0].split()[0].replace(',','')
            item['questions'] = sel.xpath('./div[@class="summary"]/*/a[@class="question-hyperlink"]/text()').extract()[0]
            item['links'] = sel.xpath('./div[@class="summary"]/*/a[@class="question-hyperlink"]/@href').extract()[0]
            item['tags'] = sel.xpath('./div[@class="summary"]/div[2]/a/text()').extract()
            yield item


        # item[''] = sel.xpath('//div[@class="votes"]/span/strong/text()').extract()
        # for index in range(1, 51):
            # sel = response.xpath('//*[@id="questions"]/div[{index}]'.format(index=index))
            # item = StackoverflowItem()

 
            # item['votes'] = sel.xpath(
            #     'div[1]/div[2]/div[1]/div[1]/span/strong/text()').extract()
            # item['answers'] = sel.xpath(
            #     'div[1]/div[2]/div[2]/strong/text()').extract()
            # item['links'] = "".join(
            #     sel.xpath('div[2]/h3/a/@href').extract()).split("/")[2]

