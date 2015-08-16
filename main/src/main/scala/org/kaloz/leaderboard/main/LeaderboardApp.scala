package org.kaloz.leaderboard.main

import akka.io.IO
import spray.can.Http

object LeaderboardApp extends App with MainModule {

  IO(Http) ! Http.Bind(leaderboardAppServiceActor, LeaderboardAppConfig.HttpConfig.interface, LeaderboardAppConfig.HttpConfig.port)

}
