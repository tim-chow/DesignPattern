# coding: utf8

import abc


class Shape(object):
    __metaclass__ = abc.ABCMeta

    def __init__(self, color=None):
        self._color = color

    @abc.abstractmethod
    def draw(self):
        pass

    def draw_shape(self):
        self.draw()
        if self._color is not None:
            self._color.bepaint()

    def set_color(self, color):
        self._color = color


class CircleShape(Shape):
    def draw(self):
        print("画一个圆形")


class SquareShape(Shape):
    def draw(self):
        print("画一个正方形")


class Color(object):
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def bepaint(self):
        pass


class BlackColor(Color):
    def bepaint(self):
        print("染成黑色")


class GreenColor(Color):
    def bepaint(self):
        print("染成绿色")


def test():
    circle = CircleShape()
    square = SquareShape()

    black = BlackColor()
    green = GreenColor()

    circle.set_color(black)
    square.set_color(green)

    circle.draw_shape()
    square.draw_shape()


if __name__ == "__main__":
    test()
