# coding: utf8

import copy

"""
copy.copy() 提供浅拷贝功能，
    浅拷贝只拷贝对象本身，不拷贝对象引用的其它对象

copy.deepcopy() 提供深拷贝功能，
    深拷贝不仅会拷贝对象本身，还会递归地拷贝对象中引用的其它对象
"""


class ProtoType(object):
    def copy(self):
        return copy.copy(self)

    def deep_copy(self):
        return copy.deepcopy(self)


if __name__ == "__main__":
    class Test(ProtoType):
        def __init__(self):
            self.obj = object()

    test = Test()

    assert test.copy() is not test.copy()
    assert test.copy().obj is test.copy().obj

    assert test.deep_copy() is not test.deep_copy()
    assert test.deep_copy().obj is not test.deep_copy().obj
