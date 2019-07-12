from django.urls import path, include
import blog.views


urlpatterns = [
    path('hallo_welt', blog.views.hallo_welt),
    path('content', blog.views.article_content),
    path('index', blog.views.display_article_list),
    path('detail', blog.views.display_article_detail)
]
