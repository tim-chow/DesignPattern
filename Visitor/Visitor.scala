package com.wchy.patterns

import scala.collection.mutable.ArrayBuffer

/**
  * Created by wangchangye on 2017/8/12.
  */

trait Employee {
  def accept(handler: Department)
}

case class FullTimeEmployee(name: String, weeklyWage: Double, workTime: Int) extends Employee {
  override def accept(handler: Department): Unit = handler.visit(this)
}
case class PartTimeEmployee(name: String, hourWage: Double, workTime: Int) extends Employee {
  override def accept(handler: Department): Unit = handler.visit(this)
}

trait Department {
  def visit(employee: FullTimeEmployee): Unit

  def visit(employee: PartTimeEmployee): Unit
}

class FADepartment extends Department {
  override def visit(employee: FullTimeEmployee): Unit = {
    var weekWage = employee.weeklyWage
    val workTime = employee.workTime
    workTime match {
      case x if x >= 40 => weekWage = weekWage + (workTime - 40) * 100
      case x if x < 40 => weekWage = weekWage - (40 - workTime) * 80
        if (weekWage < 0) {
          weekWage = 0
        }
    }
    println(s"正式员工${employee.name}实际工资为${weekWage}元")
  }

  override def visit(employee: PartTimeEmployee): Unit = {
    val workTime = employee.workTime
    println(s"兼职员工${employee.name}实际工资为${employee.hourWage * workTime}元")

  }
}

class HRDepartment extends Department {
  override def visit(employee: FullTimeEmployee): Unit = {
    val workTime = employee.workTime
    println(s"正式员工${employee.name}实际上班时间为${workTime}小时")
    workTime match {
      case x if x >= 40 => println(s"正式员工${employee.name}加班时间为${workTime - 40}")
      case x if x < 40 => println(s"正式员工${employee.name}请假时间为${40 - workTime}")
    }
  }

  override def visit(employee: PartTimeEmployee): Unit =
    println(s"兼职员工${employee.name}实际上班时间为${employee.workTime}")
}

class EmployeeArray {
  private var employees = new ArrayBuffer[Employee]()

  def addEmployee(employee: Employee) = employee match {
    case e if employees.exists(e.eq(_)) => println("已经添加")
    case _ => employees += employee
  }

  def accept(department: Department) = employees.foreach(_.accept(department))
}


object Visitor extends App{
  val fte1: Employee = FullTimeEmployee("a", 500, 40)
  val fte2: Employee = FullTimeEmployee("b", 600, 38)
  val fte3: Employee = FullTimeEmployee("c", 550, 44)
  val fte4: Employee = PartTimeEmployee("d", 15, 33)
  val fte5: Employee = PartTimeEmployee("e", 17, 20)

  val employeeBuffer = new EmployeeArray
  employeeBuffer.addEmployee(fte1)
  employeeBuffer.addEmployee(fte1)
  employeeBuffer.addEmployee(fte2)
  employeeBuffer.addEmployee(fte3)
  employeeBuffer.addEmployee(fte4)
  employeeBuffer.addEmployee(fte5)

  val dep: Department = new HRDepartment
  employeeBuffer.accept(dep)
}
