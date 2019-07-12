import json

from django.shortcuts import render
from django.http import HttpResponse
from .models import Article


# Create your views here.


def hallo_welt(request):
    return HttpResponse("hallo welt".title())


def article_content(request):
    article = Article.objects.all()[0]
    res = {
        "id": article.article_id,
        "title": article.title,
        "brief": article.brief_content,
        "content": article.content,
        "publish_date": article.publish_date.strftime("%Y-%m-%d %H:%M:%S")
    }
    json_res = json.dumps(res)
    return HttpResponse(json_res)


def display_article_list(request):
    article_list = Article.objects.all()
    context = {
        "article_list": article_list
    }
    return render(request, "article/index.html", context=context)


def display_article_detail(request, article_id):
    article_list = Article.objects.all()
    article = None
    for x in article_list:
        if x.article_id == article_id:
            article = x
            break

    context = {
        "article": article,
        "section_list": article.content.split('\n')
    }
    return render(request, "article/detail.html", context=context)
