package model

/**
  * Created by Ron on 23.01.2016.
  */
trait Grid {
  def openField(x:Int, y:Int):Boolean
  def placeFlag(x:Int, y:Int)
  def showBombCounter(x:Int, y:Int)

}
