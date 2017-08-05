#coding: utf8

import time

"""
python在语言层面就支持装饰器模式，
下面用一个绕口的方式，说明python中的装饰器：
    装饰器是一个可调用对象
    装饰器需要返回一个可调用对象
    装饰器用来装饰可调用对象
python中的可调用对象包括：
    函数
    方法
    实现了__call__方法的对象
    类
装饰器分为两类：
    不带参数的装饰器
    带参数的装饰器

@decorator
def func():
    pass
等同于 func = decorator(func)

@decorator2
@decorator1
def func():
    pass
等同于 func = decorator2(decorator1(func))

@decorator(arg)
def func():
    pass
等同于 func = decorator(arg)(func)
"""  

class LogDecorator:
    def __init__(self, wrapped_callable_object):
        self._callable = wrapped_callable_object

    def __call__(self, *a, **kw):
        print("begin to invoked %s with %s, %s" % (self._callable, a, kw))
        result = self._callable(*a, **kw)
        print("invoked succesfully")
        return result

def execution_time_decorator(func):
    def _inner(*a, **kw):
        start_time = time.time()
        result = func(*a, **kw)
        print("%s executed in %s s" % (func, time.time()-start_time))
        return result
    return _inner

if __name__ == "__main__":
    @LogDecorator
    @execution_time_decorator
    def func1():
        time.sleep(1)

    func1()

