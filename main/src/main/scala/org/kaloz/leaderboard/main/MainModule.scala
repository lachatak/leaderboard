package org.kaloz.leaderboard.main

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import com.softwaremill.macwire._
import org.kaloz.leaderboard.LeaderboardModule

import scala.concurrent.duration._

trait MainModule extends LeaderboardModule {

  implicit val system = ActorSystem("leaderboard-main")

  implicit val timeout:Timeout = 1 minute

  val services = Seq(leaderboardHttpService)

  lazy val leaderboardAppServiceActor = system.actorOf(Props(wire[LeaderboardAppServiceActor]), "leaderboard-service")

}
