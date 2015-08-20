package org.kaloz.common.spray

import spray.routing.{HttpService, Route}

trait RouteService extends HttpService {

  def route:Route
}

object RouteServiceConversion {

  implicit def toRoute(routeService:RouteService):Route = routeService.route

  implicit def toRouteList(routeServices:List[RouteService]):List[Route] = routeServices.map(_.route)

}