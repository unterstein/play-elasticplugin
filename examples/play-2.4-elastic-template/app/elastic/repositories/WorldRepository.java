package elastic.repositories;

import elastic.models.World;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
//@Named
//@Singleton
public interface WorldRepository extends ElasticsearchRepository<World, String> {

}
