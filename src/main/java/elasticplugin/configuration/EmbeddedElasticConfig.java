package elasticplugin.configuration;


import com.typesafe.config.ConfigFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.cdi.ElasticsearchRepositoryBean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Configuration which is used to connect to embedded database.
 */
@EnableTransactionManagement
@Configuration
@EnableElasticsearchRepositories(basePackages = "elastic.repositories", repositoryFactoryBeanClass = ElasticsearchRepositoryBean.class)
@ComponentScan("elastic")
public class EmbeddedElasticConfig extends ElasticBaseConfiguration {

  private static Settings elasticsearchSettings = Settings.settingsBuilder()
      .put("path.data", ConfigFactory.load().getString("elastic.embeddedTarget"))
      .put("http.port", 8200)  // TODO JU configurable
      .build();

  private static Node node = nodeBuilder().local(true).settings(elasticsearchSettings).node();

  private static Client client = node.client();

  @Bean
  @Override
  public ElasticsearchTemplate elasticsearchTemplate() {
    return new ElasticsearchTemplate(client);
  }

  @Bean
  @Override
  public Client client() {
    return client;
  }

  @Override
  public void shutDown() {
    node.close();
    client.close();
  }
}

