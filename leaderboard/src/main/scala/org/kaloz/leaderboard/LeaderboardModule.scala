package org.kaloz.leaderboard

import akka.actor.ActorSystem
import akka.util.Timeout

trait LeaderboardModule {

  implicit def system:ActorSystem
  implicit def timeout:Timeout

  private lazy val leaderboard = system.actorOf(LeaderboardActor.props(), "leaderboard")

  lazy val leaderboardHttpService:LeaderboardHttpService = LeaderboardHttpService(leaderboard)

}
