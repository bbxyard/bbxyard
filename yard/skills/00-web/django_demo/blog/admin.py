from django.contrib import admin

# Register your models here.

from .models import Article


class ArticleAdmin(admin.ModelAdmin):
    list_display = ('title', 'publish_date')
    list_filter = ('publish_date', )


admin.site.register(Article, ArticleAdmin)
