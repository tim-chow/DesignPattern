package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/12.
  */

class Screen(var long: Int = 1, var wide: Int = 1) {
  private var screenState: ScreenState = NormalScreenState(this)
  show()
  def onClick(): Unit = screenState match {
    case NormalScreenState(_) =>
      this.wide += this.wide
      this.long += this.long
      screenState = LargerScreenState(this)
      print("点击：")
      screenState.display()
    case LargerScreenState(_) =>
      this.wide += this.wide
      this.long += this.long
      screenState = LargestScreenState(this)
      print("点击：")
      screenState.display()
    case LargestScreenState(_) =>
      this.wide = this.wide / 4
      this.long = this.long / 4
      screenState = NormalScreenState(this)
      print("点击：")
      screenState.display()
  }

  def show(): Unit ={
    print("显示：")
    screenState.display()
  }
}
sealed abstract  class  ScreenState {
  def display(): Unit = {}
}
case class NormalScreenState(screen: Screen) extends ScreenState {
  override def display(): Unit = {
    println(s"正常大小,长:${screen.wide},宽:${screen.long}")
  }
}

case class LargerScreenState(screen: Screen) extends ScreenState {
  override def display(): Unit = {
    println(s"两倍大小,长:${screen.wide},宽:${screen.long}")
  }
}

case class LargestScreenState(screen: Screen) extends ScreenState {
  override def display(): Unit = {
    println(s"四倍大小,长:${screen.wide},宽:${screen.long}")
  }
}

object State extends App {
  val screen = new Screen
  screen.show()
  screen.onClick()
  screen.show()
  screen.onClick()
  screen.onClick()
  screen.onClick()

}