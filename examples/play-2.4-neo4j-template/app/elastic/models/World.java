package elastic.models;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Sebastian Hardt (s.hardt@micromata.de)
 *         Date: 27.08.15
 *         Time: 21:39
 */
@Document(indexName = "World")
public class World extends AbstractNode
{

  public String name;

  public int moons;

  public World(String name, int moons) {
    this.name = name;
    this.moons = moons;
  }

  public World() {
  }

}
