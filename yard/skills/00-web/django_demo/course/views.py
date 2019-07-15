import json
from django.shortcuts import render
from django.http import JsonResponse, HttpResponse
from .models import *

# Create your views here.


def hallo_welt(request):
    return JsonResponse({"res": "hallo-welt"})

# 获得所有
def get_all_title():
    res = {}
    res["teachers"] = [item.nickname for item in Teacher.objects.all()]
    res["students"] = [item.nickname for item in Student.objects.all()]
    res["course"] = [item.title for item in Course.objects.all()]
    res["assist"] = [item.nickname for item in TeacherAssistant.objects.all()]
    return res


# 格式化输出
def _to_list(xlist):
    out_list = []
    for item in xlist:
        if isinstance(item, Teacher):
            out_list.append({"name": item.nickname, "fans": item.fans})
    return out_list


# 获取指定
def get_teacher(request):
    # id = request.GET.get("id")
    name = request.GET.get("name")
    elem = Teacher.objects.get(nickname=name)
    info = {
        "name": name,
        "fans": elem.fans,
        "introduction": elem.introduction
    }
    return JsonResponse(info)


# 检索
def search_teacher(request):
    min_fans = request.GET.get("min", 100)
    max_fans = request.GET.get("max", 100000)
    name = request.GET.get("name")

    # 数值比较，区间查询
    res = {
        "fans__lte": [{"name": elem.nickname, "fans": elem.fans} for elem in Teacher.objects.filter(fans__lte=min_fans)],
        "fans__gte": _to_list(Teacher.objects.filter(fans__gte=min_fans)),
        "fans__range": _to_list(Teacher.objects.filter(fans__in=(min_fans, max_fans)))
    }
    
    # 按名查询
    if name:
        res["name"] = _to_list(Teacher.objects.filter(nickname=name)),
        res["name__contains"] = _to_list(Teacher.objects.filter(nickname__contains=name)),
        res["name__icontains"] = _to_list(Teacher.objects.filter(nickname__icontains=name)),

    # 测试排序
    res["order_by_name"] = _to_list(Teacher.objects.filter(fans__range=(min_fans, max_fans)).order_by('nickname'))
    res["order_by_name_desc"] = _to_list(Teacher.objects.filter(fans__range=(min_fans, max_fans)).order_by('-nickname'))
    res["order_by_fans"] = _to_list(Teacher.objects.filter(fans__range=(min_fans, max_fans)).order_by('fans'))
    res["order_by_fans_desc"] = _to_list(Teacher.objects.filter(fans__range=(min_fans, max_fans)).order_by('-fans'))
    return JsonResponse(res)


def get_student(request):
    # id = request.GET.get("id")
    name = request.GET.get("name")
    elem = Student.objects.get(nickname=name)
    # xx = elem.course
    info = {
        "name": name,
        "age": elem.age,
        "gender": elem.gender,
        "study_time": elem.study_time,
        # "course": [x.title for x in elem.course]
    }
    return JsonResponse(info)


def test(request):
    res = {
        "list": get_all_title()
    }
    # json_res = json.dumps(res)
    # return HttpResponse(json_res)
    return JsonResponse(res)
