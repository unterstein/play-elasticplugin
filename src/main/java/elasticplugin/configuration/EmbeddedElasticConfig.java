package elasticplugin.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Configuration which is used to connect to embedded database.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "elastic.repositories")
public class EmbeddedElasticConfig extends ElasticBaseConfiguration {

  private static Node node = null;

  private static Client client = null;

  {
    Config config = ConfigFactory.load();
    Settings.Builder builder = Settings.settingsBuilder();
    builder = addStringIfPresent(builder, config, "path.home", "elastic.embeddedTarget");
    builder = addStringIfPresent(builder, config, "cluster.name", "elastic.clusterName");
    builder = addIntegerIfPresent(builder, config, "http.port", "elastic.httpPort");

    node = nodeBuilder().local(true).settings(builder.build()).node();
    client = node.client();
  }

  @Bean
  @Override
  public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
    return new ElasticsearchTemplate(client);
  }

  @Bean
  @Override
  public Client client() {
    return client;
  }

  @Override
  public void destroy() throws Exception {
    node.close();
    client.close();
  }

}

