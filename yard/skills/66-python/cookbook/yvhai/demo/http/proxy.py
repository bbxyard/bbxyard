#!/usr/bin/env python3
# proxy


from urllib import request
from yvhai.demo.base import YHDemo


class ProxyDemo(YHDemo):
    def __init__(self):
        super(ProxyDemo, self).__init__('ProxyDemo')

    @staticmethod
    def test():
        proxy_meta = "http://%(user)s:%(pass)s@%(host)s:%(port)s" % {
            "host": "http-pro.abuyun.com",
            "port": "9010",
            "user": 'H2M5PG7226IL97EP',
            "pass": "20B2D001399FDE0F"
        }
        # proxy_meta = "http://%(user)s:%(pass)s@%(host)s:%(port)s" % {
        #     "host": "http-dyn.abuyun.com",
        #     "port": "9020",
        #     "user": 'HSJ9C9029EE63J5D',
        #     "pass": "92B2F1FD981540B8"
        # }
        proxy_handler = request.ProxyHandler({
            "http": proxy_meta,
            "https": proxy_meta
        })

        opener = request.build_opener(proxy_handler)
        request.install_opener(opener)
        resp = request.urlopen('http://test.abuyun.com/').read()
        print(resp)

    @staticmethod
    def demo(args=[]):
        ProxyDemo.test()
