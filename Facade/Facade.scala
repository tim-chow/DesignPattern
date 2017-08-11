
package com.wchy.patterns

/**
  * Created by wangchangye on 17-8-10.
  */


trait Shape{
  def draw:Unit
}

object Rectangle extends Shape{
  override def draw: Unit = println("This is Rectangle")
}
object Square extends Shape{
  override def draw: Unit = println("This is Square")
}
object Circle extends Shape{
  override def draw: Unit = println("This is Circle")
}

object ShapeMaker {
  var rectangle = Rectangle
  var square = Square
  var circle = Circle

  def drawRectangle:Unit = rectangle.draw
  def drawSquare:Unit = square.draw
  def drawCircle:Unit = circle.draw
}


object Facade {
  def main(args: Array[String]): Unit = {
    val shapeMaker = ShapeMaker
    shapeMaker.drawCircle
    shapeMaker.drawRectangle
    shapeMaker.drawSquare
  }
}
