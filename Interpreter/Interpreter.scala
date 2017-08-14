package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/12.
  */

trait Action
trait Direction

object Up extends Direction
object Down extends Direction
object Left extends Direction
object Right extends Direction
object Run extends Action
object Move extends Action
object Then extends Action


class Robot(var x: Int = 0, var y: Int = 0) {
  private var actions: String = null
  private var directions: Direction = null
  private var tag: String = null
  def up(action: Action): this.type = {
    action match {
      case Run => actions = "快速移动"
      case Move => actions = "移动"
    }
    directions = Up
    this
  }
  def down(action: Action): this.type = {
    action match {
      case Run => actions = "快速移动"
      case Move => actions = "移动"
    }
    directions = Down
    this
  }

  def left(action: Action): this.type = {
    action match {
      case Run => actions = "快速移动"
      case Move => actions = "移动"
    }
    directions = Left
    this
  }
  def right(action: Action): this.type = {
    action match {
      case Run => actions = "快速移动"
      case Move => actions = "移动"
    }
    directions = Right
    this
  }

  def length(length: Int): this.type = {
    directions match {
      case Up => y += length; tag = "向上"
      case Down => y -= length; tag = "向下"
      case Left => x -= length; tag = "向左"
      case Right => x += length; tag = "向右"
    }
    println(s"机器人$tag$actions :$length ,此时的位置为x坐标:$x,y坐标:$y")
    this
  }

  def and(word: Then.type) = this
}
object Interpreter extends App {
  val robot = new Robot
  robot up Run length 10 and Then left Move length 5 and Then right Run length 3 and Then down Move length 8
}
