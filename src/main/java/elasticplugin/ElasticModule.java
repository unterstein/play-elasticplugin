package elasticplugin;

import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;

public class ElasticModule extends Module {

  @Override
  public Seq<Binding<?>> bindings(Environment environment, Configuration configuration) {
    return seq(
        bind(ElasticPlugin.class).toSelf().eagerly()
    );
  }

}