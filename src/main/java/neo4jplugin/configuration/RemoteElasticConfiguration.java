package neo4jplugin.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import neo4jplugin.ElasticPlugin;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.cdi.ElasticsearchRepositoryBean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Configuration which is used to connect via rest to the database.
 */
@EnableTransactionManagement
@Configuration
@EnableElasticsearchRepositories(basePackages = "elastic.repositories", repositoryFactoryBeanClass = ElasticsearchRepositoryBean.class)
@ComponentScan("elastic")
public class RemoteElasticConfiguration extends ElasticBaseConfiguration {

  private Client remoteClient;

  @Bean
  @Override
  public ElasticsearchTemplate elasticsearchTemplate() {
    try {
      Config config = ConfigFactory.load();
      remoteClient = TransportClient.builder().build()
          .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(config.getString("elastic.remote.host")), config.getInt("elastic.remote.port")));
    } catch (UnknownHostException o_O) {
      // TODO JU
      o_O.printStackTrace();
    }
    return new ElasticsearchTemplate(remoteClient);
  }

  @Bean
  @Override
  public Client client() {
    return remoteClient;
  }

  @Override
  public void shutDown() {
    remoteClient.close();
  }
}
