package controllers

import javax.inject.Inject

import Model.{UserData, UserProfileInfo}
import play.api.Logger
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import users.ProfileForm

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserController @Inject () (protected val dbConfigProvider: DatabaseConfigProvider, profileForm: ProfileForm, userProfileInfo: UserProfileInfo, cc: ControllerComponents) extends AbstractController (cc) {
  def index: Action[AnyContent] = Action { implicit request =>
    Ok (views.html.profile (profileForm.userInfo))
  }

  def userProfile () = Action { implicit request => {
    Ok (views.html.outputUser ())

  }
  }

  def addUser (): Action[AnyContent] = Action.async { implicit request =>
    println ("should com here>>>>>>>>>>>>>")
    profileForm.userInfo.bindFromRequest ().fold (
      formWithError => {
        println ("should com here>>>>>>>>>>>>>111")
        Future.successful (BadRequest (views.html.profile (formWithError)))
      },
      dataUser => {
        Logger.info ("HEre>>>>>>>>>>>>> " + dataUser)

        val user = UserData (1, dataUser.name, dataUser.lname, dataUser.email)
        userProfileInfo.store (user).map {
          case true =>
            Logger.info ("stored>>>>>>>>>>>>> ")
            Redirect (routes.UserController.userProfile ()).withSession ("email" -> dataUser.email).flashing ("Success" -> "user Successfully Created")
          case false =>
            Logger.error ("error>>>>>>>>>>>>> ")
            Ok ("Go to hell")
        }
      }
    )
  }


}
