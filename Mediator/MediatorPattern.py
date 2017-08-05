#coding: utf8

import threading

class User:
    def __init__(self, name, chatroom):
        self._name = name
        # 具体同事对象 需要保存 具体中介者对象 的引用
        self._chatroom = chatroom
        self._chatroom.add_user(self)

    @property
    def name(self):
        return self._name

    def receive_message(self, from_user_name, message):
        print("[%s recieves a message from %s]: %s" %
            (self._name, from_user_name, message))

    def send_message(self, message, to_user_names=None):
        self._chatroom.send_message(self, message, to_user_names)

class ChatRoom:
    instance = None
    lock = threading.Lock()

    @classmethod
    def get_instance(cls):
        with cls.lock:
            if not cls.instance:
                cls.instance = ChatRoom()
            return cls.instance

    def __init__(self):
        # 具体中介者对象 需要保存 所有的 具体同事对象 的引用
        # + 具体中介者对象
        # + + 既需要从 具体同事对象 那里接收消息
        # + + 又需要向 具体同事对象 那里发送消息
        self._users = {}

    def add_user(self, user):
        self._users[user.name] = user

    def remove_user(self, user_name):
        return self._users.pop(user_name, None)

    # 抽象中介者角色需要 定义 同事对象到中介者对象 之间的接口
    def send_message(self, from_user, message, to_user_names=None):
        if to_user_names is None:
            for user_name, user in self._users.iteritems():
                user.receive_message(from_user.name, message)
            return
        for user_name in to_user_names:
            if user_name in self._users:
                self._users[user_name].receive_message(from_user.name, message)

if __name__ == "__main__":
    chatroom = ChatRoom.get_instance()

    for i in range(1, 10):
        username = "user%d" % i
        locals()[username] = User(username, chatroom)
    user1.send_message("hello everyone")

    user1.send_message("user3, I have something to ask you.", ["user3"])
    user3.send_message("user1, please say,", ["user1"])

