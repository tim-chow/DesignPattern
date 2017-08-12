package com.wchy.patterns

import scala.collection.mutable.ArrayBuffer

/**
  * Created by wangchangye on 2017/8/12.
  */


trait AbstractFile{
  def add(adstractFile:AbstractFile) = println("whoops!")
  def remove(abstractFile: AbstractFile) = println("whoops!")
  def child(i:Int):AbstractFile = {
    println("whoops!")
    null
  }
  def killVirus():Unit
}

case class ImageFile(name:String) extends AbstractFile{
  override def killVirus() = println(s"killing virus -> $name")
}

case class VideoFile(name:String) extends AbstractFile{
  override def killVirus() = println(s"killing virus -> $name")
}

case class Folder(name:String) extends AbstractFile{
  var fileList = new ArrayBuffer[AbstractFile]()

  override def add(adstractFile: AbstractFile) = fileList += adstractFile
  override def remove(abstractFile: AbstractFile) = fileList += abstractFile
  override def killVirus(): Unit = {
    for (file <- fileList) file.killVirus()
  }
}

object CompositePattern extends App{
  val folder1 = Folder("图像资料")
  val folder2 = Folder("文本资料")
  val folder3 = Folder("视频资料")

  val file1 = ImageFile("1.jpg")
  val file2 = ImageFile("2.jpg")
  val file3 = VideoFile("1.avi")
  val file4 = VideoFile("2.avi")

  folder1.add(file1)
  folder1.add(file2)
  folder1.add(file3)
  folder1.add(file4)

  folder2.add(file1)
  folder2.add(file2)
  folder2.add(file3)
  folder2.add(file4)

  folder1.killVirus()
  folder2.killVirus()


}
