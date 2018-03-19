//package controllers
//
//import javax.inject._
//
//import Model.UserInfoRepo
//import play.api.mvc._
//import users.UserForm
//
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.Future
//
///**
//  * This controller creates an `Action` to handle HTTP requests to the
//  * application's home page.
//  */
//@Singleton
//class HomeController @Inject () (userForm: UserForm, userInfoRepo: UserInfoRepo, cc: ControllerComponents) extends AbstractController (cc) {
//
//  def index: Action[AnyContent] = Action.async { implicit request =>
//    //throw new Exception
//    Future.successful (Ok (views.html.index (userForm.userInfoForm)))
//  }
//
//  def storeData: Action[AnyContent] = Action.async { implicit request =>
//    userForm.userInfoForm.bindFromRequest ().fold (
//      formWithError => {
//        Future.successful (BadRequest (views.html.index (formWithError)))
//      },
//      data => {
//        userInfoRepo.getUser (data.email).flatMap {
//          optionalRecord =>
//            optionalRecord.fold {
//              val record = userInfoRepo.UserInfo (data.fname, data.lname, data.email)
//              userInfoRepo.store (record).map {
//                _ => Ok ("stored")
//              }
//
//            } {
//              _ => Future.successful (InternalServerError ("User exist SuccessFully"))
//            }
//        }
//      })
//  }
//
//  def getData (email: String): Action[AnyContent] = Action.async { implicit request =>
//    userInfoRepo.getUser (email).map { optionalRecord =>
//      optionalRecord.fold {
//        NotFound ("Oops! user not found")
//      } {
//        record =>
//          Ok (s"User's FullName is: ${record.fname},${record.lname},${record.email}")
//      }
//    }
//  }
//
//}
