package com.wchy.patterns

/**
  * Created by wangchangye on 17-8-10.
  */
trait Target {
  def Request
}

class Adaptee {
  def SpecificRequest = println("Specific Request!")
}

trait Adapter {
  self : Target with Adaptee=>
  def Request()= SpecificRequest
}

object AdapterPattern{
  def main(args:Array[String])= {
    val adapter=new Adaptee with Target with Adapter
    adapter.Request
    adapter.SpecificRequest
  }
}

