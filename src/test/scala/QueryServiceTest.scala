import java.util.NoSuchElementException

import org.scalatest._
import service.QueryService

/**
  * Created by louis on 18/10/2016.
  */
class QueryServiceTest extends
  FlatSpec with Matchers {

  "Query" should "return some results" in {
    val req = QueryService.query("CN")
    assert(req.nonEmpty)
  }

  "Query" should "return 21476 airports for countryCode US" in {
    val req = QueryService.query("US")
    assert(req.size === 21476)
  }

  "Query" should "return 148 airports for countryCode JP" in {
    val req = QueryService.query("JP")
    assert(req.size === 148)
  }

  "Query" should "return 148 airports from countryName 'Japan'" in {
    val req = QueryService.query("Japan")
    assert(req.size === 148)
  }

  "Query" should "return 148 airports from upper countryName 'JAPAN'" in {
    val req = QueryService.query("JAPAN")
    assert(req.size === 148)
  }

  "Query" should "return 1 airport from incomplete countryName 'iechtenstein'" in {
    val req = QueryService.query("iechtenstein")
    assert(req.size === 1)
  }

  "Query" should "throw no element exeption when no country is found" in {
    assertThrows[NoSuchElementException](QueryService.query("gfggfgfdgfd"))
  }

  "Query" should "throw illegal argument exeption when two or more countries are found" in {
    assertThrows[IllegalArgumentException](QueryService.query("states"))
  }

  "Query" should "return airports and associated runways" in {
    val req = QueryService.query("US")
    assert(req(10).runways.size === 1)
    println(s"airport: ${req(10)}")
    println(s"has runway: ${req(10).runways.get.toString()}")
    assert(req(16).runways.size === 1)
    assert(req(1600).runways.size === 1)
  }

  "Query" should "obtain good runway information" in {
    val req = QueryService.query("US")
    val airport = req(10)
    val runway = airport.runways.get.head
    val airport2 = req(1500)
    val runway2 = airport2.runways.get.head
    assert(runway.length_ft.getOrElse("") === "2600")
    assert(runway2.length_ft.getOrElse("") === "150")
  }

  "Report" should "return results" in {
    QueryService.Report.run
  }

}
