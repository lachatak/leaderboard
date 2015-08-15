import sbt._
import sbt.Keys._

object Version {

  val marsrover     = "1.0.0"
  val akka          = "2.3.12"
  val akkaMongo     = "0.7.4"
  val spray         = "1.3.3"
  val sprayJson     = "1.3.2"
  val embedMongo    = "1.35"
  val scalazCore    = "7.1.3"
  val scalaTest     = "2.2.0"
  val swagger       = "0.5.1"
  val config        = "1.2.1"
  val logback       = "1.1.2"
  val mockito       = "1.10.19"
  val macwire       = "1.0.5"
}

object Library {
  val akkaActor       = "com.typesafe.akka"        %% "akka-actor"                    % Version.akka
  val akkaSlf4j       = "com.typesafe.akka"        %% "akka-slf4j"                    % Version.akka
  val akkaPersistence = "com.typesafe.akka"        %% "akka-persistence-experimental" % Version.akka
  val akkaTestkit     = "com.typesafe.akka"        %% "akka-testkit"                  % Version.akka
  val akkaMongo       = "com.github.ddevore"       %% "akka-persistence-mongo-casbah" % Version.akkaMongo
  val sprayRouting    = "io.spray"                 %% "spray-routing"                 % Version.spray
  val sprayClient     = "io.spray" 		             %% "spray-client" 		              % Version.spray
  val sprayHttpx      = "io.spray" 		             %% "spray-httpx" 		              % Version.spray
  val sprayHttp       = "io.spray" 		             %% "spray-http" 	                  % Version.spray
  val sprayUtil	      = "io.spray" 		             %% "spray-util" 		                % Version.spray
  val sprayTest	      = "io.spray" 		             %% "spray-testkit" 		            % Version.spray
  val sprayJson	      = "io.spray" 		             %% "spray-json" 		                % Version.sprayJson
  val swagger 	      = "com.gettyimages" 	       %% "spray-swagger" 		            % Version.swagger
  val config          = "com.typesafe" 		         %  "config" 			                  % Version.config
  val embedMongo      = "de.flapdoodle.embed"      %  "de.flapdoodle.embed.mongo"     % Version.embedMongo
  val scalazCore      = "org.scalaz"           	   %% "scalaz-core"                   % Version.scalazCore
  val macwireMacros   = "com.softwaremill.macwire" %% "macros" 			                  % Version.macwire
  val macwireRuntime  = "com.softwaremill.macwire" %% "runtime" 		                  % Version.macwire
  val mockito         = "org.mockito"              %  "mockito-core"                  % Version.mockito
  val scalaTest       = "org.scalatest"            %% "scalatest"                     % Version.scalaTest
}

object Dependencies {

  import Library._

  val leaderboard = Seq(
    akkaActor,
    akkaSlf4j,
    akkaPersistence,
    akkaMongo,
    sprayRouting,
    sprayClient,
    sprayHttpx,
    sprayHttp,
    sprayUtil,
    sprayJson,
    swagger,
    config, 
    scalazCore,
    macwireMacros,
    macwireRuntime,
    embedMongo		  % "test",
    sprayTest 		  % "test",
    mockito       	% "test",
    akkaTestkit     % "test",
    scalaTest     	% "test"
  )

  val leaderboardProtocol = Seq(

  )

  val ranking = Seq(

  )

  val main = Seq(

  )

  val leaderboardE2e = Seq(

  )

  private def deps(modules: ModuleID*): Seq[Setting[_]] = Seq(libraryDependencies ++= modules)
}
