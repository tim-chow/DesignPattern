package com.wchy.patterns

import scala.collection.mutable

/**
  * Created by wangchangye on 2017/8/12.
  */
case class Coordinates(x:Int,y:Int)
abstract class IgoChessman {

  def color(): String
  def display(coordinates: Coordinates): Unit = {
    if (color() == "没有该颜色的棋子"){
      println(color())
    }else{
      println("棋子颜色：" + color() + "棋子位置：" + coordinates.x + "," + coordinates.y)
    }
  }
}

object BlackIgoChessman extends IgoChessman {
  override def color() = "黑色"
}

object WhiteIgoChessman extends IgoChessman {
  override def color() = "白色"
}

object IgoChessmanFactory {

  private val hm = new mutable.HashMap[String, IgoChessman] {}

  val black: IgoChessman = BlackIgoChessman
  val white: IgoChessman = WhiteIgoChessman
  hm.put("b", black)
  hm.put("w", white)

  def getIgoChessman(color: String): IgoChessman = hm.getOrElse(color, new IgoChessman {
    override def color(): String = "没有该颜色的棋子"
  })
}


object Flyweight extends App {

  val factory = IgoChessmanFactory

  val black1 = factory.getIgoChessman("b")
  val black2 = factory.getIgoChessman("b")
  val black3 = factory.getIgoChessman("b")
  black1.display(Coordinates(1, 2))
  black2.display(Coordinates(1, 4))
  black3.display(Coordinates(1, 3))

  println("判断棋子是否相同：" + black1.eq(black2))


  val white1 = factory.getIgoChessman("w")
  val white2 = factory.getIgoChessman("w")
  white1.display(Coordinates(0,0))
  white2.display(Coordinates(1,1))
  println("判断棋子是否相同：" + white1.eq(white2))

  val error = factory.getIgoChessman("c")

  error.display(Coordinates(1, 0))


}
