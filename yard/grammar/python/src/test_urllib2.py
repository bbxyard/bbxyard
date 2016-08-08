#!/usr/bin/python
#-*- coding: utf-8 -*-
# test urllib2 urllib

import os, sys
import urllib, urllib2


# basic use
def test_url_request_basic():
    response = urllib2.urlopen("http://python.org")
    html = response.read()
    print html


# basic use -- post
def test_url_request_basic_plus():
    req = urllib2.Request("http://www.baidu.com")
    response = urllib2.urlopen(req)
    html = response.read()
    print html


# post - request
# curl -d '{"params":"type=cnen;outfmt=json", "key":"python", "txt":"你好"}' "http://translate.openspeech.cn/translate"
def test_url_post():
    url = "http://translate.openspeech.cn/translate"
    #params = { "params":"type=cnen;outfmt=json", "key":"python", "txt":"这是一个python-post测试"}
    params = {"params": "type=cnen;outfmt=json", "key": "python", "txt": "python-post"}
    data = urllib.urlencode(params)
    # 构造request
    req = None
    if True:
        user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
        headers = { "User-Agent" : user_agent }
        req = urllib2.Request(url, data, headers)
    else :
        req = urllib2.Request(url, data)
    resp = urllib2.urlopen(req)
    the_page = resp.read()
    print the_page


# get - request
def test_url_get():
    url = "http://translate.openspeech.cn/translate"
    params = "key=python&from=zh&to=en&content=这是一个python-get测试"
    full_url = url + "?" + params
    resp = urllib2.urlopen(full_url)
    the_page = resp.read()
    print the_page


# main
def test_url_main():
    # 基本测试
    test_url_request_basic()
    # 基本测试, 带上显示request
    test_url_request_basic_plus()
    # post测试
    test_url_post()
    # test测试
    test_url_get()


# test main
if __name__ == "__main__" :
    test_url_main()
