package elastic.services;

import elasticplugin.ElasticPlugin;
import elasticplugin.ElasticServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
@Component
public class ElasticServiceProviderImpl extends ElasticServiceProvider {

  @Autowired
  public GalaxyService galaxyService;

  public static ElasticServiceProviderImpl get() {
    return ElasticPlugin.get();
  }
}
