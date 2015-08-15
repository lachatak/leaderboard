import sbt._
import Keys._

object Build extends Build {

  lazy val global = BaseSettings.settings ++ ResolverSettings.settings ++ Testing.settings ++ Vagrant.settings
  
  override lazy val settings = super.settings :+ {
    shellPrompt := { s => "[" + scala.Console.BLUE + Project.extract(s).currentProject.id + scala.Console.RESET + "] $ "}
  }

  lazy val root = Project("root", file("."))
    .aggregate(main, leaderboard, leaderboardProtocol, ranking, leaderboardE2e)
    .configs(Configs.all: _*)

  lazy val leaderboard = Project("leaderboard", file("leaderboard"))
    .dependsOn(leaderboardProtocol)
    .configs(Configs.all: _*)
    .settings(global: _*)
    .settings(libraryDependencies ++= Dependencies.leaderboard)
    .settings(mainClass in (Compile, run) := Some("org.kaloz.leaderboard.LeaderboardApp"))

  lazy val leaderboardProtocol = Project("leaderboard-protocol", file("leaderboard-protocol"))
    .configs(Configs.all: _*)
    .settings(global: _*)
    .settings(libraryDependencies ++= Dependencies.leaderboardProtocol)

  lazy val ranking = Project("ranking", file("ranking"))
    .configs(Configs.all: _*)
    .dependsOn(leaderboardProtocol)
    .settings(global: _*)
    .settings(libraryDependencies ++= Dependencies.ranking)

  lazy val main = Project("main", file("main"))
    .configs(Configs.all: _*)
    .dependsOn(leaderboard, leaderboardProtocol, ranking)
    .settings(global: _*)
    .settings(libraryDependencies ++= Dependencies.main)

  lazy val leaderboardE2e = Project("leaderboard-e2e", file("leaderboard-e2e"))
    .configs(Configs.all: _*)
    .dependsOn(main, leaderboard, leaderboardProtocol, ranking)
    .settings(global: _*)
    .settings(libraryDependencies ++= Dependencies.leaderboardE2e)

}
