package tui

import java.util.Scanner

import controller.MainController
import model.impl.Grid

/**
  * Created by Ron on 30.12.2015.
  */
object TUI {
  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    val controller = new MainController(1)
    var grid: Grid = null
    var replay = true

    println("Hello, welcome to Mines!")


    while (replay) {
      println("What size do you want to play?")
      println("(1) 3x3")
      println("(2) 4x4")
      println("(3) 5x5")
      val size = scanner.nextInt()
      size match {
        case 1 => print("1")
         controller.newGame(3,3,3)
        case 2 => print("2")
          controller.newGame(4,4,4)
        case 3 => print("3")
          controller.newGame(5,5,5)
        case _ => print("error")

      }

      printGrid()

      var game = true
      while (game) {
        println("Enter your next move like: XYZ  (X-> x-Coordinate, Y-> y-Coordinate, Z-> 0 for open or 1 for flag) ")
        val input = scanner.next()
        input.toList.filter(c => c != '	').map(c => c.toString.toInt) match {
          case row :: column :: value :: Nil => {
            value match {
              case 0 => game = controller.openField(row, column)
              case 1 => controller.toggleFlag(row,column)
            }
          }
        }
        printGrid()
      }

      println("Do you want to play again? (y/n)")
      val x = scanner.next()
      x match {
        case "y" => replay = true
        case "n" => replay = false
          println("Bye!")
      }

    }

    def printGrid(): Unit = {
      println()
      for (i <- 0 until controller.grid.height) {
        print("|")
        for (j <- 0 until controller.grid.width) {
          if (controller.grid.gridArray(i)(j).isOpened) {
            if (controller.grid.gridArray(i)(j).hasBomb) {
              print("*|")
            }
            else {
              print(controller.grid.gridArray(i)(j).bombCounter + "|")
            }
          }
          else {
            if (controller.grid.gridArray(i)(j).isFlagged) {
              print("F|")
            }
            else {
              print("_|")
            }
          }
        }
        println()
      }
    }

  }
}
