package users

import play.api.data.Form
import play.api.data.Forms._

case class UserInformation(fname: String, lname: String, email: String)
class UserForm {

  val userInfoForm = Form (mapping (
    "fname" -> text.verifying("Kuch to fill kar!!!",_.nonEmpty),
    "lname" -> text.verifying("niche bhi bharle kuch!!!",_.nonEmpty),
    "email" -> email
  )(UserInformation.apply)(UserInformation.unapply))
}
