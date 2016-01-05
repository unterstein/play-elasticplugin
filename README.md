# Play ElasticSearchPlugin

This is a simple Play 2 plugin, for Elasticsearch.

I got the idea from https://github.com/tuxBurner/play-neo4jplugin ! THANKS A LOT Seppel (@tuxBurner) for your awesome plugin and skeleteon !

You can use Play 2.4 with spring-data-elasticsearch without this plugin, see my example: https://github.com/unterstein/play-spring-data-elasticsearch-example

What i didn't liked that i can't call elasticsearch in the static way, like Ebean etc...

So here we go, i created this plugin :)

## Current Version

**0.2.0**

* kickstart the project


## Installation (using sbt)

You will need to add the following resolver in your `build.sbt` file:

```scala
resolvers += "unterstein.github.io" at "http://unterstein.github.io/repo"

resolvers += "Spring milestones" at "http://repo.spring.io/milestone"
```

Add a dependency on the following artifact:

```scala
libraryDependencies += "com.github.unterstein" %% "play-elasticplugin" % "0.2.0"


Settings for the plugin go into the `conf/application.conf`:

```
elastic.serviceProviderClass="elastic.services.ElasticServiceProvider" # the provider class which holds the annotated elasticsearch beans

elastic.mode="embedded" # mode to run embedded or remote

# embedded elastic config
```
elastic.embeddedTarget="target/elastic" # where to put the embedded target
elastic.clusterName="myCluster"
```

# remote elastic config
```
elastic.remoteHost="localhost"
elastic.remotePort=9300
elastic.clusterName="myCluster"
```

## Usage

All elasticsearch relevant stuff must go to `app/elastic/`

All elasticsearch repositories go to `app/elastic/repositories/`


You need a class which must extend from `elasticplugin.ElasticServiceProvider` and must be configured in the `application.conf` under the key `elastic.serviceProviderClass`:

Example:
```java

    @Component
    public class ElasticServiceProviderImpl extends ElasticServiceProvider {

      @Autowired
      public ElasticUserRepository elasticUserRepository;

      public static ElasticServiceProvider get() {
        return ElasticPlugin.get();
      }
    }
```

To access your repository you can call: `ElasticServiceProviderImpl.get().elasticUserRepository.<do magic elastic stuff>`


Take a look into the examples


## TODO

* TBD


## Old Versions

0.2.0 Bugfix: Problems with spring-context when reloading during re-compile through play

0.1.0 Initial working version
