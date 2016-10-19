package service

import model._

import scala.annotation.tailrec
import scala.io.Source

/**
  * Created by louis on 18/10/2016.
  */
object QueryService {

  object Data {

    def loadCsv(f: String) =
      Source.fromFile(f)
        .getLines()
        .drop(1)
        .map(_.split(",")
          .map(_.stripPrefix("\"")
            .stripSuffix("\"")))

    lazy val sortedRunways = loadCsv("resources/runways.csv")
      .map(new Runway(_)).toSeq.groupBy(_.airport_ref)

    lazy val airports = loadCsv("resources/airports.csv") map (a =>
      new Airport(a, sortedRunways.get(Some(a(0))))) toSeq

    lazy val countries = loadCsv("resources/countries.csv") map (new Country(_)) toSeq

  }

  def query(q: String): Seq[Airport] = {

    def countryCodesFromString(s: String) =
      Data.countries
        .filter(_.name.getOrElse("").toLowerCase
          contains s.toLowerCase)
        .map(_.code.get)

    def isCountryCode(s: String) =
      Data.countries.exists(
        _.code.equals(Some(s)))

    if (isCountryCode(q.toUpperCase))
      Data.airports.filter(_.iso_country == Some(q))
    else {
      val codes = countryCodesFromString(q)
      codes size match {
        case 0 => throw new NoSuchElementException
        case 1 => query(codes.head)
        case _ => throw new IllegalArgumentException
      }
    }

  }

  /*
  - Choosing Reports will print the following:
    - 10 countries with highest number of airports (with count) and countries with lowest number of airports.
    - Type of runways (as indicated in "surface" column) per country
    - Bonus: Print the top 10 most common runway identifications (indicated in "le_ident" column)
   */
  def report = {

    // val tenCountriesByAirportCount =

  }

}
