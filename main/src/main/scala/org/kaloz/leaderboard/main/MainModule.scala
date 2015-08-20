package org.kaloz.leaderboard.main

import akka.actor.ActorSystem
import akka.util.Timeout
import org.kaloz.leaderboard.LeaderboardHttpService

import scala.concurrent.duration._

trait MainModule {

  implicit val system = ActorSystem("leaderboard-main")
  implicit val timeout: Timeout = 10 second

  private val services = Seq(leaderboardHttpService)
  lazy val leaderboardAppServiceActor = system.actorOf(LeaderboardAppServiceActor.props(services), "leaderboard-app-service")

  def leaderboardHttpService: LeaderboardHttpService
}
