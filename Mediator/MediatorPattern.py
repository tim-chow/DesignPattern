# coding: utf8

import threading


class User(object):
    def __init__(self, name, chat_room):
        self._name = name
        # 具体同事对象需要保存具体中介者对象的引用
        self._chat_room = chat_room
        self._chat_room.add_user(self)

    @property
    def name(self):
        return self._name

    def receive_message(self, from_user_name, message):
        print("[%s receives a message from %s]: %s" %
              (self._name, from_user_name, message))

    def send_message(self, message, to_user_names=None):
        self._chat_room.send_message(self.name, message, to_user_names)


class ChatRoom(object):
    instance = None
    lock = threading.Lock()

    @classmethod
    def get_instance(cls):
        if cls.instance is not None:
            return cls.instance

        with cls.lock:
            if cls.instance is None:
                cls.instance = ChatRoom()
            return cls.instance

    def __init__(self):
        # 具体中介者对象需要保存所有具体同事对象的引用
        # + 具体中介者对象
        # + + 既需要从具体同事对象那里接收消息
        # + + 又需要向具体同事对象那里发送消息
        self._users = {}

    def add_user(self, user):
        self._users[user.name] = user

    def remove_user(self, user_name):
        return self._users.pop(user_name)

    def send_message(self, from_user_name, message, to_user_names=None):
        if to_user_names is None:
            for user_name, user in self._users.iteritems():
                if user_name == from_user_name:
                    continue
                user.receive_message(from_user_name, message)
            return
        for user_name in to_user_names:
            if user_name in self._users:
                self._users[user_name].receive_message(from_user_name, message)


def test():
    chat_room = ChatRoom.get_instance()

    users = []
    for i in range(1, 10):
        users.append(User("user_%d" % i, chat_room))

    users[0].send_message("hello everyone")
    users[1].send_message("%s, I have something to ask you" % users[2].name,
                          [users[2].name])
    users[2].send_message("please say", [users[1].name])


if __name__ == "__main__":
    test()
