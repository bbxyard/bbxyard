import json
from django.db.models import Count, Avg, Max, Min, Sum, F, Q
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
        if isinstance(item, Course):
            out_list.append({"name": item.teacher.nickname, "fans": item.teacher.fans, "title": item.title})
        if isinstance(item, Student):
            course = [c.title for c in item.course.all()]
            out_list.append({"name": item.nickname, "age": item.age, "course": course})
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
        "fans__lte": [{"name": elem.nickname, "fans": elem.fans} for elem in
                      Teacher.objects.filter(fans__lte=min_fans)],
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

    # 过滤
    if name:
        res["exclude_name"] = _to_list(Teacher.objects.all().exclude(nickname=name))
        res["exclude_name_reverse"] = _to_list(Teacher.objects.all().exclude(nickname=name).reverse())

    # 返回元组、字典
    print(TeacherAssistant.objects.values('nickname', 'hobby'))
    print(TeacherAssistant.objects.values_list('nickname', 'hobby'))
    print(TeacherAssistant.objects.values_list('nickname', flat=True))

    # dates
    print(Course.objects.dates('created_at', 'year', order="DESC"))
    print(Course.objects.datetimes('created_at', 'year', order="DESC"))
    print(Course.objects.dates('online_date', 'year', order="DESC"))

    # 集合 交并差
    p240 = Course.objects.filter(price__gte=240)
    p260 = Course.objects.filter(price__lte=260)
    print(p240.union(p260))
    print(p240.intersection(p260))
    print(p240.difference(p260))

    # 关联查询
    # select_related() 一对一、多对一查询优化, prefetch_related() 一对多、多对多查询优化；反向查询
    courses = Course.objects.all().select_related('teacher')
    res["course_select_related"] = _to_list(courses)

    students = _to_list(Student.objects.filter(age__gt=30).prefetch_related('course'))
    print(Teacher.objects.get(nickname=name).course_set.all())

    # 统计信息
    print(Course.objects.values('teacher').annotate(vol=Sum('volume')))
    print(Course.objects.values('teacher').annotate(avg=Avg('price'), sum=Sum('price'), max=Max('price'), min=Min('price')))

    # 不返回Query API
    # 1.获取对象 get(), get_or_create(), first(), last(), latest(), earliest(), in_bulk()
    print(Course.objects.first())
    print(Course.objects.last())
    print(Course.objects.earliest())
    print(Course.objects.latest())
    print(Course.objects.in_bulk(['Python系列教程4', 'Golang系列教程1']))
    # 2.创建对象 create(), bulk_create(), update_or_create() 创建，批量创建，创建或更新

    # 3.更新对象 update(), update_or_create() 更新，更新或创建
    print(Course.objects.filter(title='Java系列教程2').update(price=300))

    # 4.删除对象 delete() 使用filter过滤
    print(Course.objects.filter(title='test').delete())

    # 5.其它操作 exists(), count(), aggregate() 判断是否存在，统计个数，聚合
    print(Course.objects.filter(title='test').exists())
    print(Course.objects.filter(title='Java系列教程2').exists())
    print(Course.objects.filter(title='Java系列教程2').count())
    print(Course.objects.count())
    print(Course.objects.aggregate(Max('price'), Min('price'), Avg('price'), Sum('volume')))
    # courses = Course.objects.values('teacher').annotate(t=GroupConcat('title', distinct=True,
    #                                                                   ordering='title ASC',
    #                                                                   separator='-'))
    # for c in courses:
    #     print(c)

    # F Q
    print(Course.objects.update(price=F('price') - 11))
    print(Course.objects.filter(volume__lte=F('price') * 10))
    print(Course.objects.filter(Q(title__icontains='Java') & Q(volume__gte=5000)))
    print(Course.objects.filter(Q(title__icontains='golang') | Q(volume__lte=1000)))

    # 返回部分字段
    res["extra_select_cols"] = [item.name for item in Teacher.objects.all().extra(select={"name": "nickname"})]

    # 查看原生SQL
    res["debug_sql_extra_select_cols"] = str(Teacher.objects.all().extra(select={"name": "nickname"}).query)
    res["debug_sql_only_cols"] = str(Teacher.objects.all().only("nickname", "fans").query)
    res["debug_sql"] = str(Teacher.objects.filter(fans__range=(min_fans, max_fans)).order_by('-fans').query)
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


def get_address(request, address_id):
    if int(address_id) == 0:
        address_data = AddressInfo.objects.filter(pid__isnull=True).values('id', 'address')
    else:
        address_data = AddressInfo.objects.filter(pid_id=int(address_id)).values('id', 'address')
    area_list = []
    for a in address_data:
        area_list.append({"id": a["id"], "address": a["address"]})
    return JsonResponse(area_list, content_type="application/json", safe=False)


def display_address(request):
    return render(request, "address.html")
