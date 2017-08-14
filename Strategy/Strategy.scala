package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/12.
  */

trait IStrategy{
  def doSomething():Unit
}
class Strategy1 extends IStrategy{
  override def doSomething(): Unit = println("执行策略1")
}
class Strategy2 extends IStrategy{
  override def doSomething() = println("执行策略2")
}

class Context(strategy: IStrategy){
   var strategy_ = strategy
   def execute = {
     strategy.doSomething()
   }
}

object Strategy extends App{
  var strategy1 = new Strategy1
  var strategy2 = new Strategy2
  val context1 = new Context(strategy1)
  val context2 = new Context(strategy2)

  context1.execute
  context1.execute
}