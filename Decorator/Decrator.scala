package com.wchy.patterns

/**
  * Created by wangchangye on 17-8-10.
  */

trait Shaper{
  def draw()
}

object Rectangler extends Shaper{
  override def draw: Unit = println("Shape is Rectangle!")
}
object Circler extends Shaper{
  override def draw: Unit = println("Shape is Circle!")
}

case class ShapeDecorator(shape:Shaper) extends Shaper{
  override def draw: Unit = shape.draw
}
class RedShapeDecotator(shaper: Shaper) extends ShapeDecorator(shaper){
  override def draw: Unit = {
    printRed()
    super.draw
    dryRed()
  }
  private def printRed() = println("red start -----------------")
  private def dryRed() = println("red dry ----------------")
}
class BlackShapeDecotator(shaper: Shaper) extends ShapeDecorator(shaper){
  override def draw: Unit = {
    printBlack()
    super.draw
    dryBlack()
  }
  private def printBlack() = println("black start -----------------")
  private def dryBlack() = println("black dry ----------------")
}

object Decrator {
  def main(args: Array[String]): Unit = {
    val recshape:Shaper = Rectangler
    val circleshape:Shaper = Circler

    val redShapeDecotator = new RedShapeDecotator(recshape)
    val blackShapeDecotator = new BlackShapeDecotator(recshape)

    redShapeDecotator.draw
    blackShapeDecotator.draw

  }
}
