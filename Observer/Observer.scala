package com.wchy.patterns

import scala.collection.mutable.ArrayBuffer

/**
  * Created by wangchangye on 2017/8/12.
  */
abstract class Observer(var name: String) {
  def help(): Unit
  def beAttacked(allControlCenter: AllControlCenter): Unit
}
class Player(name: String) extends Observer(name) {
  override def help(): Unit = println(s"坚持住，${name}来救你！")
  override def beAttacked(allControlCenter: AllControlCenter): Unit = {
    println(s"${name}被攻击")
    allControlCenter.notifyObserver(name)
  }
}
abstract class AllControlCenter(allyName: String) {
  protected var players = new ArrayBuffer[Observer]()
  def join(observer: Observer):Unit = observer match {
    case o if players.exists(_.eq(o)) => println(s"${o.name}已加入${allyName}战队")
    case o: Observer => println(s"${o.name}加入${allyName}战队")
      players += observer
    case _ => println("异常")
  }
  def quit(observer: Observer):Unit = observer match {
    case o if players.exists(o.eq(_)) => println(s"${o.name}退出${allyName}战队")
      players -= observer
    case o if !players.exists(_.eq(o)) => println(s"${o.name}已退出${allyName}战队")
    case _ => println("异常")
  }

  def notifyObserver(oName: String): Unit
}

class ConcreteAllyControlCenter(name: String) extends AllControlCenter(name) {
  override def notifyObserver(oName: String): Unit = oName match {
    case o if players.exists(_.name == o) => println(s"${name}战队紧急通知，盟友${oName}遭到攻击")
      players.filterNot(_.name == oName).foreach(_.help())
    case _ => println(s"$oName，您已不是战队${name}成员，无法通知战队您被攻击的消息")
  }
}

object Observer extends App {
  val acc: AllControlCenter = new ConcreteAllyControlCenter("justSo")

  val play1: Observer = new Player("a")
  val play2: Observer = new Player("b")
  val play3: Observer = new Player("c")
  val play4: Observer = new Player("d")
  val play5: Observer = new Player("e")
  acc.join(play1)
  acc.join(play2)
  acc.join(play3)
  acc.join(play3)
  acc.join(play4)
  acc.join(play5)
  acc.quit(play1)
  acc.quit(play1)
  play1.beAttacked(acc)
}