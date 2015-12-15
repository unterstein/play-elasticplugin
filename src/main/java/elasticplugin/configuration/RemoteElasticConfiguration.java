package elasticplugin.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.cdi.ElasticsearchRepositoryBean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

/**
 * Configuration which is used to connect via rest to the database.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "elastic.repositories", repositoryFactoryBeanClass = ElasticsearchRepositoryBean.class)
@ComponentScan("elastic")
public class RemoteElasticConfiguration extends ElasticBaseConfiguration {

  private Client client;

  @Bean
  @Override
  public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
    Config config = ConfigFactory.load();
    client = new TransportClient(ImmutableSettings.settingsBuilder().build())
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(config.getString("elastic.remote.host")), config.getInt("elastic.remote.port")));
    return new ElasticsearchTemplate(client);
  }

  @Bean
  @Override
  public Client client() {
    return client;
  }

  @Override
  public void destroy() throws Exception {
    client.close();
  }

}
