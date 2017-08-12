package com.wchy.patterns

/**
  * Created by wangchangye on 2017/8/12.
  */

case class PurchaseRequest(amount: Double, number: Int, purpose: String)
abstract class Approve(name: String) {
  protected var successor: Approve = _
  def setSuccessor(approve: Approve) = successor = approve
  def processRequest(request: PurchaseRequest): Unit
}
class Director(name: String) extends Approve(name) {
  override def processRequest(request: PurchaseRequest) = request.amount match {
    case x: Double if x < 50000 =>
      println(s"主任$name 审批采购单：" + request.number + ",金额：" + request.amount + "元，采购目的：" + request.purpose)
    case _ => this.successor.processRequest(request)
  }
}
class VicePresident(name: String) extends Approve(name) {
  override def processRequest(request: PurchaseRequest) = request.amount match {
    case x: Double if x < 100000 =>
      println(s"副董事长$name 审批采购单：" + request.number + ",金额：" + request.amount + "元，采购目的：" + request.purpose)
    case _ => this.successor.processRequest(request)
  }
}
class President(name: String) extends Approve(name) {
  override def processRequest(request: PurchaseRequest) = request.amount match {
    case x: Double if x < 500000 =>
      println(s"董事长$name 审批采购单：" + request.number + ",金额：" + request.amount + "元，采购目的：" + request.purpose)
    case _ => this.successor.processRequest(request)
  }
}

class Congress(name: String) extends Approve(name) {
  override def processRequest(request: PurchaseRequest) =
    println("召开董事会审批采购单：" + request.number + ",金额：" + request.amount + "元，采购目的：" + request.purpose)
}
object Client extends App {
  val ZH: Approve = new Director("周华")
  val YJY: Approve = new VicePresident("游建友")
  val WZX: Approve = new President("吴志雄")
  val meeting: Approve = new Congress("董事会")

  ZH.setSuccessor(YJY)
  YJY.setSuccessor(WZX)
  WZX.setSuccessor(meeting)

  ZH.processRequest(PurchaseRequest(45000, 1001, "大数据卡口项目"))
  ZH.processRequest(PurchaseRequest(60000, 1002, "服务器购置"))
  ZH.processRequest(PurchaseRequest(145000, 1003, "星环开科技专利购买"))
  ZH.processRequest(PurchaseRequest(1145000, 1004, "公司并购"))


}
