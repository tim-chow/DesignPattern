#coding: utf8

import copy
import unittest

"""
copy.copy是浅拷贝，
    浅拷贝只拷贝对象本身，而不拷贝对象引用的其他对象
copy.deepcopy是深拷贝，
    深拷贝不仅会拷贝对象本身，还会**递归地**拷贝对象中引用的其他对象
"""

class Test:
    def __init__(self):
        self.list = [1, 2, 3]
        self.str = "str"

class CopyTest(unittest.TestCase):
    def test_shallow_copy(self):
        test = Test()
        test_shallow_copy = copy.copy(test)

        test.list.pop()
        self.assertEqual(test_shallow_copy.list, [1, 2])

    def test_immutable_types(self):
        test = Test()
        test_shallow_copy = copy.copy(test)

        test.str = "str2"
        self.assertNotEqual(test_shallow_copy.str, "str2")
        self.assertEqual(test_shallow_copy.str, "str")

    def test_deep_copy(self):
        test = Test()
        test_deep_copy = copy.deepcopy(test)

        test.list.pop()
        self.assertNotEqual(test.list, test_deep_copy.list)
        self.assertEqual(test_deep_copy.list, [1, 2, 3])

if __name__ == "__main__":
    unittest.main()

