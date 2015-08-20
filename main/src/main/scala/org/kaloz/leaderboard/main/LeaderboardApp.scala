package org.kaloz.leaderboard.main

import akka.io.IO
import org.kaloz.leaderboard.LeaderboardModule
import spray.can.Http

object LeaderboardApp extends App with MainModule with LeaderboardModule {

  IO(Http) ! Http.Bind(leaderboardAppServiceActor, LeaderboardAppConfig.HttpConfig.interface, LeaderboardAppConfig.HttpConfig.port)

}
