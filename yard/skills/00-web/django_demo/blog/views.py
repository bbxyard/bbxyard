import json

from django.shortcuts import render
from django.http import HttpResponse
from django.core.paginator import Paginator
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
    page = request.GET.get('page')
    page = int(page) if page else 1

    article_list = Article.objects.all()
    paginator = Paginator(article_list, 2)
    page_num = paginator.num_pages
    page_article_list = paginator.page(page)
    prev_page = page - 1 if page_article_list.has_previous() else page
    next_page = page + 1 if page_article_list.has_next() else page

    context = {
        "latest_article_list": article_list,
        "article_list": page_article_list.object_list,
        "page_num_range": range(1, page_num + 1),
        "cur_page": page,
        "prev_page": prev_page,
        "next_page": next_page
    }
    return render(request, "article/index.html", context=context)


def display_article_detail(request, article_id):
    article_list = Article.objects.all()
    prev_article, article, next_article, cnt = None, None, None, len(article_list)
    for index, item in enumerate(article_list):
        if item.article_id == article_id:
            article = item
            if index > 0: prev_article = article_list[index - 1]
            if index + 1 < cnt: next_article = article_list[index + 1]
            break

    context = {
        "article": article,
        "prev_article": prev_article,
        "next_article": next_article,
        "section_list": article.content.split('\n')
    }
    return render(request, "article/detail.html", context=context)
