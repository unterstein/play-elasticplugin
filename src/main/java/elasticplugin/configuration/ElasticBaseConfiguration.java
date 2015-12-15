package elasticplugin.configuration;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * This is the base configuration for the Elastic Plugin.
 *
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
public abstract class ElasticBaseConfiguration implements DisposableBean {

  public abstract Client client();

  public abstract ElasticsearchTemplate elasticsearchTemplate() throws Exception;

}

