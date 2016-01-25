package gui

/**
  * Created by Ron on 11.01.2016.
  */

import java.awt.Color

import controller.MainController

import scala.swing._
import scala.swing.event._

object GUI extends SimpleSwingApplication{
  var openOrFlag = true
  val controller = new MainController(1)
  val label = new Label("")
  val s = new Dimension(50,50)
  val menu = new MenuBar {
    contents += new Menu("Menu") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New 3x3 Game") {
        newGame(3,3,3)
      })
      contents += new MenuItem(Action("New 4x4 Game") {
        newGame(4,4,4)
      })
      contents += new MenuItem(Action("New 5x5 Game") {
        newGame(5,5,5)
      })

      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
  }

  val toggleButton = new Button{text ="Mode: Open | Click to set Flagmode"
    reactions += {
      case ButtonClicked(_) => toggleModus()

    }}

  var gridPanel = new GridPanel(controller.grid.height, controller.grid.width) {
    for (x <- 0 until controller.grid.height; y <- 0 until controller.grid.width) {
      contents += new Button {
        minimumSize = s
        maximumSize = s
        preferredSize = s
        text = ""
        background = Color.LIGHT_GRAY
        reactions += {
          case ButtonClicked(_) => handleButtonPress(this,x,y)
        }
      }
    }
  }

  def newUI = new BorderPanel(){
    layout(new BorderPanel(){
      layout(toggleButton) = BorderPanel.Position.Center
      layout(new Label("Toggle Flag/Open:")) = BorderPanel.Position.West
      layout(label) = BorderPanel.Position.South
    }) = BorderPanel.Position.North
    layout(gridPanel) = BorderPanel.Position.Center

  }


  var top = new MainFrame{

    title = "HTWG-Mines"
    menuBar = menu
    contents = new BoxPanel(Orientation.Horizontal){
      contents += newUI
    }

  }


  def handleButtonPress(button:Button, x: Int, y:Int) ={
    if(openOrFlag) {
      if (button.background != Color.GREEN){
        label.text = "Fields to open: " + controller.getFields()

        if (controller.openField(x, y)) {
          button.background = Color.RED
          label.text = "You Lost"
          label.background = Color.RED
        }
        else {
          if (controller.checkForWin()) {
            label.text = "you won"
            label.background = Color.RED
          }
          button.text = "" + controller.getCounter(x, y)
          button.background = Color.GREEN
        }
      }
    }
    else{
      setFlag(button,x,y)

    }
  }

  def toggleModus(): Boolean ={
    if(openOrFlag){

      openOrFlag = false
      toggleButton.text = "Mode: Flag | Click to set Openmode"
    }
    else{

      openOrFlag = true
      toggleButton.text = "Mode: Open | Click to set Flagmode"
    }
    true
  }

  def setFlag(button:Button,x: Int, y:Int): Unit ={
    if(button.text.equals("F")){
      button.text = " "
      button.background = Color.LIGHT_GRAY
    }
    if(button.text == "") {
      button.text = "F"
      button.background = Color.YELLOW
    }

  }

  def newGame(length: Int, height: Int, bombs: Int) : Boolean= {
  controller.newGame(length,height,bombs)
  label.text = ""

  gridPanel = new GridPanel(controller.grid.height, controller.grid.width) {
    for (x <- 0 until controller.grid.height; y <- 0 until controller.grid.width) {
      contents += new Button {
        background = Color.LIGHT_GRAY
        minimumSize = s
        maximumSize = s
        preferredSize = s
        text = " "
        reactions += {
          case ButtonClicked(_) => handleButtonPress(this,x,y)
        }
      }
    }
  }

  top.contents = newUI
  gridPanel.repaint()
  top.repaint()

  true
  }

}

