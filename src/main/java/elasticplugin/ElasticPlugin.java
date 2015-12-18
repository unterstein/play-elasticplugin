package elasticplugin;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.ConfigFactory;
import elasticplugin.configuration.EmbeddedElasticConfig;
import elasticplugin.configuration.RemoteElasticConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import play.Logger;
import play.api.Play;
import play.inject.ApplicationLifecycle;
import play.libs.F;

import java.lang.annotation.Annotation;

/**
 * This is the main singleton handling all the magic elastic stuff :)
 *
 * @author Sebastian Hardt (sebasth@gmx.de)
 * @author Johannes Unterstein (unterstein@me.com)
 */
@Singleton
public class ElasticPlugin {

  public static Logger.ALogger LOGGER = Logger.of(ElasticPlugin.class);

  private static ThreadLocal<ElasticServiceProvider> elasticProvider = new ThreadLocal<>();

  private static AnnotationConfigApplicationContext springContext = null;

  private final static String SERVICE_PROVIDER_NAME_CFG = "elastic.serviceProviderClass";

  private static Class<?> serviceProviderClass = null;

  @Inject
  public ElasticPlugin(ApplicationLifecycle applicationLifecycle) {

    initialize();

    applicationLifecycle.addStopHook(() -> {

      // TODO JU
//      ElasticPlugin.get().template.getGraphDatabaseService().shutdown();
      springContext.stop();

      // TODO clean the application lifecycle here
      return F.Promise.pure(null);
    });
  }


  /**
   * Get the implementation of ServiceProvider all your repositories.
   * This is configured in your application configuration with the key: elastic.serviceProviderClass.
   *
   * @param <E> the implementation class of your ServiceProvider.
   * @return the configured implementation of the ServiceProvider.
   */
  public static <E extends ElasticServiceProvider> E get() {
    elasticProvider.set((ElasticServiceProvider) springContext.getBean(serviceProviderClass));
    return (E) elasticProvider.get();
  }


  /**
   * This does the initialization of the plugin by wiring up the springcontext according to the configuration settings.
   */
  private void initialize() {

    springContext = new AnnotationConfigApplicationContext();

    String serviceProviderClassName = ConfigFactory.load().getString(SERVICE_PROVIDER_NAME_CFG);
    if (StringUtils.isEmpty(serviceProviderClassName) == true) {
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error("No configuration for the elastic ServiceProvider found: " + SERVICE_PROVIDER_NAME_CFG + " must be" + " set for this plugin.");
      }
      return;
    }
    try {
      final ClassLoader classLoader = Play.classloader(Play.current());
      serviceProviderClass = Class.forName(serviceProviderClassName, false, classLoader);
      Annotation annotation = serviceProviderClass.getAnnotation(Component.class);
      if (annotation == null) {
        if (LOGGER.isErrorEnabled()) {
          LOGGER.error("Class : " + serviceProviderClassName + " must be annotated with: " + Component.class
              .getCanonicalName());
        }
        return;
      }
    } catch (ClassNotFoundException e) {
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error("Error while getting elastic class from configuration: " + SERVICE_PROVIDER_NAME_CFG + " = " + serviceProviderClassName, e);
      }
      return;
    }


    final String mode = ConfigFactory.load().getString("elastic.mode");

    if (mode.equals("embedded")) {
      if (LOGGER.isDebugEnabled() == true) {
        LOGGER.debug("Loading embedded configuration");
      }
      springContext.register(EmbeddedElasticConfig.class);
    }

    if (mode.equals("remote")) {
      if (LOGGER.isDebugEnabled() == true) {
        LOGGER.debug("Loading remote configuration");
      }
      springContext.register(RemoteElasticConfiguration.class);
    }


    if (springContext == null) {
      if (LOGGER.isErrorEnabled() == true) {
        LOGGER.error("Could not load config must be: embedded or embeddedWithWebServer");
      }
    }

    springContext.scan("elastic", "elastic.repositories");
    springContext.refresh();
    springContext.start();
    springContext.getAutowireCapableBeanFactory().autowireBean(serviceProviderClass);
    springContext.registerShutdownHook();
  }

}
