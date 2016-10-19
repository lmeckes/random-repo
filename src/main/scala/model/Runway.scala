package model

/**
  * Created by louis on 18/10/2016.
  */

case class Runway(
                   id: Option[String],
                   airport_ref: Option[String],
                   airport_ident: Option[String],
                   length_ft: Option[String],
                   width_ft: Option[String],
                   surface: Option[String],
                   lighted: Option[String],
                   closed: Option[String],
                   le_ident: Option[String],
                   le_latitude_deg: Option[String],
                   le_longitude_deg: Option[String],
                   le_elevation_ft: Option[String],
                   le_heading_degT: Option[String],
                   le_displaced_threshold_ft: Option[String],
                   he_ident: Option[String],
                   he_latitude_deg: Option[String],
                   he_longitude_deg: Option[String],
                   he_elevation_ft: Option[String],
                   he_heading_degT: Option[String],
                   he_displaced_threshold_ft: Option[String]

                 ) {
  override def toString = s"$id, $airport_ref, $length_ft"
}

object Runway {
  def create(data: Array[String]): Runway = {
    new Runway(
      data.lift(0),
      data.lift(1),
      data.lift(2),
      data.lift(3),
      data.lift(4),
      data.lift(5),
      data.lift(6),
      data.lift(7),
      data.lift(8),
      data.lift(9),
      data.lift(10),
      data.lift(11),
      data.lift(12),
      data.lift(13),
      data.lift(14),
      data.lift(15),
      data.lift(16),
      data.lift(17),
      data.lift(18),
      data.lift(19)
    )
  }

}
