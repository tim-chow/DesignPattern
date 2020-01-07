from abc import ABCMeta, abstractmethod


class Phone(object):
    def __init__(self, branch):
        self._branch = branch
        self._model = None
        self._color = None

    @property
    def model(self):
        return self._model

    @model.setter
    def model(self, model):
        self._model = model

    @property
    def color(self):
        return self._color

    @color.setter
    def color(self, color):
        self._color = color

    def __str__(self):
        return "%s{model=%s, color=%s}" % (
            self._branch, 
            self._model,
            self._color)
    __repr__ = __str__


class IPhone(Phone):
    def __init__(self):
        super(IPhone, self).__init__(
            self.__class__.__name__)


class Oneplus(Phone):
    def __init__(self):
        super(Oneplus, self).__init__(
            self.__class__.__name__)


class PhoneFactory:
    __metaclass__ = ABCMeta

    def create(self, model, color, *a, **kw):
        phone = self.create_phone(*a, **kw)
        phone.model = model
        phone.color = color
        return phone

    @abstractmethod
    def create_phone(self, *a, **kw):
        pass


class IPhoneFactory(PhoneFactory):
    def create_phone(self, *a, **kw):
        return IPhone()


class OneplusFactory(PhoneFactory):
    def create_phone(self, *a, **kw):
        return Oneplus()


if __name__ == "__main__":
    print(IPhoneFactory().create("IPhone8", "black"))
    print(OneplusFactory().create("Oneplus3T", "white"))
