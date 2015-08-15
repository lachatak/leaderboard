package org.kaloz.leaderboard

import org.joda.time.DateTime
import org.kaloz.leaderboard.DateTimeJsonProtocol._
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class Leaderboard(id: Int, name: String, birthDate: DateTime)

object Leaderboard extends DefaultJsonProtocol with SprayJsonSupport{
  implicit val marshaller = jsonFormat(Leaderboard.apply, "id", "name", "birthDate")
}

case class CreateLeaderBoardCommand(id:Int)
