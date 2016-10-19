package model

/**
  * Created by louis on 18/10/2016.
  */
class Country(data: Array[String]) {

  val id = data.lift(0)
  val code = data.lift(1)
  val name = data.lift(2)
  val continent = data.lift(3)
  val wikipedia_link = data.lift(4)
  val keywords = data.lift(5)

  override def toString = s"$id, $code, $name"

}
