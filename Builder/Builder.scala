package com.wchy.patterns

/**
  * Created by wangchangye on 17-8-10.
  */

class Product{
  var part1:String=_
  var part2:String=_

  override def toString: String = part1 +"  &&  " + part2
}
trait ProductBuilder{
  protected var product:Product
  def buildPart1:Unit
  def buildPart2:Unit
  def buildProduct:Product
}

object RealBuilder1 extends ProductBuilder{

  override protected var product:Product = new Product
  override def buildPart1: Unit = product.part1="part1 build from RealBuilder1"
  override def buildPart2: Unit = product.part2="part2 build from RealBuilder1"
  override def buildProduct: Product = product
}

object RealBuilder2 extends ProductBuilder{

  override protected var product:Product = new Product
  override def buildPart1: Unit = product.part1="part1 build from RealBuilder2"
  override def buildPart2: Unit = product.part2="part2 build from RealBuilder2"
  override def buildProduct: Product = product
}

object BuilderCommander{
  def buildPrduct(builder:ProductBuilder):Product = {
    builder.buildPart1
    builder.buildPart2
    var pro = builder.buildProduct
    pro
  }
}


object Builder {
  def main(args: Array[String]): Unit = {

    var commander = BuilderCommander
    var builder1 = RealBuilder1
    var builder2 = RealBuilder2

    println(commander.buildPrduct(builder1))
    println(commander.buildPrduct(builder2))

  }
}
