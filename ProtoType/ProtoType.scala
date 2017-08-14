package com.wchy.patterns

/**
  * Created by wangchangye on 17-8-14.
  */
case class WeeklyLog(var name: String, var date: String, var content: String, var attachment: Attachment)
case class Attachment(var name: String) {
  def download() = println("下载附件，文件名为：" + name)
}
//case 1
object Client extends App {
  val log_previous = WeeklyLog("小明", "第1周", "本周主要是进行设计模式学习",Attachment("原型模式.pdf"))
  println("*******周报*******")
  println("周次：" + log_previous.date)
  println("姓名：" + log_previous.name)
  println("内容：" + log_previous.content)
  log_previous.attachment.download()
  println("----------------------------")

  val log_new = log_previous.copy()
  println(log_new.eq(log_previous))
  println(log_new.attachment.eq(log_previous.attachment))
  log_new.date = "第2周"
  log_new.attachment = Attachment("其他模式.pdf")
  println("*******周报*******")
  println("周次：" + log_new.date)
  println("姓名：" + log_new.name)
  println("内容：" + log_new.content)
  log_new.attachment.download()
  println("----------------------------")

}




//case 2
object PrototypeManager {
  var ht = new scala.collection.mutable.HashMap[String, OfficialDocument]
  ht += ("far" -> new FAR("<可行性报告>"))
  ht += ("srs" -> new SRS("<软件需求规格说明书>"))

  def addOfficialDocument(key: String, officialDocument: OfficialDocument) = ht += (key -> officialDocument)
  def getOfficialDocument(key: String): OfficialDocument = ht.getOrElse(key, OfficialDocument("null")).copy()
}
case class OfficialDocument(title: String) {
  def display(): Unit = println("公文标题：" + title)
}
class FAR(title: String) extends OfficialDocument(title)
class SRS(title: String) extends OfficialDocument(title)

object ProtoType extends App {

  val pm = PrototypeManager
  val doc1 = pm.getOfficialDocument("far")
  val doc2 = pm.getOfficialDocument("srs")
  doc1.display()
  doc2.display()
  println(doc1.eq(doc2))
}
