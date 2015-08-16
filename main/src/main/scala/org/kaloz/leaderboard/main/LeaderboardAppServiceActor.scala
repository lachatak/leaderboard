package org.kaloz.leaderboard.main

import org.kaloz.common.spray.RouteService
import org.kaloz.leaderboard.LeaderboardHttpService
import spray.routing.HttpServiceActor

import scala.reflect.runtime.universe._

class LeaderboardAppServiceActor(services: Seq[RouteService]) extends HttpServiceActor with SwaggerHttpService {

  override def actorRefFactory = context

  override def apiTypes = Seq(typeOf[LeaderboardHttpService])
//  override def apiTypes = services.map( s => typeOf[s.type])

  def receive = runRoute(services.map(_.routes).fold(swaggerRoutes)(_ ~ _))
}