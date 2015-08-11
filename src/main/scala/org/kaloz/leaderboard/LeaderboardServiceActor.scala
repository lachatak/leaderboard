package org.kaloz.leaderboard

import spray.routing._

import scala.reflect.runtime.universe._

class LeaderboardServiceActor(leaderboardHttpService: LeaderboardHttpService) extends HttpServiceActor with SwaggerHttpService {

  override def actorRefFactory = context

  override def apiTypes = Seq(typeOf[LeaderboardHttpService])

  def receive = runRoute(leaderboardHttpService.routes ~ swaggerRoutes)

}
