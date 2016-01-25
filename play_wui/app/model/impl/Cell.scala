package model.impl

import model.{Cell => CellTrait}

/**
  * Created by Ron on 23.01.2016.
  */
case class Cell(isOpened: Boolean, hasBomb: Boolean, bombCounter: Int, isFlagged: Boolean) extends CellTrait {
  def this() = this(false, false, 0, false)

  def setBombCount(x: Int): Cell = copy(bombCounter = x)

  def setBomb(): Cell = copy(hasBomb = true)

  def setOpened(): Cell = copy(isOpened = true)

  def setFlag(flag: Boolean): Cell = {
    copy(isFlagged = flag)
  }
}
