#! /usr/bin/env python
# -*- coding:utf-8 -*-

from os import path
import matplotlib.pyplot as plt  #绘制图片
from scipy.misc import imread   #读取图片
from wordcloud import WordCloud,ImageColorGenerator
import jieba  #结巴分词

import sys
reload(sys)
sys.setdefaultencoding("utf8")


def txt2pic(txt_file, out_png, font_path, mask_file):
  text_address = path.abspath(txt_file)
  text = open(text_address).read()   #读取文本
  text_cut = jieba.cut(text)   #分词
  new_textlist = ' '.join(text_cut)   #组合
  pic_address = path.abspath(mask_file)
  pic = imread(pic_address)  #读取图片
  pic_color = ImageColorGenerator(pic)   #根据图片生成颜色函数
  wc = WordCloud(background_color='white',    #构造wordcloud类
    mask=pic,
    width = 750,
    height = 750,
    max_font_size = 80,
    random_state=30,
    font_path=font_path,
    max_words=500,
    min_font_size=2,
    color_func=pic_color
  )
  wc.generate(new_textlist)    #生成词云图
  plt.figure()    #画图
  plt.imshow(wc)
  plt.axis("off")
  plt.show()
  wc.to_file(out_png)   #保存图片

def txt2dict(phrase_file):
  dict = {}
  # lns = "a 5\nb 10\nc 20".splitlines()
  lns = open(phrase_file).read().decode('utf-8').splitlines()
  for x in lns:
    kv = x.split(' ')
    dict[kv[0]] = int(kv[1])
  print lns
  return dict

def printFreq(phrase_file):
  print txt2dict(phrase_file)
  print "done"

def phrase2pic(phrase_file, out_png, font_path, mask_file):
  phrase_dict = txt2dict(phrase_file)
  pic_address = path.abspath(mask_file)
  pic = imread(pic_address)  #读取图片
  pic_color = ImageColorGenerator(pic)   #根据图片生成颜色函数
  wc = WordCloud(background_color='white',    #构造wordcloud类
    mask=pic,
    width = 750,
    height = 750,
    max_font_size = 80,
    random_state=30,
    font_path=font_path,
    max_words=500,
    min_font_size=2,
    color_func=pic_color
  )
  wc.generate_from_frequencies(phrase_dict)
  # wc.generate(new_textlist)    #生成词云图
  plt.figure()    #画图
  plt.imshow(wc)
  plt.axis("off")
  plt.show()
  wc.to_file(out_png)   #保存图片

def main():
  # printFreq("./1.txt")
  # phrase2pic('./1.txt', "/tmp/wc-out.png", "/tmp/msyhbd.ttf", './mask-map.jpg')
  # txt2pic('/tmp/1.txt', "/tmp/wc-out.png", "/tmp/msyhbd.ttf", './mask.jpg')
  # txt2pic('/tmp/1.txt', "/tmp/wc-out.png", "/tmp/Baoli.ttc", './mask.jpg')

# main
if __name__ == "__main__":
  main()
