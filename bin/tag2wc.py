#!/usr/bin/python
# -*- coding: utf-8 -*-

"""Command-line tool to generate word clouds
Usage:

depends:
    - libs: python, pip, wordcloud
    - fonts: ...
    + run: easy_install pip; sudo pip install wordcloud;
"""

import re
import argparse
import wordcloud as WC
import numpy as np
from PIL import Image

import jieba  #结巴分词
import sys
reload(sys)
sys.setdefaultencoding("utf8")

def txt2dict(txt):
  dict = {}
  # lns = "a 5\nb 10\nc 20".splitlines()
  lns = txt.splitlines()
  for x in lns:
    kv = x.strip().split(' ')
    dict[kv[0]] = int(kv[1])
  print lns
  return dict

def txt2dictOrList(txt):
    lns = txt.splitlines()
    if len(lns) == 0:
        return {}
    # 判断第一行.
    firstLn = lns[0].strip()
    if re.search(r" [0-9]+$", firstLn, re.M | re.I):
        dict = {}
        for x in lns:
            kv = x.strip().split(' ')
            if len(kv) < 2:
                continue
            dict[kv[0]] = int(kv[1])
        print dict
        return dict
    else:
        return txt

def main(args):
    wc = WC.WordCloud(stopwords=args.stopwords, mask=args.mask,
        width=args.width, height=args.height, margin=args.margin,
        max_font_size=args.max_font_size, min_font_size=args.min_font_size,
        font_path=args.font_path,
        random_state=args.random_state,
        relative_scaling=args.relative_scaling,
        max_words=args.max_words,
        color_func=args.color_func, background_color=args.background_color,
        collocations=args.collocations)

    if type(args.text) == type({}):
        wc.generate_from_frequencies(args.text)
    else:
        text_cut = jieba.cut(args.text)   #分词
        new_textlist = ' '.join(text_cut)   #组合
        wc.generate(new_textlist)

    image = wc.to_image()

    with args.imagefile:
        out = args.imagefile if sys.version < '3' else args.imagefile.buffer
        image.save(out, format='png')

def parse_args(arguments):
    prog = 'python tag2wc.py'
    description = ('A simple command line interface for tags to wordcloud.')
    parser = argparse.ArgumentParser(description=description)
    parser.add_argument('-t', '--text', metavar='file', type=argparse.FileType(), default='-',
        help='specify file of words to build the word cloud (default: stdin)')
    parser.add_argument('-p', '--image-file', metavar='file', dest='imagefile', type=argparse.FileType('w'), default='-',
        help='file the completed PNG image should be written to (default: stdout)')
    parser.add_argument('-f', '--font-file', metavar='path', dest='font_path',
        help='path to font file you wish to use (default: DroidSansMono)')
    parser.add_argument('-m', '--mask-file', metavar='file', dest="mask", type=argparse.FileType(),
        help='mask to use for the image form')
    parser.add_argument('-s', '--stopwords', metavar='file', type=argparse.FileType(),
        help='specify file of stopwords (containing one word per line) to remove from the given text after parsing')
    parser.add_argument('-M', '--colormask', metavar='[file]', nargs="*",
        help='color mask to use for image coloring')
    parser.add_argument('--relative_scaling', type=float, default=0,
        metavar='rs', help=' scaling of words by frequency (0 - 1)')
    parser.add_argument('--margin', type=int, default=2,
        metavar='size', help='spacing to leave around words')
    parser.add_argument('--max-words', type=int, default=500, dest="max_words",
        metavar="mw", help="define output max words count")
    parser.add_argument('--max-font-size', type=int, default=80, dest="max_font_size",
        metavar='mfs', help='define output max font size')
    parser.add_argument('--min-font-size', type=int, default=2, dest="min_font_size",
        metavar='mfs', help='define output min font size')
    parser.add_argument('-W', '--width', type=int, default=750,
        metavar='width', help='define output image width')
    parser.add_argument('-H', '--height', type=int, default=750,
        metavar='height', help='define output image height')
    parser.add_argument('--random-state', type=int, default=30, dest="random_state",
        metavar='rs', help='define output random_state')
    parser.add_argument('-c', '--color', metavar='color',
        help='use given color as coloring for the image - accepts any value from PIL.ImageColor.getcolor')
    parser.add_argument('-b', '--background', metavar='color', default='white', type=str, dest='background_color',
        help='use given color as background color for the image - accepts any value from PIL.ImageColor.getcolor')
    parser.add_argument('--no_collocations', action='store_true',
        help='do not add collocations (bigrams) to word cloud (default: add unigrams and bigrams)')
    args = parser.parse_args(arguments)

    if args.colormask and args.color:
        raise ValueError('specify either a color mask or a color function')

    with args.text:
        args.text = txt2dictOrList(args.text.read().decode('utf-8'))

    if args.stopwords:
        with args.stopwords:
            args.stopwords = set(map(str.strip, args.stopwords.readlines()))

    if args.mask:
        args.mask = np.array(Image.open(args.mask))

    color_func = WC.random_color_func
    if args.color:
        color_func = WC.get_single_color_func(args.color)
    
    if args.colormask == None:
        print("NONE ")
    elif len(args.colormask) == 0:
        if args.mask != None:
            color_func = WC.ImageColorGenerator(args.mask)
        print "Empty [] SAME AS mask"
    elif len(args.colormask) == 1:
        cmfile = args.colormask[0]
        image = np.array(Image.open(cmfile))
        color_func = WC.ImageColorGenerator(image)
        print args.colormask

    args.collocations = not args.no_collocations

    args.color_func = color_func
    return args

if __name__ == '__main__':
  main(parse_args(sys.argv[1:]))
