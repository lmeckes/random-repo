package model

/**
  * Created by louis on 18/10/2016.
  */
case class Airport(
    id: Option[String],
    ident: Option[String],
    typex: Option[String],
    name: Option[String],
    latitude_deg: Option[String],
    longitude_deg: Option[String],
    elevation_ft: Option[String],
    continent: Option[String],
    iso_country: Option[String],
    iso_region: Option[String],
    municipality: Option[String],
    scheduled_service: Option[String],
    gps_code: Option[String],
    iata_code: Option[String],
    local_code: Option[String],
    home_link: Option[String],
    wikipedia_link: Option[String],
    keywords: Option[String],
    runways: Option[Seq[Runway]]) {
  override def toString = s"$id, $ident, $iso_country"
}

object Airport {
  def create(data: Array[String],
             runways: Option[Seq[Runway]]): Airport = {
    new Airport(
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
      runways
    )
  }
}
