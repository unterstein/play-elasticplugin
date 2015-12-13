package controllers;

import com.google.inject.Inject;
import elastic.models.World;
import elastic.services.ElasticServiceProviderImpl;
import elastic.services.GalaxyService;
import elasticplugin.ElasticPlugin;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
public class JavaController extends Controller {

  @Inject
  ElasticPlugin elasticPlugin;

  public Result index() {

    final GalaxyService galaxyService = ElasticServiceProviderImpl.get().galaxyService;

    if (galaxyService.getNumberOfWorlds() == 0) {
      galaxyService.makeSomeWorldsAndRelations();
    }

    final List<World> allWorlds = galaxyService.getAllWorlds();
    return ok(views.html.index.render(allWorlds));
  }

}
