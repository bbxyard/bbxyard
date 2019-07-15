from django.db import models


# Create your models here.


class BuiltInType(models.Model):
    # 自增
    # auto = models.AutoField()
    # big_auto = models.BigAutoField()

    # 二进制
    binary = models.BinaryField()

    # 布尔型
    boolean = models.BooleanField()
    null_boolean = models.NullBooleanField()

    # 整型
    positive_small_integer = models.PositiveSmallIntegerField(db_column="age")  # 5B
    small_integer = models.SmallIntegerField(primary_key=False)  # 6B
    positive_integer = models.PositiveIntegerField()  # 10B
    integer = models.IntegerField(verbose_name="11 Bytes")  # 11B
    big_integer = models.BigIntegerField(unique=True)  # 20B

    # 字符串类型
    char = models.CharField(max_length=1024, null=True, blank=True, db_index=True, help_text="varchar")
    text = models.TextField(help_text="这个是longtext")

    # 时间日期
    date = models.DateField(unique_for_date=True, auto_now=True)
    time = models.TimeField()
    date_time = models.DateTimeField(editable=False, unique_for_month=True, auto_now_add=True)
    duration = models.DurationField()  # int, 基于Python timedelta

    # 浮点型
    float = models.FloatField()
    decimal = models.DecimalField(max_digits=4, decimal_places=2)  # 16.18, 99.06

    # 其它字段
    email = models.EmailField()
    image = models.ImageField()
    file = models.FileField()
    file_path = models.FilePathField()
    url = models.URLField()
    uuid = models.UUIDField()
    # ip_address = models.IPAddressField()
    generic_ip_address = models.GenericIPAddressField()

    class Meta:
        db_table = "misc_builtin_type"


class AddressInfo(models.Model):
    """省市县地址信息"""
    address = models.CharField(max_length=200, null=True, blank=True, verbose_name="地址")
    pid = models.ForeignKey('self', null=True, blank=True, on_delete=models.CASCADE, verbose_name="自关联")
    note = models.CharField(max_length=200, null=True, blank=True, verbose_name="说明")

    def __str__(self):
        return self.address

    class Meta:
        db_table = 'misc_address'
        # ordering = ['pid']
        verbose_name = "省市县地址信息"
        verbose_name_plural = "省市县地址信息"
        # abstract = True
        # permissions = (("定义好的权限", "权限说明"), )
        # managed = False
        unique_together = ('address', 'note')
        # app_label = 'course'
        # db_tablespace = ''


# class A(models.Model):
#     onetoone = models.OneToOneField(Test, related_name="one")
#
#
# class B(models.Model):
#     foreign = models.ForeignKey(A, on_delete=models.CASCADE)  # 删除级联
#     # foreign = models.ForeignKey(A, on_delete=models.PROTECT)
#     # foreign = models.ForeignKey(A, on_delete=models.SET_NULL, null=True, blank=True)  # 删除置空
#     # foreign = models.ForeignKey(A, on_delete=models.SET_DEFAULT, default=0)
#     # foreign = models.ForeignKey(A, on_delete=models.DO_NOTHING)
#     # foreign = models.ForeignKey(A, on_delete=models.SET)
#
#
# class C(models.Model):
#     manytomany = models.ManyToManyField(B)


# 1.所有字段都有的参数
# 2.个别字段才有的参数
# 3.关系型字段的参数


"""
on_delete 当一个被外键关联的对象被删除时，Django将模仿on_delete参数定义的SQL约束执行相应操作
    如下6种操作
    CASCADE：模拟SQL语言中的ON DELETE CASCADE约束，将定义有外键的模型对象同时删除！（该操作为当前Django版本的默认操作！）
    PROTECT:阻止上面的删除操作，但是弹出ProtectedError异常
    SET_NULL：将外键字段设为null，只有当字段设置了null=True时，方可使用该值。
    SET_DEFAULT:将外键字段设为默认值。只有当字段设置了default参数时，方可使用。
    DO_NOTHING：什么也不做。
    SET()：设置为一个传递给SET()的值或者一个回调函数的返回值。注意大小写。
"""


class Teacher(models.Model):
    """讲师信息表"""
    nickname = models.CharField(max_length=64, primary_key=True, db_index=True, verbose_name="昵称")
    introduction = models.TextField(default="这们同学很懒，啥也没有备注~", verbose_name="简介")
    fans = models.PositiveIntegerField(default=0, verbose_name="粉丝数")
    created_at = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")
    updated_at = models.DateTimeField(auto_now=True, verbose_name="更新时间")

    def __str__(self):
        return self.nickname

    def __getstate__(self):
        return {"nickname": self.nickname, "fans": self.fans}

    class Meta:
        verbose_name = "讲师信息表"
        verbose_name_plural = verbose_name


class Course(models.Model):
    """课程信息表"""
    title = models.CharField(max_length=100, primary_key=True, db_index=True, verbose_name="课程名")
    teacher = models.ForeignKey(Teacher, null=True, blank=True, on_delete=models.CASCADE, verbose_name="课程讲师")
    type = models.CharField(choices=((1, "实战课"), (2, "免费课"), (0, "其它")), max_length=1, default=0, verbose_name="课程类型")
    price = models.PositiveSmallIntegerField(verbose_name="价格")
    volume = models.PositiveIntegerField(verbose_name="销量")
    online_date = models.DateField(verbose_name="上线时间")
    created_at = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")
    updated_at = models.DateTimeField(auto_now=True, verbose_name="更新时间")

    def __str__(self):
        return f"{self.get_type_display()}-{self.title}"

    class Meta:
        verbose_name = "课程信息表"
        verbose_name_plural = verbose_name
        get_latest_by = "created_at"


class Student(models.Model):
    """学生信息表"""
    nickname = models.CharField(max_length=64, primary_key=True, db_index=True, verbose_name="昵称")
    course = models.ManyToManyField(Course, verbose_name="课程")
    age = models.PositiveSmallIntegerField(verbose_name="年龄")
    gender = models.CharField(choices=((1, "男"), (2, "女"), (0, "保密")), max_length=1,
                              default=0, verbose_name="性别")
    study_time = models.PositiveIntegerField(default=0, verbose_name="学习时长(h)")
    created_at = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")
    updated_at = models.DateTimeField(auto_now=True, verbose_name="更新时间")

    def __str__(self):
        return self.nickname

    def __getstate__(self):
        return {"nickname": self.nickname}

    class Meta:
        verbose_name = "学生信息表"
        verbose_name_plural = verbose_name
        ordering = ["age"]


class TeacherAssistant(models.Model):
    """助教信息表"""
    nickname = models.CharField(max_length=64, primary_key=True, db_index=True, verbose_name="昵称")
    teacher = models.OneToOneField(Teacher, null=True, blank=True, on_delete=models.SET_NULL, verbose_name="讲师")
    hobby = models.CharField(max_length=100, null=True, blank=True, verbose_name="爱好")
    created_at = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")
    updated_at = models.DateTimeField(auto_now=True, verbose_name="更新时间")

    def __str__(self):
        return self.nickname

    class Meta:
        verbose_name = "助教信息表"
        verbose_name_plural = verbose_name
        db_table = "course_assistant"


class GroupConcat(models.Aggregate):
    """自定义实现聚合功能，实现GROUP_CONCAT功能"""
    function = "GROUP_CONCAT"
    template = '%(function)s(%(distinct)s%(expressions)s%(ordering)s%(separator)s)'

    def __init__(self, expression, distinct=False, ordering=None, separator=",", **extra):
        super(GroupConcat, self).__init__(expression,
                                          distinct='DISTINCT ' if distinct else '',
                                          ordering=' ORDER BY %s' % ordering if ordering is not None else '',
                                          separator=' SEPARATOR "%s"' % separator,
                                          output_field=models.CharField(),
                                          **extra)
