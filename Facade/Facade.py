class SubSystem1:
    def method1(self):
        print("this is method1 of %s" % self.__class__.__name__)

class SubSystem2:
    def method2(self):
        print("this is method2 of %s" % self.__class__.__name__)

class SubSystem3:
    def method3(self):
        print("this is method3 3 of %s" % self.__class__.__name__)

class Facade:
    def __init__(self):
        self._subSystem1 = SubSystem1()
        self._subSystem2 = SubSystem2()
        self._subSystem3 = SubSystem3()

    def __getattr__(self, attr):
        for subSystem in [self._subSystem1,
            self._subSystem2, 
            self._subSystem3]:
            if getattr(subSystem, attr, None):
                return getattr(subSystem, attr)
        raise AttributeError("Facade has no attribute: %s" % attr)

if __name__ == "__main__":
    facade = Facade()
    facade.method2()
    facade.method1()

