from django.contrib import admin

# Register your models here.

from .models import Course, Teacher, Student, TeacherAssistant


class CourseAppAdmin(admin.ModelAdmin):
    list_display = ('nickname', 'updated_at')
    list_filter = ('updated_at', )


class CourseTabAdmin(CourseAppAdmin):
    list_display = ('title', 'updated_at')


admin.site.register(Course, CourseTabAdmin)
admin.site.register(Teacher, CourseAppAdmin)
admin.site.register(Student, CourseAppAdmin)
admin.site.register(TeacherAssistant, CourseAppAdmin)
