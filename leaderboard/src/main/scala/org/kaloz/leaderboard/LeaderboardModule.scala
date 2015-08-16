package org.kaloz.leaderboard

import akka.actor.{ActorSystem, ActorRefFactory, Props}
import akka.util.Timeout
import com.softwaremill.macwire._

trait LeaderboardModule {

  def system:ActorSystem
  def timeout:Timeout

  private lazy val leaderboard = system.actorOf(Props(wire[LeaderboardActor]), "leaderboard")

  lazy val leaderboardHttpService = wire[LeaderboardHttpService]

}
