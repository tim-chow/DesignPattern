package com.wchy.patterns

import scala.collection.mutable.ArrayBuffer

/**
  * Created by wangchangye on 2017/8/12.
  */
class Adder {
  private var num = 0
  def add(value: Int) = {
    num += value
    num
  }
}

abstract class AbstractCommand {
  def execute(value: Int): Int
  def undo(): Int
  def redo(): Int
}

class AddCommand extends AbstractCommand {
  private val values = new ArrayBuffer[Int]()
  private val valuesRedo = new ArrayBuffer[Int]()
  val adder = new Adder
  override def execute(value: Int) = {
    values += value
    adder.add(value)
  }
  override def undo() = {
    values.length match {
      case 0 => println("不可undo"); adder.add(0)
      case _ => val temp = values.remove(values.length - 1)
        valuesRedo += temp
        adder.add(-temp)
    }

  }
  override def redo() = {
    valuesRedo.length match {
      case 0 => println("不可redo"); adder.add(0)
      case _ => val temp = valuesRedo.remove(valuesRedo.length -1)
        values += temp
        adder.add(temp)
    }
  }
}

class CalculatorForm(command: AbstractCommand) {
  def compute(value: Int): Unit = {
    val i = command.execute(value)
    println(s"执行运算，运算结果为: $i")
  }

  def undo(): Unit = {
    val i = command.undo()
    println(s"执行undo，运算结果为: $i")

  }

  def redo(): Unit = {
    val i = command.redo()
    println(s"执行redo，运算结果为: $i")
  }
}

object Commander extends App {
  val command: AbstractCommand = new AddCommand
  val form = new CalculatorForm(command)
  form.compute(10)
  form.compute(5)
  form.undo()
  form.undo()
  form.redo()
  form.redo()
  form.redo()
  form.undo()
  form.undo()
  form.undo()
  form.redo()
  form.compute(100)
}