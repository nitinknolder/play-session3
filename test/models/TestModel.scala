package models

import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder

import scala.reflect.ClassTag


class TestModel[T: ClassTag] {
  lazy val app2doo: Application => T = Application.instanceCache [T]
  lazy val repository: T = app2doo (fakeApp)

  def fakeApp: Application = {
    new GuiceApplicationBuilder ()
      .build
  }


}
