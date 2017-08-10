package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/10.
  */

abstract  class Chart {
  def display():Unit
}

class  HistogramChart extends  Chart{
  println("初始化柱状图")

  override def display(): Unit = {
    println("显示柱状图")
  }
}

class  PieChart extends  Chart{
  println("初始化饼状图")

  override def display(): Unit = {
    println("显示饼状图")
  }
}

class LineChart extends  Chart{
  println("初始化折线图")

  override def display(): Unit = {
    println("显示折线图")
  }
}

object  Chart {

  def apply(style:String) = style match {
    case "histogram" => println("创建化柱状图");new HistogramChart
    case "pie" => println("创建饼状图");new PieChart
    case "line" =>println("创建折线图"); new LineChart
  }
}

object SimpleFactory extends  App{
  //产出饼状图
  val pie = Chart("pie")
  pie.display()
  //产出柱状图
  val histogram = Chart("histogram")
  histogram.display()
  //产出折线图
  val line = Chart("line")
  line.display()

}
