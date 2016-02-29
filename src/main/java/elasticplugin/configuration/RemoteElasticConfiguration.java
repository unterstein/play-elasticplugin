package elasticplugin.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

/**
 * Configuration which is used to connect via rest to the database.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "elastic.repositories")
public class RemoteElasticConfiguration extends ElasticBaseConfiguration {

  private static TransportClient client;

  {
    Config config = ConfigFactory.load();
    Settings.Builder builder = Settings.settingsBuilder();
    builder = addStringIfPresent(builder, config, "cluster.name", "elastic.clusterName");

    client = TransportClient.builder().settings(builder).build();
    try {
      client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(config.getString("elastic.remoteHost")), config.getInt("elastic.remotePort")));
    } catch (Exception o_O) {
      throw new RuntimeException("Spring Configuration RemoteElasticConfiguration could not be initialized", o_O);
    }
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
    client.close();
  }

}
