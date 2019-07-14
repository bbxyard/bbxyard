from django.contrib import admin
from django.contrib.admin import views
from com.adapter.about import init_about
from .models import Article


# Register your models here.


class ArticleAdmin(admin.ModelAdmin):
    list_display = ('title', 'publish_date')
    list_filter = ('publish_date', )


# init_about(admin, views)
admin.site.register(Article, ArticleAdmin)
