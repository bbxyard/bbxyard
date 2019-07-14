import xadmin
from xadmin import views
from com.adapter.about import init_about
from .models import Article


class ArticleAdmin(object):
    list_display = ('title', 'publish_date')
    list_filter = ('publish_date',)
    pass


init_about(xadmin, views)
xadmin.site.register(Article, ArticleAdmin)
