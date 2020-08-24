# coding: utf8


class Context(object):
    """环境角色"""
    def __init__(self):
        self._open = OpenState()
        self._closed = ClosedState()
        self._cooling = CoolingState()
        self._heating = HeatingState()
        self._current_state = self._closed

    def open(self):
        if self._current_state.open():
            self._current_state = self._open
        else:
            print("can't change to open from %s" % self._current_state.get_name())

    def close(self):
        if self._current_state.close():
            self._current_state = self._closed
        else:
            print("can't change to closed from %s" % self._current_state.get_name())

    def cool(self):
        if self._current_state.cool():
            self._current_state = self._cooling
        else:
            print("can't change to cooling from %s" % self._current_state.get_name())

    def heat(self):
        if self._current_state.heat():
            self._current_state = self._heating
        else:
            print("can't change to heating from %s" % self._current_state.get_name())


class AirConditionState(object):
    """抽象状态角色"""
    def open(self):
        return False

    def close(self):
        return False

    def cool(self):
        return False

    def heat(self):
        return False

    def get_name(self):
        return self.__class__.__name__[:-1 * len("State")].lower()


class OpenState(AirConditionState):
    """开机状态"""
    def close(self):
        print("closed")
        return True

    def cool(self):
        print("cooling")
        return True

    def heat(self):
        print("heating")
        return True


class ClosedState(AirConditionState):
    """关机状态"""
    def open(self):
        print("open")
        return True


class CoolingState(AirConditionState):
    """制冷状态"""
    def close(self):
        print("closed")
        return True

    def heat(self):
        print("heating")
        return True


class HeatingState(AirConditionState):
    """制冷状态"""
    def close(self):
        print("closed")
        return True

    def cool(self):
        print("cooling")
        return True


if __name__ == "__main__":
    context = Context()
    context.open()
    context.heat()
    context.close()
    context.cool()
