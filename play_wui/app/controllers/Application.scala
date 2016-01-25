package controllers


import controllers.MainController
import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready.", new MainController(1)))
  }

  def newGame(variant :Int) = Action{
    Ok(views.html.game("Your new application is ready.", new MainController(variant)))
  }

  def lose = Action{
    Ok(views.html.lost())
  }

  def win = Action{
    Ok(views.html.won())
  }

}
