package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/9.
  * 抽象工厂
  */

/**
  * trait类比java的interface object类比java的class
  */
trait CarFactory {
  def displayColor(): Color
  def displayPrice(): Price
}

trait Color {
  def display(): Unit
}

trait Price {
  def display(): Unit
}


object AudiFactory extends CarFactory {

    class AudiColor extends Color {
      override def display(): Unit = println("audi color")
    }


    class AudiPrice extends Price {
      override def display(): Unit = println("audi price")
    }

  override def displayColor() = new AudiColor()

  override def displayPrice() = new AudiPrice()
}

object BMWFactory extends CarFactory {


  class BMWColor extends Color {
    override def display(): Unit = println("BMW color")
  }


  class BMWPrice extends Price {
    override def display(): Unit = println("BMW price")
  }

  override def displayColor() = new BMWColor()
  override def displayPrice() = new BMWPrice()

}



object AbstractFactory {
  def main(args: Array[String]): Unit = {

    var audiFactory = AudiFactory
    audiFactory.displayColor().display()
    audiFactory.displayPrice().display()

    var bmwFactory = BMWFactory
    bmwFactory.displayColor().display()
    bmwFactory.displayPrice().display()

  }
}
