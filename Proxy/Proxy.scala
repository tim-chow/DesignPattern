package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/10.
  */

trait Image{
  def display(message:String)
}

object RealImage extends Image{
  override def display(message: String) = println(s"message is $message")
}

object ProxyImage extends Image{
  private val realImage = RealImage

  override def display(message: String) ={
    println(s"before...")
    realImage.display(s"delivering message: $message")
    println(s"after...")
  }
}


object Proxy {

  def main(args: Array[String]): Unit = {
    val proxyImage = ProxyImage
    proxyImage.display("hello")
  }
}
