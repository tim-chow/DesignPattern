package com.wchy.patterns

import scala.collection.mutable.ArrayBuffer

/**
  * Created by wangchangye on 2017/8/12.
  */

case class Chessman(var label: String, var x: Int, var y: Int) extends ChessmanFunction {
  def save: ChessMemento = ChessMemento(this.label, this.x, this.y)
  def restore(chessMemento: ChessMemento): Unit = {
    label = chessMemento.label
    x = chessMemento.x
    y = chessMemento.y
  }
}

case class ChessMemento(label: String, x: Int, y: Int)

trait ChessmanFunction {
  private var mementoArrayBuffer = new ArrayBuffer[ChessMemento]()
  private var index = -1
  def setMemento(memento: ChessMemento): Unit = mementoArrayBuffer += memento
  def play(chessman: Chessman): Unit = {
    setMemento(chessman.save)
    index += 1
    println(s"棋子${chessman.label} 当前位置为第${chessman.x}行，第${chessman.y}列")
  }
  def undo(chessman: Chessman): Unit = {
    println("******悔棋******")
    index match {
      case -1 => println("已经在最初位置，无法撤销")
      case 0 => index -= 1
        println("已经在最初位置，无法撤销")
      case length: Int if length > 0 => index -= 1
        chessman.restore(mementoArrayBuffer(index))
        println(s"棋子${chessman.label} 当前位置为第${chessman.x}行，第${chessman.y}列")
      case _ => println("出现异常")
    }
  }

  def redo(chessman: Chessman): Unit = {
    println("******撤销悔棋******")
    index match {
      case -1 => index = 1
        chessman.restore(mementoArrayBuffer(index))
        println(s"棋子${chessman.label} 当前位置为第${chessman.x}行，第${chessman.y}列")
      case length: Int if -1 < length && length < mementoArrayBuffer.length - 1 => index += 1
        chessman.restore(mementoArrayBuffer(index))
        println(s"棋子${chessman.label} 当前位置为第${chessman.x}行，第${chessman.y}列")
      case length: Int if length >= mementoArrayBuffer.length - 1 => println("已经在最终位置，无法恢复")
      case _ => println(s"异常$index")
    }
  }
}

object Memento extends App {
  val chess = new Chessman("马",1,1)
  chess.undo(chess)
  chess.play(chess)
  chess.y = 4
  chess.play(chess)
  chess.x = 5

  chess.play(chess)
  chess.undo(chess)
  chess.undo(chess)
  chess.undo(chess)
  chess.redo(chess)
  chess.redo(chess)
  chess.redo(chess)
  chess.redo(chess)
  chess.undo(chess)
  chess.undo(chess)
  chess.undo(chess)
  chess.undo(chess)
  chess.undo(chess)
  chess.redo(chess)
}
