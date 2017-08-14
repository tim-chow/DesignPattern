package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/12.
  */


abstract class Image1 {

  var imageImp : ImageImp
  def parseFile(fileName:String)
}

class  JPGImage extends Image1{
   var imageImp: ImageImp = _

  override def parseFile(fileName: String) = {
    imageImp.doPaint(new Matrix)
    println(fileName+",格式为JPG")
  }
}

class  PNGImage extends Image1{
  override var imageImp: ImageImp = _

  override def parseFile(fileName: String) = {
    imageImp.doPaint(new Matrix)
    println(fileName+",格式为PNG")
  }
}


class  GIFImage extends Image1{
  override var imageImp: ImageImp = _

  override def parseFile(fileName: String) = {
    imageImp.doPaint(new Matrix)
    println(fileName+",格式为GIF")
  }
}


class Matrix

trait ImageImp {
  def doPaint(m: Matrix)
}

class WindowsImp extends ImageImp {
  override def doPaint(m: Matrix) = println("在windows操作系统中显示图片：")
}

class LinuxImp extends ImageImp {
  override def doPaint(m: Matrix) = println("在linux操作系统中显示图片")
}

class MaxOsImp extends ImageImp {
  override def doPaint(m: Matrix) = println("在Mac操作系统中显示图片")
}


object Client extends App {

  val image: Image1 = new JPGImage()
  val imageImp: ImageImp = new WindowsImp
  image.imageImp = imageImp

  image.parseFile("你好")
}
