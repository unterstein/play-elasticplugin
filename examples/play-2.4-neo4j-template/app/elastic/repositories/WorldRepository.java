package elastic.repositories;

import elastic.models.World;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
public interface WorldRepository extends ElasticsearchCrudRepository<World, Long> {

}
