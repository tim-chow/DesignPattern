class Facade(object):
    def __init__(self, subSystem1, subSystem2, subSystem3):
        self.subSystem1 = subSystem1
        self.subSystem2 = subSystem2
        self.subSystem3 = subSystem3

    def method1(self):
        self.subSystem1.method1()

    def method2(self):
        self.subSystem2.method2()

    def method3(self):
        self.subSystem3.method3()


class SubSystem1(object):
    def method1(self):
        print("method1 in SubSystem1")


class SubSystem2(object):
    def method2(self):
        print("method2 in SubSystem2")


class SubSystem3(object):
    def method3(self):
        print("method3 in SubSystem3")


if __name__ == "__main__":
    facade = Facade(SubSystem1(), SubSystem2(), SubSystem3())
    facade.method1()
    facade.method2()
    facade.method3()
