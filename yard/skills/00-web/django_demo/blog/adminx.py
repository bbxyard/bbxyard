from .models import Article
import xadmin


class ArticleAdmin(object):
    list_display = ('title', 'publish_date')
    list_filter = ('publish_date',)
    pass


xadmin.site.register(Article, ArticleAdmin)
