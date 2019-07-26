from nose import with_setup
from .nt import NamedTupleDemo
from .dict import DictDemo
from .deque import DequeDemo


def setup_func():
    print("begin ...")


def teardown_func():
    print("end ...")


@with_setup(setup_func, teardown_func)
def test_hallo1():
    NamedTupleDemo.demo()
    print("hallo welt")


@with_setup(setup_func, teardown_func)
def test_dict():
    DictDemo.demo()


@with_setup(setup_func, teardown_func)
def test_deque():
    DequeDemo.demo()
