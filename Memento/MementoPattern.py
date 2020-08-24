# coding: utf8

import datetime


class Memento(object):
    def __init__(self, old_content):
        self._content = old_content
        self._created_at = datetime.datetime.now()

    def get_content(self):
        return self._content

    @property
    def created_at(self):
        return self._created_at


class Originator(object):
    def __init__(self, content):
        self._content = content

    def set_content(self, new_content):
        self._content = new_content

    def create_memento(self):
        return Memento(self._content)

    def recover_from_memento(self, memento):
        self._content = memento.get_content()

    def get_content(self):
        return self._content


class Caretaker(object):
    def __init__(self, max_count):
        self._max_count = max_count
        self._list = []

    def add_memento(self, memento):
        if len(self._list) == self._max_count:
            self._list.pop(0)
        self._list.append(memento)

    def remove_memento(self, memento_id):
        self._list.pop(memento_id)

    def get_memento_count(self):
        return len(self._list)

    def get_memento(self, memento_id):
        return self._list[memento_id]


def test():
    caretaker = Caretaker(5)

    originator = Originator("content #1")
    for ind in range(2, 7):
        originator.set_content("content #%d" % ind)
        memento = originator.create_memento()
        caretaker.add_memento(memento)

    assert caretaker.get_memento_count() == 5

    for memento_id in range(caretaker.get_memento_count()):
        memento = caretaker.get_memento(memento_id)
        print("%s created at %s" % (memento.get_content(), memento.created_at))

    print("current content of originator is %s" % originator.get_content())

    originator.recover_from_memento(caretaker.get_memento(0))

    print("current content of originator is %s" % originator.get_content())


if __name__ == "__main__":
    test()
