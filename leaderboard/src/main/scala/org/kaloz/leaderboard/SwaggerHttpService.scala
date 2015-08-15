package org.kaloz.leaderboard

import com.gettyimages.spray.swagger.{SwaggerHttpService => SpraySwaggerHttpService}
import com.wordnik.swagger.model.ApiInfo
import spray.routing.HttpServiceActor

trait SwaggerHttpService {
  self: HttpServiceActor =>

  def apiTypes: Seq[scala.reflect.runtime.universe.Type]

  val swaggerService = new SpraySwaggerHttpService {
    override def apiTypes = SwaggerHttpService.this.apiTypes

    override def apiVersion = "2.0"

    override def baseUrl = "/"

    override def docsPath = "api-docs"

    override def actorRefFactory = context

    override def apiInfo = Some(new ApiInfo("Leaderboard API", "Lederboard application with Spray, Swagger, Akka Persistence", "TOC Url", "krisztian.lachata@gmail.com", "Apache V2", "http://www.apache.org/licenses/LICENSE-2.0"))
  }

  def swaggerRoutes = swaggerService.routes ~
    get {
      pathPrefix("swagger-ui") {
        pathEndOrSingleSlash {
          getFromResource("swagger-ui/index.html")
        }
      } ~
        getFromResourceDirectory("swagger-ui")
    }
}
