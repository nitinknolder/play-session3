package Model


import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.{ProvenShape}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future


case class UserData (id: Int, name: String, lname: String, email: String)


trait UserProfileRepositoryTable extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  val userProfileQuery: TableQuery[UserProfile] = TableQuery [UserProfile]

  class UserProfile (tag: Tag) extends Table[UserData](tag, "usersprofile") {


    def * : ProvenShape[UserData] = (id, name, lname, email) <> (UserData.tupled, UserData.unapply)

    def id: Rep[Int] = column [Int]("id", O.PrimaryKey, O.AutoInc)

    def name: Rep[String] = column [String]("name")

    def lname: Rep[String] = column [String]("lname")

    def email: Rep[String] = column [String]("email")

  }

}



//Writing

class UserProfileInfo @Inject () (protected val dbConfigProvider: DatabaseConfigProvider) extends UserProfileBaseRepository with
UserProfileBaseRepositoryImplementation with UserProfileRepositoryTable with UserProfileReadRepository with
UserProfileReadRepositoryImpl

trait UserProfileBaseRepository {
  def store (data: UserData): Future[Boolean]
}

trait UserProfileBaseRepositoryImplementation extends UserProfileBaseRepository {
  self: UserProfileRepositoryTable =>

  import profile.api._

  def store (userData: UserData): Future[Boolean] =
    db.run (userProfileQuery += userData) map (_ > 0)
}

trait UserProfileReadRepository {
  def findByEmail(email: String): Future[Option[UserData]]
}

trait UserProfileReadRepositoryImpl extends UserProfileReadRepository {
  self: UserProfileRepositoryTable =>

  import profile.api._

  def findByEmail (email: String): Future[Option[UserData]] = {
    val queryResult = userProfileQuery.filter (_.email.toLowerCase === email.toLowerCase).result.headOption
    db.run (queryResult)
  }
}






