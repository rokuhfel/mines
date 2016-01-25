import controller.MainController
import model.impl.{Cell, Grid}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
/**
  * Created by Ron on 21.01.2016.
  */

@RunWith(classOf[JUnitRunner])
class Tests extends Specification{
  "A	new Cell" should {
    val cell = new Cell(false,false,0,false)

    "have value	0" in {
      cell.bombCounter must be_==(0)

    }

    "have no Flag" in{
      cell.isFlagged must be_==(false)
    }

    "have no Bomb" in{
      cell.hasBomb must be_==(false)
    }

    "be closed" in {
      cell.isOpened must be_==(false)
    }


  }


  "A new Grid" should {
    val grid = new Grid(3, 3, 3)

    "have the Heigth 3 " in {
      grid.height must be_==(3)
    }

    "have the Width 3 " in {
      grid.width must be_==(3)
    }

    "have 3 bombs" in {
      grid.bombs must be_==(3)
    }

    "have all bombs set after the are placed" in {
      grid.instantiate()
      grid.placeBombs()
      grid.bombsToPlace must be_==(0)
    }

    "have bomb counters set after they are calculated" in {
      grid.setBombCounters()
      grid.gridArray(0)(0) must_!= null
    }

  }

  "A controller" should {
    val c = new MainController(1)
    "have 12 fields to open" in{
      c.getFields() must be_==(12)
    }

    "have 11 fields to open after you open 1" in{
      c.openField(0,0)
      c.fieldsToOpen must be_==(11)
    }

    "get a Counter for a field" in{
      c.getCounter(0,0) must beOneOf(0,1,2,3,4,5,6,7,8,9)
    }

    "set a flag if there is no flag" in{
      c.toggleFlag(1,1) must be_==(true)
    }

    "remove a flag if there is a flag" in{
      c.toggleFlag(1,1) must be_==(false)
    }

    "finish the game when there is a win" in{
      c.fieldsToOpen = 0
      c.checkForWin() must be_==(true)

    }
  }


}
