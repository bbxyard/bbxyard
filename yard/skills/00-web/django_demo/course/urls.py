from com.adapter.urls import path, include
import course.views


urlpatterns = [
    path('hallo_welt', course.views.hallo_welt)
    # path('content', blog.views.article_content),
    # path('index', blog.views.display_article_list),
    # path('detail', blog.views.display_article_detail),
    # path('<int:article_id>', blog.views.display_article_detail),
    # path('article/<int:article_id>', blog.views.display_article_detail)
]
