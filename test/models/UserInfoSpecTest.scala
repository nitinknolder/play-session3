package models

import Model.{UserData, UserProfileInfo}
import org.specs2.mutable.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class UserInfoSpecTest extends Specification {

  val repo = new TestModel[UserProfileInfo]
  val user = UserData (1,"Nitin", "Arora", "nitinarora1519@gmail.com")

  "user info repository" should {
    "associate detail of a user" in {
      val storeResult = Await.result (repo.repository.store(user), Duration.Inf)
      storeResult must equalTo (true)
    }

  }

    "associate detail of a user" in {
      val storeResult = Await.result (repo.repository.findByEmail("nitinarora1519@gmail.com"), Duration.Inf)
      storeResult must beSome(UserData(1,"Nitin","Arora","nitinarora1519@gmail.com"))
    }

  "associate detail of a user" in {
    val storeResult = Await.result (repo.repository.findByEmail("nitinarora1@gmail.com"), Duration.Inf)
    storeResult must beNone
  }
}

