package elastic.services;


import elasticplugin.ElasticPlugin;
import elasticplugin.ElasticServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticServiceProviderImpl extends ElasticServiceProvider
{


  @Autowired
  public GalaxyService galaxyService;

  public static ElasticServiceProviderImpl get() {
    return ElasticPlugin.get();
  }
}
