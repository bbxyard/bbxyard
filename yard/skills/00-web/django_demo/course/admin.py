from django.contrib import admin
from .models import Course, Teacher, Student, TeacherAssistant

# Register your models here.


class CourseAppAdmin(admin.ModelAdmin):
    list_display = ('nickname', 'updated_at')
    list_filter = ('updated_at', )


@admin.register(Course)
class CourseTabAdmin(CourseAppAdmin):
    list_display = ('title', 'type', 'price', 'volume', 'online_date')

    # list_per_page设置每页显示多少条记录，默认是100条
    list_per_page = 5

    # ordering设置默认排序字段，负号表示降序排序
    ordering = ('-online_date',)

    # list_editable 设置默认可编辑字段
    list_editable = ['type', 'price']

    # fk_fields 设置显示外键字段
    # fk_fields = ('teacher_id',)

    # 筛选器
    list_filter = ('type', 'price', 'volume', 'online_date')
    search_fields = ('type', 'price', 'volume')
    date_hierarchy = 'online_date'


@admin.register(Student)
class StudentTabAdmin(CourseAppAdmin):
    # many to many
    filter_horizontal = ('course', )
    # 字段排列
    fieldsets = (
        ("基本信息", {"fields": [("nickname"), ("age", "gender", "study_time")]}),
        ("内容", {"fields": ["course"]})
    )


admin.site.register(Teacher, CourseAppAdmin)
# admin.site.register(Student, CourseAppAdmin)
admin.site.register(TeacherAssistant, CourseAppAdmin)
