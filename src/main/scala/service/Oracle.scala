package service


import model.{Airport, Runway}

import scala.io.Source

/**
  * Created by louis on 18/10/2016.
  */
object Oracle {

  private def loadCsv(f: String) =
    Source.fromFile(f)
      .getLines()
      .drop(1)
      .map(_.split(",")
        .map(_.stripPrefix("\"")
          .stripSuffix("\"")))

  private lazy val sortedRunways = loadCsv("resources/runways.csv").map(new Runway(_)).toSeq.groupBy(_.airport_ref)
  private lazy val airports = loadCsv("resources/airports.csv") map (a =>
    new Airport(a, sortedRunways.get(Some(a.head)))) toSeq

  /*- Query Option will ask the user for the country name or code and print the airports & runways at each airport.
  The input can be country code or country name.
    For bonus points make the test partial/fuzzy. e.g. entering zimb will result in Zimbabwe :)*/
  def query(country: String): Seq[Airport] = {
    println(s"searching for '$country'")
    airports.filter(_.iso_country == Some(country))
  }

  /*
  - Choosing Reports will print the following:
    - 10 countries with highest number of airports (with count) and countries with lowest number of airports.
    - Type of runways (as indicated in "surface" column) per country
    - Bonus: Print the top 10 most common runway identifications (indicated in "le_ident" column)
   */
  def report: Unit = {

  }

}
