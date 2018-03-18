package Model

import javax.inject.Inject

import akka.Done
import play.api.cache.AsyncCacheApi

import scala.concurrent.Future

class UserInfoRepo @Inject () (cache: AsyncCacheApi) {

  def store (userInfo: UserInfo): Future[Done] = {
    cache.set (userInfo.email, userInfo)
  }

  def getUser (email: String): Future[Option[UserInfo]] = {
    cache.get [UserInfo](email)
  }

  case class UserInfo (fname: String, lname: String, email: String)

}
