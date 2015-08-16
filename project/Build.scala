import sbt._
import Keys._

object Build extends Build {

  lazy val global = BaseSettings.settings ++ ResolverSettings.settings ++ Testing.settings ++ Vagrant.settings
  
  override lazy val settings = super.settings :+ {
    shellPrompt := { s => "[" + scala.Console.BLUE + Project.extract(s).currentProject.id + scala.Console.RESET + "] $ "}
  }

  lazy val root = Project("root", file("."))
    .aggregate(main, leaderboard, leaderboardProtocol, ranking)
    .configs(Configs.all: _*)

  lazy val leaderboard = Project("leaderboard", file("leaderboard"))
    .dependsOn(leaderboardProtocol)
    .configs(Configs.all: _*)
    .settings(global: _*)
    .settings(Dependencies.leaderboard: _*)
    .settings(mainClass in (Compile, run) := Some("org.kaloz.leaderboard.LeaderboardApp"))

  lazy val leaderboardProtocol = Project("leaderboard-protocol", file("leaderboard-protocol"))
    .configs(Configs.all: _*)
    .settings(global: _*)
    .settings(Dependencies.leaderboardProtocol: _*)

  lazy val ranking = Project("ranking", file("ranking"))
    .configs(Configs.all: _*)
    .dependsOn(leaderboardProtocol)
    .settings(global: _*)
    .settings(Dependencies.ranking: _*)

  lazy val main = Project("main", file("main"))
    .configs(Configs.all: _*)
    .dependsOn(leaderboard, leaderboardProtocol, ranking)
    .settings(global: _*)
    .settings(Dependencies.main: _*)
}
