package org.kaloz.leaderboard

import akka.actor.{Props, ActorSystem}
import akka.util.Timeout
import com.softwaremill.macwire._
import scala.concurrent.duration._

trait DI {

  implicit val system = ActorSystem("leaderboard-system")

  implicit val timeout:Timeout =  1 minute

  lazy val leaderboard = system.actorOf(Props(wire[LeaderboardActor]), "leaderboard")
  lazy val leaderboardHttpService = wire[LeaderboardHttpService]
  lazy val leaderboardServiceActor = system.actorOf(Props(wire[LeaderboardServiceActor]), "leaderboard-service")

}
