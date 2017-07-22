class Context:
    def __init__(self):
        self._open = OpenState()
        self._close = CloseState()
        self._cool = CoolState()
        self._heat = HeatState()
        self._current_state = self._close;

    def to_open(self):
        if self._current_state.open():
            self._current_state = self._open

    def to_close(self):
        if self._current_state.close():
            self._current_state = self._close

    def to_cool(self):
        if self._current_state.cool():
            self._current_state = self._cool

    def to_heat(self):
        if self._current_state.heat():
            self._current_state = self._heat

class AirContiditoningState:
    def open(self):
        print("illegal state, unable to open")
        return False

    def close(self):
        print("illegal state, unable to close")
        return False

    def cool(self):
        print("illegal state, unable to cool")
        return False

    def heat(self):
        print("illegal state, unable to heat")
        return False

class OpenState(AirContiditoningState):
    def close(self):
        print("change to state: `close`")
        return True

    def cool(self):
        print("change to state: `cool`")
        return True

    def heat(self):
        print("change to state: `heat`")
        return True

class CloseState(AirContiditoningState):
    def open(self):
        print("change to state: `open`")
        return True

class CoolState(AirContiditoningState):
    def close(self):
        print("change to state: `close`")
        return True

    def heat(self):
        print("change to state: `heat`")
        return True

class HeatState(AirContiditoningState):
    def close(self):
        print("change to state: `close`")
        return True

    def cool(self):
        print("change to state: `cool`")
        return True

if __name__ == "__main__":
    context = Context()
    context.to_heat();
    context.to_open();
    context.to_heat();
    context.to_cool();
    context.to_close();
    context.to_close();

