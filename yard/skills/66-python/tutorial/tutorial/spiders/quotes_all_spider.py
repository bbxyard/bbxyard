import scrapy

class QuotesAllSpider(scrapy.Spider):
  name = 'quotes-all'

  def start_requests(self):
    urls = [
      'http://quotes.toscrape.com'
    ]
    for url in urls:
      yield scrapy.Request(url=url, callback=self.parse)


  def parse(self, response):
    # 保存整个网页到文件
    page = response.url.split('/')[-2]
    filename = 'out/quotes-%s.htm' % page
    with open(filename, 'wb') as f:
      f.write(response.body)
    self.log('Saved file %s' % filename)
    # 解析网页
    for quote in response.css('div.quote'):
      yield {
        'text': quote.css('span.text::text').extract_first(),
        'author': quote.xpath('span/small/text()').extract_first
      }
    # 获取下一页
    next_page = response.css('li.next a::attr("href")').extract_first()
    if next_page is not None:
      next_page = response.urljoin(next_page)
      yield scrapy.Request(next_page, callback=self.parse)
