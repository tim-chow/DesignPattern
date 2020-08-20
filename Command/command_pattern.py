# coding: utf8

from abc import ABCMeta, abstractmethod


class AbstractCommand(object):
    """抽象命令"""
    __metaclass__ = ABCMeta

    def __init__(self, receiver):
        self._receiver = receiver

    @abstractmethod
    def execute(self):
        """
        执行
        """
        pass

    @abstractmethod
    def undo(self):
        """
        回滚
        """
        pass


class RefrigerationCommand(AbstractCommand):
    """制冷命令"""
    def execute(self):
        self._receiver.refrigerate()

    def undo(self):
        self._receiver.stop_to_refrigerate()


class StartingUpCommand(AbstractCommand):
    """开机命令"""
    def execute(self):
        self._receiver.start_up()

    def undo(self):
        raise RuntimeError("starting up command can not undo")


class ShuttingDownCommand(AbstractCommand):
    """关机命令"""
    def execute(self):
        self._receiver.shut_down()

    def undo(self):
        raise RuntimeError("shutting down command can not undo")


class AirCondition(object):
    """接收者 - 空调"""
    def __init__(self):
        self._started = False
        self._refrigerating = False

    def start_up(self):
        if not self._started:
            self._started = True
            print("开机成功")
        else:
            print("已经处于开机状态，无需重复开机")

    def shut_down(self):
        if self._started:
            self._started = False
            self._refrigerating = False
            print("关机成功")
        else:
            print("已经处于关机状态，无需重复关机")

    def refrigerate(self):
        if not self._started:
            print("关机状态，无法执行制冷命令")
            return
        if self._refrigerating:
            print("已经在制冷状态，无需重复制冷")
            return

        self._refrigerating = True
        print("开始制冷")

    def stop_to_refrigerate(self):
        if not self._started:
            print("关机状态，无法执行停止制冷命令")
            return

        if not self._refrigerating:
            print("当前未制冷，无需停止制冷")
            return

        self._refrigerating = False
        print("停止制冷")


class RemoteController(object):
    """调用者 - 遥控器"""
    def __init__(self):
        self._starting_up_commander = None
        self._shutting_down_commander = None
        self._refrigeration_commander = None

    def set_starting_up_commander(self, commander):
        self._starting_up_commander = commander

    def set_shutting_down_commander(self, commander):
        self._shutting_down_commander = commander

    def set_refrigeration_commander(self, commander):
        self._refrigeration_commander = commander

    def start_up(self):
        self._starting_up_commander.execute()

    def shut_down(self):
        self._shutting_down_commander.execute()

    def refrigerate(self):
        self._refrigeration_commander.execute()

    def stop_to_refrigerate(self):
        self._refrigeration_commander.undo()


class Client(object):
    """客户端"""
    def __init__(self):
        self._invoker = self.assemble()

    @staticmethod
    def assemble():
        """
        组装命令对象和接收者
        """
        # 创建接收者
        air_condition = AirCondition()

        # 创建命令，并设置接收者
        starting_up_commander = StartingUpCommand(air_condition)
        shutting_down_commander = ShuttingDownCommand(air_condition)
        refrigeration_commander = RefrigerationCommand(air_condition)

        # 创建接收者，并设置命令对象
        remote_controller = RemoteController()
        remote_controller.set_starting_up_commander(starting_up_commander)
        remote_controller.set_shutting_down_commander(shutting_down_commander)
        remote_controller.set_refrigeration_commander(refrigeration_commander)

        return remote_controller

    def execute(self):
        self._invoker.start_up()
        self._invoker.refrigerate()
        self._invoker.stop_to_refrigerate()
        self._invoker.shut_down()


if __name__ == "__main__":
    client = Client()
    client.execute()
