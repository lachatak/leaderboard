package org.kaloz.leaderboard

import javax.ws.rs.Path

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import com.wordnik.swagger.annotations._
import org.joda.time.DateTime
import org.kaloz.common.spray.RouteService

import scala.concurrent.ExecutionContext.Implicits.global

@Api(value = "/leaderboard", description = "Operations about leaderboard", position = 0)
class LeaderboardHttpService(leaderboard: ActorRef)(implicit val actorRefFactory: ActorSystem, timeout:Timeout) extends RouteService {

  val route = readRoute ~ updateRoute ~ deleteRoute ~ addRoute ~ searchRoute ~ readRouteForNestedResource

  @ApiOperation(value = "Find a leaderboard by ID", notes = "Returns a leaderboard on ID", httpMethod = "GET", response = classOf[Leaderboard])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "leaderboardId", value = "ID of leaderboard that needs to be fetched", required = true, dataType = "integer", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Leaderboard not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied")
  ))
  def readRoute = get {
    path("leaderboard" / IntNumber) { id =>
      {
        complete((leaderboard ? CreateLeaderBoardCommand(id)).mapTo[Leaderboard])
      }
    }
  }

  @ApiOperation(value = "Updates a leaderboard  in the store with form data.", notes = "", nickname = "updateLeaderboardWithForm", httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "leaderboardId", value = "ID of leaderboard  that needs to be updated", required = true, dataType = "string", paramType = "path"),
    new ApiImplicitParam(name = "name", value = "Updated name of the leaderboard .", required = false, dataType = "string", paramType = "form"),
    new ApiImplicitParam(name = "status", value = "Updated status of the leaderboard .", required = false, dataType = "string", paramType = "form")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Dictionary does not exist.")
  ))
  def updateRoute = post {
    path("leaderboard" / Segment) { id =>
      formFields('name, 'status) { (name, status) =>
        complete("ok")
      }
    }
  }

  @ApiOperation(value = "Deletes a leaderboard ", nickname = "deleteLeaderboard", httpMethod = "DELETE")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "leaderboardId", value = "Leaderboard id to delete", required = true, dataType = "string", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid leaderboard  value")
  ))
  def deleteRoute = delete {
    path("leaderboard" / Segment) { id => complete(s"Deleted $id") }
  }

  @ApiOperation(value = "Add a new leaderboard  to the store", nickname = "addLeaderboard", httpMethod = "POST", consumes = "application/json, application/vnd.custom.leaderboard ")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "Leaderboard object that needs to be added to the store", dataType = "leaderboard", required = true, paramType = "body")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 405, message = "Invalid input")
  ))
  def addRoute = post {
    path("/leaderboard " / Segment) { id => complete(id) }
  }

  @ApiOperation(value = "Searches for a leaderboard ", nickname = "searchLeaderboard", httpMethod = "GET", produces = "application/json, application/xml")
  def searchRoute = get {
    path("leaderboard") {
      complete( Leaderboard(1, "sparky", DateTime.now))
    }
  }

  @Path("/findByTags")
  @ApiOperation(value = "Find Leaderboards by Tags", httpMethod = "GET", nickname = "findLeaderboardsByTags")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "leaderboardId", value = "Tags to filter by", required = true, dataType = "string", paramType = "query", allowMultiple = true)
  ))
  def findByTags = get {
    path("findByTags") {
      complete(List( Leaderboard(1, "sparky", DateTime.now)))
    }
  }

  @Path("/{leaderboardId}/friends/{friendId}")
  @ApiOperation(value = "Find Leaderboard's friend by friendId", httpMethod = "GET", nickname = "findLeaderboardsFriendById")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "leaderboardId", value = "Leaderboard id of the leaderboard  whose friend needs to be fetched", required = true, dataType = "string", paramType = "path"),
    new ApiImplicitParam(name = "friendId", value = "Id of the friend that needs to be fetched", required = true, dataType = "string", paramType = "path")
  ))
  def readRouteForNestedResource = get {
    path("leaderboard" / Segment / "friends" / Segment) {
      (leaderboardId, friendId) => {
        complete( Leaderboard(2, "scooby (sparky's friend)", DateTime.now))
      }
    }
  }
}

object LeaderboardHttpService {
  def apply(leaderboard:ActorRef)(implicit actorRefFactory: ActorSystem, timeout:Timeout) = new LeaderboardHttpService(leaderboard)
}


