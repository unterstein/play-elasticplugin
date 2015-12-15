package controllers

import elastic.models.World
import elastic.repositories.WorldRepository
import elastic.services.{InitialDataHelper, ElasticServiceProviderImpl}
import play.api.mvc._
import scala.collection.JavaConversions._

class ScalaController extends Controller {

  def index = Action {
    def worldRepository: WorldRepository = ElasticServiceProviderImpl.get().worldRepository

    if (worldRepository.count() == 0) {
      InitialDataHelper.makeSomeWorldsAndRelations()
    }

    def allWorlds: java.util.List[World] = worldRepository.findAll().toList

    Ok(views.html.index.render(allWorlds))
  }

}
