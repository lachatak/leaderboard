package org.kaloz.leaderboard.main

import akka.actor.Props
import org.kaloz.common.spray.RouteService
import spray.routing.{HttpServiceActor, Route}

class LeaderboardAppServiceActor(override val serviceApis: List[RouteService]) extends HttpServiceActor with SwaggerHttpService {

  val routes:Seq[Route] = swaggerRoute :: serviceApis.map(_.route)

  def receive = runRoute(routes.reduce(_ ~ _))
}

object LeaderboardAppServiceActor {
  def props(routes: Seq[RouteService]) = Props(classOf[LeaderboardAppServiceActor], routes)
}