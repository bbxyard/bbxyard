
# 测试等号区别
# ref: https://www.cnblogs.com/wanqieddy/archive/2011/09/21/2184257.html
x1 = foo
y1 = $(x1) bar
x1 = FOO

x2 := foo
y2 := $(x2) bar
x2 := FOO

x3  = foo
y3 := $(x3) bar
x3 := FOO

x4 := foo
y4 = $(x4) bar
x4 := FOO

gm:
	@echo x1=$(x1) y1=$(y1)
	@echo x2=$(x2) y2=$(y2)
	@echo x3=$(x3) y3=$(y3)
	@echo x4=$(x4) y4=$(y4)

foo:
bar:

foobar: foo bar
	@echo Deps -- "$$^" $^
	@echo Deps -- "$$<" $<

/tmp/etc/passwd:
	@echo Goal -- '$$@' $@
