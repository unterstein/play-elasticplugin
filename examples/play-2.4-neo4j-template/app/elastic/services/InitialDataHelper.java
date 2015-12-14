package elastic.services;

import elastic.models.World;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
public class InitialDataHelper {

  public static List<World> makeSomeWorldsAndRelations() {

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

  private static World createWorld(String name, int moons) {
    return ElasticServiceProviderImpl.get().worldRepository.save(new World(name, moons));
  }
}
