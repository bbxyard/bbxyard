

def dyn_import(mod_name):
    obj = __import__(mod_name, fromlist=['xx'])
    return obj


def verify_then_run(obj, fun_name):
    if hasattr(obj, fun_name):
        f = getattr(obj, fun_name)
        f()
    else:
        print("404")


def verify_then_by_n2n(mod_name, fun_name):
    obj = dyn_import(mod_name)
    verify_then_run(obj, fun_name)


def test_func():
    print("start")
    import yvhai.demo.feature.reflect.xdefs as defs
    verify_then_run(defs, "login")
    verify_then_run(defs, "logout")
    verify_then_run(defs, "home")


def test_auto_import():
    print("此为动态导入")
    verify_then_by_n2n("yvhai.demo.feature.reflect.xdefs", "login")
    verify_then_by_n2n("yvhai.demo.feature.reflect.xdefs", "logout")
    verify_then_by_n2n("yvhai.demo.feature.reflect.xdefs", "home")
