import xadmin as admin
from .models import Course, Teacher, Student, TeacherAssistant

# Register your models here.


class CourseAppAdmin(object):
    list_display = ('nickname', 'updated_at')
    list_filter = ('updated_at', )


class CourseTabAdmin(CourseAppAdmin):
    list_display = ('title', 'updated_at')
    data_charts = {
        "order_stat": {
            'title': "订单统计",
            "x-field": "online_date",
            "y-field": ("price", "volume"),
            "order": ("online_date", )
        }
    }


admin.site.register(Course, CourseTabAdmin)
admin.site.register(Teacher, CourseAppAdmin)
admin.site.register(Student, CourseAppAdmin)
admin.site.register(TeacherAssistant, CourseAppAdmin)
