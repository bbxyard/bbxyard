from com.adapter.urls import path, include
import blog.views


urlpatterns = [
    path('hallo_welt', blog.views.hallo_welt),
    path('content', blog.views.article_content),
    path('index', blog.views.display_article_list),
    path('detail', blog.views.display_article_detail),
    path('<int:article_id>', blog.views.display_article_detail),
    path('article/<int:article_id>', blog.views.display_article_detail)
]
