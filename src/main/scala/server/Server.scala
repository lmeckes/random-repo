package server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.Directives
import akka.stream.{ActorMaterializer, Materializer}
import org.json4s._
import service.Oracle

import scala.io.StdIn

/**
  * Created by louis on 18/10/2016.
  */
object Server {

  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher


    def route(implicit mat: Materializer) = {
      import Directives._
      import org.json4s._
      import org.json4s.native.Serialization
      import org.json4s.native.Serialization.{ read, write, writePretty }

      implicit val serialization = native.Serialization // or native.Serialization
      implicit val formats = DefaultFormats

      pathSingleSlash {
        get {
          complete(ToResponseMarshallable(write("test")))
        }
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 7890)

    println(s"Server online at http://localhost:7890/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}