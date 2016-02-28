package modules;

import com.google.inject.AbstractModule;
import elasticplugin.ElasticPlugin;

public class ElasticModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ElasticPlugin.class).asEagerSingleton();
    }
}