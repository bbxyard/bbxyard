#!/usr/bin/env python3
# 测试http请求

import requests
import urllib
import httplib2

headers = {
    'User-Agent': ''
}


class HttpRequester:

    @staticmethod
    def get(url, params):
        res1 = HttpRequester.get_by_r(url, params)
        res2 = HttpRequester.get_by_urllib(url, params)
        res3 = HttpRequester.get_by_httplib2(url, params)
        return res2

    # 调用requests, params为json/dict
    @staticmethod
    def get_by_r(url, params):
        resp = requests.get(url, params=params)
        res = (headers, content) = (resp.headers, resp.content)
        return res

    # 调用urllib, params内部转为string
    @staticmethod
    def get_by_urllib(url, params):
        queryStr = urllib.parse.urlencode(params)
        resp = urllib.request.urlopen(url + '?' + queryStr)
        res = (headers, content) = (resp.headers, resp.read())
        return res

    # 调用httplib2
    @staticmethod
    def get_by_httplib2(url, params):
        hObj = httplib2.Http(url)
        res = (headers, content) = hObj.request(url, 'GET')
        return res


def main():
    res = HttpRequester.get('https://baidu.com', {'ie': 'utf-8', 'tn': 'baidu', 'wd': 'hallo'})
    print(res)


if __name__ == "__main__":
    main()
