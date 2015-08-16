package org.kaloz.common.spray

import spray.routing.{HttpService, Route}

trait RouteService extends HttpService {

  def routes:Route
}
