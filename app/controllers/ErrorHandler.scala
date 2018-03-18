package controllers

import play.http.HttpErrorHandler
import play.mvc._
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import javax.inject.Singleton


/**We can Set Our own status code for specifying specific
  * page for specific request to appear on a Screen*/

@Singleton class ErrorHandler extends HttpErrorHandler {
  override def onClientError (request: Http.RequestHeader, statusCode: Int, message: String): CompletionStage[Result] = CompletableFuture.completedFuture (Results.status (statusCode, "A client error occurred: " + message))

  override def onServerError (request: Http.RequestHeader, exception: Throwable): CompletionStage[Result] = CompletableFuture.completedFuture (Results.internalServerError ("A server error occurred: " + exception.getMessage))
}