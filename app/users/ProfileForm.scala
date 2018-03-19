package users

import play.api.data.Form
import play.api.data.Forms.{mapping, text}
case class UserFormData1(name: String, lname: String, email: String)
class ProfileForm {
  val userInfo = Form (mapping (
    "fname" -> text.verifying ("Fill Whatever You Feel Like", _.nonEmpty),
    "lname" -> text.verifying ("Same As Above", _.nonEmpty),
    "email" -> text
  )(UserFormData1.apply)(UserFormData1.unapply))
}
