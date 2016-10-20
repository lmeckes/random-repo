package server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.Directives
import akka.stream.{ActorMaterializer, Materializer}
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.{DefaultFormats, jackson}
import service.QueryService

import scala.io.StdIn

/**
  * Created by louis on 18/10/2016.
  */
object AirportRESTServer {

  def main(args: Array[String]) {

    implicit val system = ActorSystem("airport-system")
    implicit val mat = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    def route(implicit mat: Materializer) = {

      import Directives._
      import Json4sSupport._

      implicit val serialization = jackson.Serialization
      implicit val formats = DefaultFormats

      get {
        pathSingleSlash(getFromResource("public/index.html")) ~
          path("airports" / Segment) { searchString =>
            complete(
              ToResponseMarshallable(
                QueryService.query(searchString)
              ))
          } ~
          path("reportData") {
            complete(
              ToResponseMarshallable(
                QueryService.reportData
              ))
          } ~
          getFromResourceDirectory("public")
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 7890)

    println(s"Airport Server online at http://localhost:7890/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}