from django.contrib import admin
from .models import Article

# Register your models here.


class ArticleAdmin(admin.ModelAdmin):
    list_display = ('title', 'publish_date')
    list_filter = ('publish_date', )


admin.site.register(Article, ArticleAdmin)
