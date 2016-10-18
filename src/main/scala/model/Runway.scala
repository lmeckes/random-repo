package model

/**
  * Created by louis on 18/10/2016.
  */
class Runway(data: Array[String]) {

  val id = data.lift(0)
  val airport_ref = data.lift(1)
  val airport_ident = data.lift(2)
  val length_ft = data.lift(3)
  val width_ft = data.lift(4)
  val surface = data.lift(5)
  val lighted = data.lift(6)
  val closed = data.lift(7)
  val le_ident = data.lift(8)
  val le_latitude_deg = data.lift(9)
  val le_longitude_deg = data.lift(10)
  val le_elevation_ft = data.lift(11)
  val le_heading_degT = data.lift(12)
  val le_displaced_threshold_ft = data.lift(13)
  val he_ident = data.lift(14)
  val he_latitude_deg = data.lift(15)
  val he_longitude_deg = data.lift(16)
  val he_elevation_ft = data.lift(17)
  val he_heading_degT = data.lift(18)
  val he_displaced_threshold_ft = data.lift(19)

  override def toString = s"$id, $airport_ref, $length_ft"

}
