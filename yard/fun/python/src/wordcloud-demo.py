#! /usr/bin/env python
# - wordcloud demo -

## FAQ
'''
# 1. depends librarys
pip install wordcloud
pip install jieba

# 2. support Chinese
locate wordcloud.py and modify "FONT_PATH", using cn-fonts instead.
FONT_PATH = os.environ.get("FONT_PATH", os.path.join(os.path.dirname(__file__),
                "DroidSansMono.ttf"))   # e.g. msmy.ttf
'''

import matplotlib.pyplot as plt
from wordcloud import WordCloud
import jieba

text_from_file_with_apath = open('/etc/passwd').read()

wordlist_after_jieba = jieba.cut(text_from_file_with_apath, cut_all = True)
wl_space_split = " ".join(wordlist_after_jieba)

my_wordcloud = WordCloud().generate(wl_space_split)

plt.imshow(my_wordcloud)
plt.axis("off")
plt.show()
