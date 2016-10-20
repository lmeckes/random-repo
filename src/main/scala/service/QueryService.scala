package service

import model._

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
      .map(Runway.create(_)).toSeq.groupBy(_.airport_ref)

    lazy val airports = loadCsv("resources/airports.csv") map (a =>
      Airport.create(a, sortedRunways.get(Some(a(0))))) toSeq

    lazy val countries = loadCsv("resources/countries.csv") map (Country.create(_)) toSeq

  }

  object Report {

    lazy val airportByCountry =
      Data.airports
        .groupBy(_.iso_country)
        .map(c => (c._1.get, c._2.size))
        .toSeq
        .sortBy(_._2)

    lazy val tenWithMost = airportByCountry drop (airportByCountry.size - 10) reverse
    lazy val tenWithLeast = airportByCountry take 10

    lazy val runwaysTypesByCountry =
      Data.airports
        .groupBy(_.iso_country)
        .map(c => {
          (c._1.get,
            c._2.flatMap(
              _.runways.getOrElse(Seq[Runway]())
                .map(_.surface.get)
            ).groupBy(s => s).mapValues(_.size))
        })

    lazy val mostCommonRunwayIdent =
      Data.airports.flatMap(
        _.runways.getOrElse(Seq[Runway]())
          .map(_.le_ident.getOrElse("unknown"))
      ).groupBy(i => i).mapValues(_.size)
        .toList.sortBy(_._2)
        .reverse
        .take(10)

    def run = {
      new ReportData(tenWithMost, tenWithLeast, runwaysTypesByCountry, mostCommonRunwayIdent)
    }

  }

  def reportData = Report.run

  def query(q: String): Seq[Airport] = {
    if (Data.countries.exists(_.code.equals(Some(q))))
      Data.airports.filter(_.iso_country == Some(q))
    else {
      val foundCodes = Data.countries
        .filter(_.name.getOrElse("").toLowerCase
          contains q.toLowerCase)
        .map(_.code.get)
      foundCodes size match {
        case 0 => throw new NoSuchElementException
        case 1 => query(foundCodes.head)
        case _ => throw new IllegalArgumentException
      }
    }
  }

}