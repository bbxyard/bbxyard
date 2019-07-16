from com.adapter.urls import path, include
import dss.views

urlpatterns = [
    path('do', dss.views.do),
]
