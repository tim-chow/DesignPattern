package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/10.
  */

class Worker private{
  def work() = println("I am the only worker!")
}

object Worker{
  val worker = new Worker
  def GetWorkInstance() : Worker = {
    worker.work()
    worker
  }
}
object Worker1{
  private val worker1 = Worker1
  def getInstance = worker1
  var index = 1
}

object Singleton {
  def main(args: Array[String]) {
    //私有构造
    for (i <- 1 to 5) {
      Worker.GetWorkInstance();
    }

    val w1 = Worker1.getInstance
    val w2 = Worker1.getInstance
    println(w1.index)
    println(w2.index)
    println(w1 == w2)


  }
}
