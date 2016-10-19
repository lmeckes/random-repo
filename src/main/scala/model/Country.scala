package model

/**
  * Created by louis on 18/10/2016.
  */
case class Country(
                    id: Option[String],
                    code: Option[String],
                    name: Option[String],
                    continent: Option[String],
                    wikipedia_link: Option[String],
                    keywords: Option[String]) {
  override def toString = s"$id, $code, $name"
}

object Country {
  def create(data: Array[String]): Country = {
    new Country(
      data.lift(0),
      data.lift(1),
      data.lift(2),
      data.lift(3),
      data.lift(4),
      data.lift(5))
  }
}
