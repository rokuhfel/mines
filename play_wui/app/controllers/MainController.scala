package controllers

import model.impl.Grid

/**
  * Created by Ron on 17.12.2015.
  */
class MainController(variant:Int){
  var grid = new Grid(4,4,4)

  variant match {
    case 1 =>  grid = new Grid(3,3,3)
    case 2 =>  grid = new Grid(4,4,4)
    case 3 =>  grid = new Grid(5,5,5)
    case _ =>  grid = new Grid(4,4,3)
  }

  var fieldsToOpen = (grid.width * grid.height) - grid.bombs

  def handleClick(x:Int,y:Int): Boolean ={
    toggleFlag(x,y)
  }

  def handleDblClick(x:Int,y:Int): Boolean ={
    openField(x,y)
  }

  def openField(x:Int,y:Int): Boolean ={
    fieldsToOpen -= 1
    grid.gridArray(x)(y).setOpened().hasBomb

  }

  def getCounter(x:Int,y:Int): Int = {
    grid.gridArray(x)(y).bombCounter
  }

  def toggleFlag(x:Int,y:Int): Boolean ={

    if(!grid.gridArray(x)(y).isFlagged){

      grid.gridArray(x)(y) = grid.gridArray(x)(y).setFlag(true)
    }
    else{

      grid.gridArray(x)(y) = grid.gridArray(x)(y).setFlag(false)
    }
    grid.gridArray(x)(y).isFlagged

  }

  def newGame(x:Int, y:Int, bombs:Int): Unit ={
    grid = new Grid(x,y,bombs)
    fieldsToOpen = (grid.width * grid.height) - grid.bombs
  }

  def checkForWin(): Boolean ={
    if(fieldsToOpen < 1){
      true
    }
    else{
      false
    }
  }

  def getFields(): Int ={
    fieldsToOpen
  }
}
