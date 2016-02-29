package elasticplugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * This is the main class of the ElasticServiceProvider. This must be overwritten by you and can than hold some spring
 * stuff.
 *
 * <code>
 * \@Component
 *  public class ElasticServiceProviderImpl extends ElasticServiceProvider
 *  {
 *
 *
 *    \@Autowired
 *    public GalaxyService galaxyService;
 *
 *    public static ElasticServiceProviderImpl get() {
 *      return ElasticPlugin.get();
 *     }
 *   }
 * </code>
 * @author Sebastian Hardt (s.hardt@micromata.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
public class ElasticServiceProvider {

  @Autowired
  public ElasticsearchTemplate template;
}
