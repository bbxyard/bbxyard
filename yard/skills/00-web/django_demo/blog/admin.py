from django.contrib import admin
from django.contrib.admin import views
from com.adapter.about import init_about
from .models import Article


# Register your models here.

@admin.register(Article)
class ArticleAdmin(admin.ModelAdmin):
    list_display = ('title', 'subtitle', 'brief_content', 'publish_date')
    list_filter = ('publish_date', )


# init_about(admin, views)
# admin.site.register(Article, ArticleAdmin)
admin.site.site_header = "网站header"
admin.site.site_title = "网站标题"

