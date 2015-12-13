package controllers

import elastic.models.World
import elastic.services.{ElasticServiceProviderImpl, GalaxyService}
import play.api.mvc._

class ScalaController extends Controller {


  def index = Action {
    def galaxyService: GalaxyService = ElasticServiceProviderImpl.get().galaxyService

    if (galaxyService.getNumberOfWorlds == 0) {
      galaxyService.makeSomeWorldsAndRelations()
    }

    def allWorlds: java.util.List[World] = galaxyService.getAllWorlds

    Ok(views.html.index.render(allWorlds))
  }

}
