import requests
import re
import os


class getURLs(object):
    """docstring for getURLs"""

    def __init__(self):
        super(getURLs, self).__init__()
        '''
           输入参数：搜索关键字
           输出：此关键字的url到文本
        '''

    def getURL(self, keyword, filename):
        page_count = 0
        url = 'http://www.baidu.com/s?wd=%s&pn=' % keyword + str(page_count)
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11',
            'Accept': 'text/html;q=0.9,*/*;q=0.8',
            'Accept-Charset': 'utf-8;q=0.7,*;q=0.3'}
        r = requests.get(url)
        page_content = r.content
        oldpage_url = []
        oldpage_url = self.find_allURL(page_content)
        newpage_url = []
        all_url = []
        all_url.extend(oldpage_url)
        while oldpage_url != newpage_url:
            page_count += 10
            url = 'http://www.baidu.com/s?wd=%s&pn=' % keyword + str(page_count)
            r = requests.get(url)
            page_content = r.content
            newpage_url = self.find_allURL(page_content)
            all_url.extend(newpage_url)
            all_url = [i for i in set(all_url)]
        self.write_tofile(all_url, filename)

    # 得到一个page的URL,将url写出文件
    def find_allURL(self, page_content):
        reg_url = r'<span class="g">(.+)/.+\d{4}-\d{2}-\d{2}.+</span>'
        onepage_url = []
        onepage_url = re.findall(reg_url, page_content)
        return onepage_url

    def write_tofile(self, all_url, filename):
        reg = r'<.{1,2}>'
        fobj = open(filename, 'a')
        fobj.writelines(['%s\n' % re.sub(reg, '', x) for x in all_url])
        fobj.close()


if __name__ == '__main__':
    geturls = getURLs()
    keyword = 'intext:powered by discuz'
    filename = 'data_for_discuztext.txt'
    geturls.getURL(keyword, filename)
