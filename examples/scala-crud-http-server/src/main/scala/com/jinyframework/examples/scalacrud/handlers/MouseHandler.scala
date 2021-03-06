package com.jinyframework.examples.scalacrud.handlers

import com.jinyframework.core.AbstractRequestBinder.{Handler, HttpResponse}
import com.jinyframework.examples.scalacrud.entities.Mouse
import com.jinyframework.examples.scalacrud.factories.app.AppFactory
import org.mongodb.scala.MongoCollection

object MouseHandler {
  val getMouses: Handler = _ => {
    val mouses = collection.find()
    HttpResponse.of(mouses)
  }
  val addMouse: Handler = ctx => {
    val body = ctx.getBody
    val newMouse: Mouse = gson.fromJson(body, classOf[Mouse])
    collection.insertOne(Mouse.apply(newMouse.name, newMouse.owner))
    HttpResponse.of(newMouse)
  }
  private val gson = AppFactory.getGson
  private val collection: MongoCollection[Mouse] = AppFactory.getMongoDatabase
    .getCollection("mouse")
}
