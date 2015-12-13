package elastic.services;

import elastic.models.World;
import elastic.repositories.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Hardt (s.hardt@micromata.de)
 *         Date: 27.08.15
 *         Time: 21:34
 */
@Service
public class GalaxyService {

  @Autowired
  private WorldRepository worldRepository;

  public long getNumberOfWorlds() {
    return worldRepository.count();
  }

  public List<World> getAllWorlds() {
    List<World> result = new ArrayList<World>();
    for (World world : worldRepository.findAll()) {
      result.add(world);
    }
    return result;
  }

  public List<World> makeSomeWorldsAndRelations() {

    Logger.debug("Creating test data set in database.");

    List<World> worlds = new ArrayList<World>();

    // Solar worlds
    worlds.add(createWorld("Mercury", 0));
    worlds.add(createWorld("Venus", 0));
    worlds.add(createWorld("Earth", 1));
    worlds.add(createWorld("Mars", 2));
    worlds.add(createWorld("Jupiter", 63));
    worlds.add(createWorld("Saturn", 62));
    worlds.add(createWorld("Uranus", 27));
    worlds.add(createWorld("Neptune", 13));

    // Norse worlds
    worlds.add(createWorld("Alfheimr", 0));
    worlds.add(createWorld("Midgard", 1));
    worlds.add(createWorld("Muspellheim", 2));
    worlds.add(createWorld("Asgard", 63));
    worlds.add(createWorld("Hel", 62));


    Logger.debug("Creating test data done, have fun with it :).");

    return worlds;
  }

  private World createWorld(String name, int moons) {
    return worldRepository.save(new World(name, moons));
  }
}
