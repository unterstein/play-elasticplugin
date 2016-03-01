package elasticplugin;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import javax.inject.Inject;

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

  @Inject
  public ElasticsearchTemplate template;
}
