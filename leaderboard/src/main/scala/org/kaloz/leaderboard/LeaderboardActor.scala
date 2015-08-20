package org.kaloz.leaderboard

import akka.actor.{Props, Actor, ActorLogging}
import org.joda.time.DateTime

class LeaderboardActor extends Actor with ActorLogging{

  override def receive: Receive = {
    case CreateLeaderBoardCommand(id) =>
      val leaderboard = Leaderboard(2, "scooby (sparky's friend)", DateTime.now)
      log.info(s"leaderboard $id created $leaderboard!")
      sender ! leaderboard
  }
}

object LeaderboardActor {
  def props() = Props[LeaderboardActor]
}
