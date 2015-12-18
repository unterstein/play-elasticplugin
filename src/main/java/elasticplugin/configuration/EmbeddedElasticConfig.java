package elasticplugin.configuration;

import com.typesafe.config.ConfigFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
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

  private static Settings elasticsearchSettings = ImmutableSettings.settingsBuilder()
      .put("path.home", ConfigFactory.load().getString("elastic.embeddedTarget"))
      .put("http.port", 8200)  // TODO JU configurable
      .build();

  private static Node node = nodeBuilder().local(true).settings(elasticsearchSettings).node();

  private static Client client = node.client();

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

