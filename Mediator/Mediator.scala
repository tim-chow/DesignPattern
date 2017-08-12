package com.wchy.patterns

import scala.collection.mutable.ArrayBuffer

/**
  * Created by wangchangye on 2017/8/12.
  */


abstract class AbstractSoftware(val name: String) {
  private var persons = new ArrayBuffer[Person]()

  def addPerson(person: Person): Unit = {
    if (persons.forall(!person.eq(_))) {
      println(s"群${name}增加${person.name}")
      persons += person
    } else {
      println(s"用户${person.name}已在群${name}内")
    }
  }

  def removePerson(person: Person): Unit = {
    if (persons.exists(person.eq(_))) {
      persons -= person
    } else {
      println("该用户已被删除")
    }
  }

  def notify(person: Person, message: String): Unit = {
    if (persons.exists(person.eq(_))) {
      persons.filter(!person.eq(_)).foreach(p => println(s"${p.name}从${person.name}接收到信息:$message"))
    } else {
      println(s"${person.name}您已经不在群组:$name")
    }

  }

  def pm(send: Person, receive: Person, message: String): Unit = send match {
    case p if persons.exists(p.eq(_)) => receive match {
      case r if persons.exists(r.eq(_)) => println(s"${send.name}发送信息:$message 给${receive.name}")
      case _ => println(s"接收者${receive.name}没有获得该软件的许可")
    }
    case _ => println(s"发送者${send.name}没有获得该软件的许可")
  }
}

class QQSoftware(name: String) extends AbstractSoftware(name) {
  override def notify(person: Person, message: String): Unit = {
    println(s"这里是qq群:$name")
    super.notify(person, message)
  }

  override def pm(send: Person, receive: Person, message: String): Unit = {
    println(s"使用qq软件进行私聊")
    super.pm(send, receive, message)
  }
}

class MSNSoftware(name: String) extends AbstractSoftware(name) {
  override def notify(person: Person, message: String): Unit = {
    println(s"这里是msn群:$name")
    super.notify(person, message)
  }

  override def pm(send: Person, receive: Person, message: String): Unit = {
    println(s"使用msn软件进行私聊")
    super.pm(send, receive, message)
  }
}

abstract class Person(val name: String) {
  def setAbstractSoftware(software: AbstractSoftware)

  def speak(message: String): Unit

  def remove(person: Person): Unit

  def add(person: Person): Unit

  def privateChat(person: Person, message: String):Unit
}

class Admin(name: String) extends Person(name) {
  private var abstractSoftware: AbstractSoftware = null

  def setAbstractSoftware(software: AbstractSoftware) = abstractSoftware = software

  override def speak(message: String) = abstractSoftware.notify(this, message)

  def remove(person: Person) = abstractSoftware.removePerson(person)

  def add(person: Person) = {
    println(s"${name}进行添加用户${person.name}的操作")
    abstractSoftware.addPerson(person)
  }
  def privateChat(person: Person, message: String) = abstractSoftware.pm(this, person, message)
}

class Member(name: String) extends Person(name) {
  private var abstractSoftware: AbstractSoftware = null

  def setAbstractSoftware(software: AbstractSoftware) = abstractSoftware = software

  override def speak(message: String) = abstractSoftware.notify(this, message)

  override def add(person: Person): Unit = {
    println(s"${name}您不是管理员，不具备增加用户权限")

  }
  override def remove(person: Person): Unit = {
    if (person.eq(this)) {
      println(s"$name，您将退出${abstractSoftware.name}")
      abstractSoftware.removePerson(person)
    } else {
      println(s"${name}您不是管理员，不具备删除用户权限")
    }
  }

  def privateChat(person: Person, message: String) = abstractSoftware.pm(this, person, message)
}

object Mediator extends App {
  val admin: Person = new Admin("admin")
  val member1: Person = new Member("member1")
  val member2: Person = new Member("member2")
  val member3: Person = new Member("member3")
  val member4: Person = new Member("member4")

  val qqSoftware: AbstractSoftware = new QQSoftware("研发中心")
  admin.setAbstractSoftware(qqSoftware)
  member1.setAbstractSoftware(qqSoftware)
  member2.setAbstractSoftware(qqSoftware)
  member3.setAbstractSoftware(qqSoftware)
  member4.setAbstractSoftware(qqSoftware)

  admin.add(admin)
  admin.add(member1)
  admin.add(member2)
  admin.add(member3)
  admin.add(member4)
  admin.add(member1)

  admin.speak("hello")
  admin.remove(member1)

  member1.speak("hi")

  member2.add(member1)
  member2.remove(member2)

  member2.speak("admin")

  member3.privateChat(admin, "你好")
  member3.privateChat(member2, "你好")



  member2.privateChat(admin, "加我")

  println("------------------------------------------")

  val msnSoftware: AbstractSoftware = new MSNSoftware("通研事业部")
  admin.setAbstractSoftware(msnSoftware)
  member1.setAbstractSoftware(msnSoftware)
  member2.setAbstractSoftware(msnSoftware)
  member3.setAbstractSoftware(msnSoftware)
  member4.setAbstractSoftware(msnSoftware)


  admin.add(admin)
  admin.add(member1)
  admin.add(member2)
  admin.add(member3)
  admin.add(member4)
  admin.add(member1)

  admin.speak("hello")
  admin.remove(member1)

  member1.speak("hi")

  member2.add(member1)

  member2.speak("admin")

  member2.privateChat(member3,"test")

}
