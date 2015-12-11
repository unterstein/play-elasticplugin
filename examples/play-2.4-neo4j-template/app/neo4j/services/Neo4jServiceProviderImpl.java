package neo4j.services;


import neo4jplugin.ElasticPlugin;
import neo4jplugin.ElasticServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Neo4jServiceProviderImpl extends ElasticServiceProvider
{


  @Autowired
  public GalaxyService galaxyService;

  public static Neo4jServiceProviderImpl get() {
    return ElasticPlugin.get();
  }
}
