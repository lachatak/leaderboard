name := "leaderboard"

organization := "org.kaloz"

version := "1.0.0-SNAPSHOT"

homepage := Some(url("http://kaloz.org"))

scalaVersion := "2.11.4"

scalacOptions ++= Seq(
  "-deprecation"
  ,"-unchecked"
  ,"-encoding", "UTF-8"
  ,"-Xlint"
)

scalacOptions ++= Seq(
  "-Yclosure-elim",
  "-Yinline"
)

scalacOptions <++= scalaVersion map { sv =>
  if (sv startsWith "2.11") List(
    "-Xverify"
    ,"-feature"
    ,"-language:postfixOps"
  )
  else Nil
}

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

val akka = "2.3.12"
val spray = "1.3.3"

/* dependencies */
libraryDependencies ++= Seq (
  "com.github.nscala-time" %% "nscala-time" % "1.2.0",
  // -- testing --
  "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test",
  // -- Logging --
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  // -- Akka --
  "com.typesafe.akka" %% "akka-testkit" % akka % "test",
  "com.typesafe.akka" %% "akka-actor" % akka,
  "com.typesafe.akka" %% "akka-slf4j" % akka,
  "com.typesafe.akka" %% "akka-persistence-experimental" % akka,
  // -- Spray --
  "io.spray" %% "spray-routing" % spray,
  "io.spray" %% "spray-client" % spray,
  "io.spray" %% "spray-json" % "1.3.2",
  "io.spray" %% "spray-httpx" % spray,
  "io.spray" %% "spray-http" % spray,
  "io.spray" %% "spray-util" % spray,
  "io.spray" %% "spray-testkit" % spray % "test",
  "com.gettyimages" %% "spray-swagger" % "0.5.1",
  // -- json --
  "org.json4s" %% "json4s-jackson" % "3.2.10",
  // -- config --
  "com.typesafe" % "config" % "1.2.1",
  // -- mongo --
  "com.github.ddevore"    %% "akka-persistence-mongo-casbah" % "0.7.4",
  "de.flapdoodle.embed"   % "de.flapdoodle.embed.mongo"      % "1.35",
  "org.scalaz"            %% "scalaz-core"                   % "7.1.3",
  "org.mockito"           %  "mockito-core"                  % "1.10.19" % "test",
  // -- macwire --
  "com.softwaremill.macwire" %% "macros" % "1.0.5",
  "com.softwaremill.macwire" %% "runtime" % "1.0.5"
)

/* you may need these repos */
resolvers ++= Seq(
   Resolver.sonatypeRepo("snapshots")
   ,Resolver.sonatypeRepo("releases")
   ,Resolver.typesafeRepo("releases")
  ,"spray repo" at "http://repo.spray.io"
)

packageArchetype.java_server

seq(Revolver.settings: _*)

net.virtualvoid.sbt.graph.Plugin.graphSettings


