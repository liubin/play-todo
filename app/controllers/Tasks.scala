package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Task


object Tasks extends Controller {

  val taskForm = Form(
    "label" -> nonEmptyText
  )

  def list = Action {
    Ok(views.html.tasks(Task.all(), taskForm))
  }

  def create = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.tasks(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.Tasks.list)
      }
    )
  }

  def done(id: Long)  = Action {
    Task.done(id)
    Redirect(routes.Tasks.list)
  }

}
