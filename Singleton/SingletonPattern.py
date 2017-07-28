#coding: utf8

import threading

"""
__new__(cls, *a, **kw) 是<strong>新式类</strong>才有的方法，
    它始终是静态方法，即使不使用@staticmethod装饰。
    它才是类实际上的 <strong>构造方法</strong> 。
        因此该方法需要返回类的一个实例。
    cls 代表要被实例化的类

解释器在创建 新式类 的对象的时候，
    首先会调用该类的 __new__ 方法，
        如果该类没提供，则调用其父类的，
            以此类推，一直到object。
在调用 __new__ 方法，创建完对象之后，
才会调用 __init__ 方法，对对象进行初始化。
    因此，可以把 __init__ 看作是初始化方法
"""

class Singleton(object): # must new-style class
    instances = {}
    lock = threading.Lock()

    # __new__ is static method, 
    # + even though there is no @staticmethod
    def __new__(cls, *a, **kw):
        with Singleton.lock:
            if cls not in Singleton.instances:
                Singleton.instances[cls] = \
                    super(Singleton, cls).__new__(cls, *a, **kw)
            return Singleton.instances[cls]

class Singleton2:
    lock = threading.Lock()
    instance = None

    @staticmethod
    def get_instance(*a, **kw):
        with Singleton2.lock:
            if not Singleton2.instance:
                Singleton2.instance = \
                    Singleton2(*a, **kw)
            return Singleton2.instance
    # other methods here ...

def test1():
    class Test(Singleton): 
        def __init__(self):
            print("__init__ is invoked")
    print(Test() == Test())

def test2():
    print(Singleton2.get_instance() == Singleton2.get_instance())


"""
在Python中一切皆对象，所以类也是对象。
类是用来创建对象的；而元类则是用来创建类的。

通过元类可以在创建类的时候，动态的修改类
"""

def SingletonMeta(name, bases, attrs):
    print name, bases, attrs
    cls = type(name, bases, attrs)
    cls.instance = cls() # 添加一个类属性 instance，
                        # 其值是类的一个实例
    return cls

class Singleton3:
    __metaclass__ = SingletonMeta

    def __init__(self):
        pass

def test3():
    print Singleton3.instance == Singleton3.instance

if __name__ == "__main__":
    test3()

