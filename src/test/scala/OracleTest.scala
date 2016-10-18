import org.scalatest._
import service.Oracle

/**
  * Created by louis on 18/10/2016.
  */
class OracleTest extends
  FlatSpec with Matchers {

  "Query" should "return some results" in {
    val req = Oracle.query("CN")
    assert(req.nonEmpty)
  }

  "Query" should "return 21476 airports for US" in {
    val req = Oracle.query("US")
    assert(req.size == 21476)
  }

  "Query" should "return 148 airports for JP" in {
    val req = Oracle.query("JP")
    assert(req.size == 148)
  }

  "Query" should "return airports and associated runways" in {
    val req = Oracle.query("US")
    assert(req(10).runways.size == 1)
    println(s"airport: ${req(10)}")
    println(s"has runway: ${req(10).runways.get.toString()}")
    assert(req(16).runways.size == 1)
    assert(req(1600).runways.size == 1)
  }

}
