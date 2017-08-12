package com.wchy.patterns

import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

/**
  * Created by wangchangye on 2017/8/12.
  */
trait Account {

  def validate(account: String, pass: String): Boolean = {
    val params = ArrayBuffer[Any](account)
    val result = new HashMap[String,String]{}
    result.put("name","ct")
    result.put("pass","123")
    return result.getOrElse("name", "null") == account && result.getOrElse("pass", "null") == pass
  }

  def calculateInterest(): Unit
  def display() = println("显示利息")
  def handle(account: String, pass: String): Unit = validate(account, pass) match {
    case true => calculateInterest()
      display()
    case false =>
      println("账户或密码错误")
  }
}

case object CurrentAccount extends Account {
  override def calculateInterest(): Unit = println("按照活期计算利息")
}

case object SavingAccount extends Account {
  override def calculateInterest(): Unit = println("按照定期计算利息")
}

object TemplateMethod extends App {
  val account1: Account = SavingAccount
  account1.handle("ct", "123")

  val account2: Account = SavingAccount
  account2.handle("ct", "1233")

  val account3: Account = CurrentAccount
  account3.handle("ct", "123")
}


