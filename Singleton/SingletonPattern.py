# coding: utf8

import threading

"""
__new__(cls, *a, **kw) 是新式类才有的魔术方法，
    它始终是静态方法，即使没使用 @staticmethod 装饰。
    它才是类真正的构造方法。
    因此该方法需要返回类的一个实例。
    其中 cls 是要被实例化的类。

解释器在创建新式类的对象时，
    首先会调用类的 __new__() 方法，
    如果类没提供该方法，则调用其父类的该方法，...，
    以此类推，一直到 object。

在调用 __new__() 方法，创建完对象之后，
    才会调用 __init__() 方法，对对象进行初始化。
    因此，可以把 __init__() 看作是初始化方法
"""


class Singleton1(object):
    instances = {}
    lock = threading.Lock()

    def __new__(cls, *a, **kw):
        if cls in Singleton1.instances:
            return Singleton1.instances[cls]

        with Singleton1.lock:
            if cls not in Singleton1.instances:
                Singleton1.instances[cls] = \
                    super(Singleton1, cls).__new__(cls, *a, **kw)
            return Singleton1.instances[cls]


class Singleton2(object):
    lock = threading.Lock()
    instance = None

    def __init__(self, *a, **kw):
        # 初始化对象
        pass

    @classmethod
    def get_instance(cls, *a, **kw):
        if cls.instance is not None:
            return cls.instance

        with cls.lock:
            if cls.instance is None:
                cls.instance = Singleton2(*a, **kw)
            return cls.instance

    # 其它方法


"""
在 Python 中一切皆对象，类也是对象。
类用来创建对象；而元类用来创建类。
通过元类可以在创建类的时候，动态的修改类
"""


def SingletonMeta(name, bases, attrs):
    cls = type(name, bases, attrs)
    cls.instance = cls()
    return cls


if __name__ == "__main__":
    import unittest


    class SingletonTest(unittest.TestCase):
        def testSingleton1(self):
            class Test(Singleton1):
                pass
            self.assertIs(Test(), Test())

        def testSingleton2(self):
            self.assertIs(Singleton2.get_instance(), Singleton2.get_instance())

        def testSingletonMeta(self):
            class Test(object):
                __metaclass__ = SingletonMeta

            self.assertIs(Test.instance, Test.instance)

    unittest.main()
