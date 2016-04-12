package uzuzjmd.competence.util


import java.util

import scala.collection.JavaConverters._
import scala.collection.mutable


/**
  * Created by dehne on 16.03.2016.
  */
trait LanguageConverter {

  def stuff(): Unit = {
    new util.LinkedList().asScala.asJava

  }

  implicit def language[T](input: Seq[T]) : java.util.List[T] = {
    return input.asJava
  }

  implicit def language[T](input: java.util.List[T]) : mutable.Buffer[T] = {
    return input.asScala
  }
}
