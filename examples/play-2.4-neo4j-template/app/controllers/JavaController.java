package controllers;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import elastic.models.World;
import elastic.repositories.WorldRepository;
import elastic.services.ElasticServiceProviderImpl;
import elastic.services.InitialDataHelper;
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

    final WorldRepository worldRepository = ElasticServiceProviderImpl.get().worldRepository;

    if (worldRepository.count() == 0) {
      InitialDataHelper.makeSomeWorldsAndRelations();
    }

    final List<World> allWorlds = Lists.newArrayList(worldRepository.findAll().iterator());
    return ok(views.html.index.render(allWorlds));
  }

}
