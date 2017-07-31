from abc import ABCMeta, abstractmethod

class Shape(object):
    def __init__(self, drawer=None):
        self._drawer = drawer

    @property
    def drawer(self):
        return self._drawer

    @drawer.setter
    def drawer(self, drawer):
        self._drawer = drawer

    def draw(self, *a, **kw):
        self._drawer.draw(*a, **kw)

class CircleShape(Shape):
    def __init__(self, radius):
        super(self.__class__, self).__init__(CircleDrawer())
        self._radius = radius

    def draw(self):
        super(self.__class__, self).draw(self._radius)

class RectangleShape(Shape):
    def __init__(self, length, width):
        super(self.__class__, self).__init__(RectangleDrawer())
        self._length = length
        self._width = width

    def draw(self):
        super(self.__class__, self).draw(self._length, self._width)

class Drawer:
    __metaclass__ = ABCMeta
   
    @abstractmethod
    def draw(self, *a, **kw):
        pass

class CircleDrawer(Drawer):
    def draw(self, radius):
        print("I am a circle, my radius is: %s^_^" % radius)

class RectangleDrawer(Drawer):
    def draw(self, length, width):
        print("I am a rectangle, my length is %s,"
            " width is %so_o" % (length, width))

if __name__ == "__main__":
    CircleShape(100).draw()
    RectangleShape(100, 200).draw()

