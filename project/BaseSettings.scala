import sbt._
import Keys._
import net.virtualvoid.sbt.graph.Plugin.graphSettings
import BuildKeys._
import spray.revolver.RevolverPlugin._

object BaseSettings {

  lazy val settings =
  Seq(
    version := "1.0.0",
    organization := "org.kaloz.leaderboard",
    description := "Leaderboard DEMO Application",
    scalaVersion := "2.11.6",
    homepage := Some(url("http://kaloz.org")),
    vagrantFile :=  (baseDirectory in ThisBuild).value / ".." / "Vagrantfile",
    scalacOptions := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.8",
      "-language:postfixOps",
      "-language:implicitConversions"
    ),
    javacOptions := Seq(
      "-Xlint:unchecked", 
      "-Xlint:deprecation"
    )
  ) ++
  Revolver.settings ++
  graphSettings

}
