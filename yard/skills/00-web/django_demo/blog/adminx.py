import xadmin
from xadmin import views
from com.adapter.about import init_about
from .models import Article


class ArticleAdmin(object):
    list_display = ('title', 'publish_date')
    list_filter = ('publish_date',)
    show_detail_fields = ['brief_content']
    # 刷新频率
    refresh_times = (3, 5)
    # 书签
    list_bookmarks = [{
        "title": "包含-哈利",
        "query": {"subtitle__contains": 'Potter'},
        "order": ("-publish_date", ),
        "cols": ("title", "content", "publish_date")
    },{
        "title": "包含-与",
        "query": {"subtitle__contains": 'and'},
        "order": ("-publish_date",),
        "cols": ("title", "subtitle", "content", "publish_date")
    }]
    # 导出
    list_export = ('xls', 'xml', 'json')
    # list_export_fields = ('article_id', 'title', 'subtitle', 'content', 'publish_date')
    list_export_fields = ('title', 'subtitle', 'content', 'publish_date')

    # 只读字段
    readyonly_fields = ["subtitle", "content"]

    def get_readonly_fields(self):
        out_fields = [] if self.user.is_superuser else ArticleAdmin.readyonly_fields
        return out_fields


init_about(xadmin, views)
xadmin.site.register(Article, ArticleAdmin)
