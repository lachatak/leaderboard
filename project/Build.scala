import sbt._
import Keys._

object Build extends Build {

  override lazy val settings = super.settings :+ {
    shellPrompt := { s => "[" + scala.Console.BLUE + Project.extract(s).currentProject.id + scala.Console.RESET + "] $ "}
  }

  lazy val root = Project("root", file("."))
    .aggregate(common, main, leaderboard, leaderboardProtocol, ranking)
    .configs(Configs.all: _*)

  lazy val main = Project("main", file("main"))
    .dependsOn(common, leaderboard, leaderboardProtocol, ranking)
    .configs(Configs.all: _*)
    .settings(BaseSettings.settings: _*)
    .settings(Dependencies.main: _*)
    .settings(mainClass in (Compile, run) := Some("org.kaloz.leaderboard.main.LeaderboardApp"))

  lazy val leaderboard = Project("leaderboard", file("leaderboard"))
    .dependsOn(common, leaderboardProtocol)
    .configs(Configs.all: _*)
    .settings(BaseSettings.settings: _*)
    .settings(Dependencies.leaderboard: _*)

  lazy val leaderboardProtocol = Project("leaderboard-protocol", file("leaderboard-protocol"))
    .configs(Configs.all: _*)
    .settings(BaseSettings.settings: _*)
    .settings(Dependencies.leaderboardProtocol: _*)

  lazy val common = Project("common", file("common"))
    .configs(Configs.all: _*)
    .settings(BaseSettings.settings: _*)
    .settings(Dependencies.common: _*)

  lazy val ranking = Project("ranking", file("ranking"))
    .dependsOn(common, leaderboardProtocol)
    .configs(Configs.all: _*)
    .settings(BaseSettings.settings: _*)
    .settings(Dependencies.ranking: _*)

}
