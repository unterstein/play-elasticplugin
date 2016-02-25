package elasticplugin.configuration;

import com.typesafe.config.Config;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
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

  protected static Settings.Builder addStringIfPresent(Settings.Builder builder, Config config, String builderPath, String path) {
    if (config.hasPath(path)) {
      return builder.put(builderPath, config.getString(path));
    }
    return builder;
  }

  protected static Settings.Builder addIntegerIfPresent(Settings.Builder builder, Config config, String builderPath, String path) {
    if (config.hasPath(path)) {
      return builder.put(builderPath, config.getInt(path));
    }
    return builder;
  }
}

