package model

/**
  * Created by Ron on 23.01.2016.
  */
trait Cell {
  def setBombCount(bombCount: Int) :Cell
  def setOpened() :Cell
  def setFlag(flag: Boolean) :Cell
  def setBomb(): Cell
}
