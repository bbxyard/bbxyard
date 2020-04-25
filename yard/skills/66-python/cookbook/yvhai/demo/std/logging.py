#!/usr/bin/env python3
# 日志测试


import logging

from yvhai.demo.base import YHDemo


class LogerDemo(YHDemo):
    def __init__(self):
        logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        self.logger = logging.getLogger(__name__)
        self.logger.info('Start print log')

    def __del__(self):
        self.logger.info('Finished')

    def test(self):
        self.logger.critical('Das ist Critical Msg')
        self.logger.debug('==> Func test Enter')
        self.logger.warning('hallo {}'.format('welt'))
        self.logger.debug('==> Func test Leave')

    @staticmethod
    def demo(args=[]):
        inst = LogerDemo()
        inst.test()


if __name__ == "__main__":
    LogerDemo.demo()
