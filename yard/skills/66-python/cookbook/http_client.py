#!/usr/bin/env python3
# 测试http请求

import requests
import urllib
import httplib2

headers = {
  'User-Agent': ''
}

class HttpRequester:
  def get(self, url, params):
    res1 = self.getByR(url, params)
    res2 = self.getByUrllib(url, params)
    res3 = self.getByHttplib2(url, params)
    return res2

  # 调用requests, params为json/dict
  def getByR(self, url, params):
    resp = requests.get(url, params=params)
    res = (headers, content) = (resp.headers, resp.content)
    return res

  # 调用urllib, params内部转为string
  def getByUrllib(self, url, params):
    queryStr = urllib.parse.urlencode(params)
    resp = urllib.request.urlopen(url + '?' + queryStr)
    res = (headers, content) = (resp.headers, resp.read())
    return res
  
  # 调用httplib2
  def getByHttplib2(self, url, params):
    hObj = httplib2.Http(url)
    res = (headers, content) = hObj.request(url, 'GET')
    return res


def main():
  hr = HttpRequester()
  res = hr.get('https://baidu.com', { 'ie': 'utf-8', 'tn': 'baidu', 'wd': 'hallo' })
  print(res)


if __name__ == "__main__":
  main()
