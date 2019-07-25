from nose import with_setup
from .nt import NamedTupleDemo

def setup_func():
    print("begin ...")


def teardown_func():
    print("end ...")


@with_setup(setup_func, teardown_func)
def test_hallo1():
    NamedTupleDemo.demo()
    print("hallo welt")
