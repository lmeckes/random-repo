name := "random"
version := "1.0"
scalaVersion := "2.11.8"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % "2.4.2"
libraryDependencies += "de.heikoseeberger" %% "akka-http-json4s" % "1.5.3"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.4.0"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.4.0"
//libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "2.4.2" % "test"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

TaskKey[Unit]("airportServer") := (runMain in Compile).toTask(" server.AirportRESTServer").value