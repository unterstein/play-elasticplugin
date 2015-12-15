package elastic.repositories;

import elastic.models.World;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
@Repository
public interface WorldRepository extends ElasticsearchRepository<World, Long> {

}
