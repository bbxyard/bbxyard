#!/usr/bin/env python3
# 测试http请求


from yvhai.demo.http.client import HttpRequester
from yvhai.demo.http.proxy import ProxyDemo

if __name__ == "__main__":
    # HttpRequester.demo()
    ProxyDemo.demo()
    print(__file__, 'done')
