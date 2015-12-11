package neo4jplugin.configuration;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * This is the base configuration for the Neo4j Plugin.
 *
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
public abstract class ElasticBaseConfiguration extends AnnotationConfigApplicationContext {

  public abstract Client client();

  public abstract void shutDown();

  public abstract ElasticsearchTemplate elasticsearchTemplate();
}

