package elastic.repositories;

import elastic.models.World;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
public interface WorldRepository extends ElasticsearchRepository<World, Long> {

}
