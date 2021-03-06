from com.adapter.urls import path, include
import course.views


urlpatterns = [
    path('hallo_welt', course.views.hallo_welt),
    path('test', course.views.test),
    path('teacher/get', course.views.get_teacher),
    path('teacher/search', course.views.search_teacher),
    path('student/get', course.views.get_student),
    # path('content', blog.views.article_content),
    # path('index', blog.views.display_article_list),
    # path('detail', blog.views.display_article_detail),
    # path('<int:article_id>', blog.views.display_article_detail),
    path('address/index', course.views.display_address),
    path('address/<int:address_id>', course.views.get_address)
]
