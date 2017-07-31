from abc import ABCMeta, abstractmethod

class Car:
    __metaclass__ = ABCMeta

    @abstractmethod
    def say(self):
        pass

class AudiCar(Car):
    pass

class AudiA4(AudiCar):
    def say(self):
        print("I am Audi A4")

class AudiQ5(AudiCar):
    def say(self):
        print("I am Audi Q5")

class BMWCar(Car):
    pass

class BMWX1(Car):
    def say(self):
        print("I am BMW X1")

class BMW3X(Car):
    def say(self):
        print("I am BMW 3X")

class CarFactory:
    __metaclass__ = ABCMeta

    def create_audi(self):
        return self.create_audi_car()
    
    def create_audi_car(self):
        pass

    def create_bmw(self):
        return self.create_bmw_car()

    def create_bmw_car(self):
        pass

class AudiA4Factory(CarFactory):
    def create_audi_car(self):
        return AudiA4()

class AudiQ5Factory(CarFactory):
    def create_audi_car(self):
        return AudiQ5()

class BMWX1Factory(CarFactory):
    def create_bmw_car(self):
        return BMWX1()

class BMW3XFactory(CarFactory):
    def create_bmw_car(self):
        return BMW3X()

def test():
    AudiA4Factory().create_audi().say()
    AudiQ5Factory().create_audi().say()
    BMWX1Factory().create_bmw().say()
    BMW3XFactory().create_bmw().say()

if __name__ == "__main__":
    test()

