# Django 多版本 自适应

try:
    # Django 2.x 写法
    from django.urls import path, include
except:
    # Django 1.x 写法
    from django.conf.urls import include
    from django.conf.urls import url as path


