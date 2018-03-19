package models

import Model.UserInfoRepo
import akka.Done
import org.specs2.mutable.Specification
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.reflect.ClassTag

/**During Testing Our application is Down So we need to mAke fake App So that We Can run Our Te
st Cases*/

//class ModelsTest[T: ClassTag] {
//  def fakeApp: Application = {
//    new GuiceApplicationBuilder()
//      .build
//  }
//
//  lazy val app2doo: Application => T = Application.instanceCache[T]
//  lazy val repository : T = app2doo(fakeApp)
//}
//class UserInfoRepoTest extends Specification {
//
//  val repo = new ModelsTest[UserInfoRepo]
//
//  "user info repository" should  {
//    "associate detail of a user" in  {
//      val user = repo.repository.UserInfo("nitin","arora","nitinarora1519@gmail.com")
//      val storeResult = Await.result(repo.repository.store(user),Duration.Inf)
//      storeResult must equalTo(Done)
//    }
//
//  }
//
//}
