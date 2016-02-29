package elasticplugin;

import com.google.inject.AbstractModule;

public class ElasticModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ElasticPlugin.class).asEagerSingleton();
    }
}