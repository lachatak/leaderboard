package org.kaloz.leaderboard.main

import com.gettyimages.spray.swagger.{SwaggerHttpService => SpraySwaggerHttpService}
import com.wordnik.swagger.model.ApiInfo
import org.kaloz.common.spray.RouteService
import spray.routing.HttpServiceActor

import scala.reflect.runtime.universe._

trait SwaggerHttpService {
  self: HttpServiceActor =>

  private val mirror = runtimeMirror(getClass.getClassLoader)

  def serviceApis: Seq[RouteService]

  private def converted: Seq[Type] = serviceApis.map(c => mirror.classSymbol(c.getClass).toType)

  private lazy val swaggerService = new SpraySwaggerHttpService {
    override def apiTypes = SwaggerHttpService.this.converted

    override def apiVersion = "2.0"

    override def baseUrl = "/"

    override def docsPath = "api-docs"

    override def actorRefFactory = SwaggerHttpService.this.actorRefFactory

    override def apiInfo = Some(new ApiInfo("Leaderboard API", "Lederboard application with Spray, Swagger, Akka Persistence", "TOC Url", "krisztian.lachata@gmail.com", "Apache V2", "http://www.apache.org/licenses/LICENSE-2.0"))
  }

  def swaggerRoute = swaggerService.routes ~
    get {
      pathPrefix("swagger-ui") {
        pathEndOrSingleSlash {
          getFromResource("swagger-ui/index.html")
        }
      } ~
        getFromResourceDirectory("swagger-ui")
    }
}
