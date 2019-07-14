import xadmin as admin
from .models import Course, Teacher, Student, TeacherAssistant

# Register your models here.


class CourseAppAdmin(object):
    list_display = ('nickname', 'updated_at')
    list_filter = ('updated_at', )


class CourseTabAdmin(CourseAppAdmin):
    list_display = ('title', 'updated_at')


admin.site.register(Course, CourseTabAdmin)
admin.site.register(Teacher, CourseAppAdmin)
admin.site.register(Student, CourseAppAdmin)
admin.site.register(TeacherAssistant, CourseAppAdmin)
