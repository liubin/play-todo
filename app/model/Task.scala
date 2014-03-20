package models

import play.api.db._
import play.api.Play.current


import anorm._
import anorm.SqlParser._


case class Task(id: Long, label: String, done: Boolean)

object Task {

  val task = {
    get[Long]("id") ~
    get[String]("label") ~
    get[String]("done") map {
      case id~label~done => Task(id, label, done == "t")
    }
  }

  def all(): List[Task] = {
    DB.withConnection { implicit c =>
      SQL("select * from tasks").as(Task.task *)
    }
  }

  def create(label: String) {
    DB.withConnection { implicit c =>
      SQL("insert into tasks (label) values ({label})").on(
        'label -> label
      ).executeUpdate()
    }
  }

  def done(id: Long) {
    DB.withConnection { implicit connection =>
      SQL("update tasks set done = 't' where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

}
