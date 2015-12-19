package elasticplugin.configuration;

import com.typesafe.config.Config;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
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

  protected static ImmutableSettings.Builder addStringIfPresent(ImmutableSettings.Builder builder, Config config, String builderPath, String path) {
    if (config.hasPath(path)) {
      return builder.put(builderPath, config.getString(path));
    }
    return builder;
  }

  protected static ImmutableSettings.Builder addIntegerIfPresent(ImmutableSettings.Builder builder, Config config, String builderPath, String path) {
    if (config.hasPath(path)) {
      return builder.put(builderPath, config.getInt(path));
    }
    return builder;
  }
}

