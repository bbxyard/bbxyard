#!/usr/bin/env python3


import os
import sys
import random
import django
from datetime import date

project_root_path = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(project_root_path)
os.environ['DJANGO_SETTINGS_MODULE'] = 'django_demo.settings'
django.setup()

from course.models import Teacher, Course, Student, TeacherAssistant


def import_data():
    """使用Django ORM导入数据"""
    # Teacher 表
    Teacher.objects.update_or_create(nickname="AE", defaults={"introduction": "物理工程师", "fans": 102424})
    Teacher.objects.update_or_create(nickname="Jack", defaults={"introduction": "Python工程师", "fans": 1582})
    Teacher.objects.update_or_create(nickname="Allen", defaults={"introduction": "Java工程师", "fans": 1752})
    Teacher.objects.update_or_create(nickname="Henry", defaults={"introduction": "Golang工程师", "fans": 5972})

    # Course 表
    # Course.objects.bulk_create([Course(title=f"物理学系列教程{i}",
    #                                    teacher=Teacher.objects.get(nickname="AE"),
    #                                    type=random.choice((0, 1, 2)),
    #                                    price=random.randint(200, 300),
    #                                    volume=random.randint(100, 10000),
    #                                    online_date=date(random.randint(2015, 2019), random.randint(1, 12),
    #                                                     random.randint(1, 28)))
    #                             for i in range(1, random.randint(5, 10))])
    #
    # Course.objects.bulk_create([Course(title=f"Python系列教程{i}",
    #                                    teacher=Teacher.objects.get(nickname="Jack"),
    #                                    type=random.choice((0, 1, 2)),
    #                                    price=random.randint(200, 300),
    #                                    volume=random.randint(100, 10000),
    #                                    online_date=date(random.randint(2015, 2019), random.randint(1, 12),
    #                                                     random.randint(1, 28)))
    #                             for i in range(1, random.randint(3, 10))])
    #
    # Course.objects.bulk_create([Course(title=f"Java系列教程{i}",
    #                                    teacher=Teacher.objects.get(nickname="Allen"),
    #                                    type=random.choice((0, 1, 2)),
    #                                    price=random.randint(200, 300),
    #                                    volume=random.randint(100, 10000),
    #                                    online_date=date(random.randint(2015, 2019), random.randint(1, 12),
    #                                                     random.randint(1, 28)))
    #                             for i in range(1, random.randint(3, 10))])
    #
    # Course.objects.bulk_create([Course(title=f"Golang系列教程{i}",
    #                                    teacher=Teacher.objects.get(nickname="Henry"),
    #                                    type=random.choice((0, 1, 2)),
    #                                    price=random.randint(200, 300),
    #                                    volume=random.randint(100, 10000),
    #                                    online_date=date(random.randint(2015, 2019), random.randint(1, 12),
    #                                                     random.randint(1, 28)))
    #                             for i in range(1, random.randint(3, 10))])

    # Student
    Student.objects.update_or_create(nickname="alpha同学", defaults={"age": random.randint(18, 72),
                                                                   "gender": random.choice((0, 1, 2)),
                                                                   "study_time": random.randint(9, 999)})

    Student.objects.update_or_create(nickname="beta同学", defaults={"age": random.randint(18, 52),
                                                                  "gender": random.choice((0, 1, 2)),
                                                                  "study_time": random.randint(9, 888)})

    Student.objects.update_or_create(nickname="gama同学", defaults={"age": random.randint(18, 52),
                                                                  "gender": random.choice((0, 1, 2)),
                                                                  "study_time": random.randint(9, 888)})

    Student.objects.update_or_create(nickname="sin同学", defaults={"age": random.randint(18, 52),
                                                                 "gender": random.choice((0, 1, 2)),
                                                                 "study_time": random.randint(9, 888)})

    Student.objects.update_or_create(nickname="cos同学", defaults={"age": random.randint(18, 52),
                                                                 "gender": random.choice((0, 1, 2)),
                                                                 "study_time": random.randint(9, 888)})

    # 正向添加
    # alpha: 销量>=1000
    # beta: 销量>500
    Student.objects.get(nickname="alpha同学").course.add(*Course.objects.filter(volume__gte=1000))
    Student.objects.get(nickname="beta同学").course.add(*Course.objects.filter(volume__gt=500))

    # 反向添加
    Course.objects.get(title="Python系列教程1").student_set.add(*Student.objects.filter(study_time__gte=500))
    Course.objects.get(title="Python系列教程2").student_set.add(*Student.objects.filter(study_time__lte=500))

    # Assistant
    TeacherAssistant.objects.get_or_create(nickname="助教1", defaults={"hobby": "幕课网学习", "teacher":
        Teacher.objects.get(nickname="Jack")})
    TeacherAssistant.objects.get_or_create(nickname="助教2", defaults={"hobby": "幕课网学习", "teacher":
        Teacher.objects.get(nickname="Allen")})
    TeacherAssistant.objects.get_or_create(nickname="助教3", defaults={"hobby": "幕课网学习", "teacher":
        Teacher.objects.get(nickname="Henry")})

    return True


if __name__ == '__main__':
    ret = -1
    if import_data():
        print("数据导入成功")
        ret = 0

    sys.exit(ret)
