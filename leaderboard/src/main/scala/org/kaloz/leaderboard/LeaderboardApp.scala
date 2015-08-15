package org.kaloz.leaderboard

import akka.io.IO
import spray.can.Http

object LeaderboardApp extends App with DI {

  IO(Http) ! Http.Bind(leaderboardServiceActor, LeaderboardConfig.HttpConfig.interface, LeaderboardConfig.HttpConfig.port)

}
