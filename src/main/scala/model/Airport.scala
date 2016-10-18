package model

/**
  * Created by louis on 18/10/2016.
  */
class Airport(data: Array[String], rw: Option[Seq[Runway]]) {

  val id = data.lift(0)
  val ident = data.lift(1)
  val typex = data.lift(2)
  val name = data.lift(3)
  val latitude_deg = data.lift(4)
  val longitude_deg = data.lift(5)
  val elevation_ft = data.lift(6)
  val continent = data.lift(7)
  val iso_country = data.lift(8)
  val iso_region = data.lift(9)
  val municipality = data.lift(10)
  val scheduled_service = data.lift(11)
  val gps_code = data.lift(12)
  val iata_code = data.lift(13)
  val local_code = data.lift(14)
  val home_link = data.lift(15)
  val wikipedia_link = data.lift(16)
  val keywords = data.lift(17)
  def runways = rw

  override def toString = s"$id, $ident, $iso_country"

}
