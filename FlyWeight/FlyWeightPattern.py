# coding: utf8

import threading
import abc


class Singleton(object):
    """单例类的父类"""
    instances = {}
    lock = threading.Lock()

    def __new__(cls, *args, **kwargs):
        if cls in Singleton.instances:
            return Singleton.instances[cls]

        with Singleton.lock:
            if cls not in Singleton.instances:
                Singleton.instances[cls] = super(Singleton, cls) \
                    .__new__(cls, *args, **kwargs)
            return Singleton.instances[cls]


class Shape(object):
    """"抽象享元角色 - 形状"""
    def __init__(self, color):
        self._color = color

    @abc.abstractmethod
    def draw(self, *args, **kwargs):
        pass

    @property
    def color(self):
        return self._color


class Circle(Shape):
    """具体享元角色 - 圆形"""
    def draw(self, radius):
        print("draw a %s circle with radius %f" % (self.color, radius))


class Rectangle(Shape):
    """具体享元角色 - 矩形"""
    def draw(self, width, height):
        print("draw a %s rectangle with width %f and height %f" % (
            self.color,
            width,
            height
        ))


class ShapeFactory(Singleton):
    """享元工厂"""
    name_to_shape = {"circle": Circle, "rectangle": Rectangle}
    map = {}
    lock = threading.Lock()

    @classmethod
    def get_shape(cls, name, color):
        if (name, color) in cls.map:
            return cls.map[(name, color)]
        if name not in cls.name_to_shape:
            raise ValueError("unknown name")
        with cls.lock:
            if (name, color) not in cls.map:
                cls.map[(name, color)] = cls.name_to_shape[name](color)
            return cls.map[(name, color)]


if __name__ == "__main__":
    import unittest


    class FlyWeightTest(unittest.TestCase):
        def testFlyWeight(self):
            circle_red = ShapeFactory.get_shape("circle", "red")
            for _ in range(10):
                self.assertIs(circle_red, ShapeFactory.get_shape("circle", "red"))
            
            circle_blue = ShapeFactory.get_shape("circle", "blue")
            self.assertTrue(circle_blue is not circle_red)

    unittest.main()
