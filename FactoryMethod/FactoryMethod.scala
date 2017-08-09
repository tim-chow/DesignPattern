package com.wchy.patterns


/**
  * Created by wangchangye on 2017/8/9.
  */


abstract class Phone(_color:String, _model:String, _brand:String){
  var color:String = _color
  var model:String = _model
  var brand:String = _brand

  override def toString: String = "Create phone, brand: " + brand + ", color: " + color + ", model: " + model
}

class IPhone(_color:String,_model:String, _brand:String) extends Phone(_color:String,_model:String,_brand:String){
  this.model = _model
  this.color = _color
  this.brand = _brand
}
class MI(_color:String,_model:String,_brand:String) extends Phone(_color:String,_model:String,_brand:String){
  this.model = _model
  this.color = _color
  this.brand = _brand
}

trait PhoneFactory{
  def creatPhone(color:String,model:String):Phone
}

object IPhoneFactory extends PhoneFactory{
  override def creatPhone(color: String, model: String) = new IPhone(color, model, "Iphone")
}
object MIphoneFactory extends PhoneFactory{
  override def creatPhone(color: String,model: String) = new MI(color,model,"MI")
}




object FactoryMethod extends App{

  val iPhoneFactory = IPhoneFactory
  val mIphoneFactory = MIphoneFactory
  println(iPhoneFactory.creatPhone("black","7Plus"))
  println(mIphoneFactory.creatPhone("golden","6s"))

}
