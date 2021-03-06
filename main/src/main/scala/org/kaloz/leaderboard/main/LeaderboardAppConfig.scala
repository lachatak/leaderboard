package org.kaloz.leaderboard.main

import com.typesafe.config.ConfigFactory

object LeaderboardAppConfig {
  private val config = ConfigFactory.load()

  object HttpConfig {
    private val httpConfig = config.getConfig("http")

    lazy val interface = httpConfig.getString("interface")
    lazy val port = httpConfig.getInt("port")
  }

  //Config Settings
}
