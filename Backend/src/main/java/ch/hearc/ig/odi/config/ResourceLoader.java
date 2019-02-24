/*
 * 2018. Cours outils de développement intégré. ulysse.rosselet@he-arc.ch
 */

package ch.hearc.ig.odi.config;

import ch.hearc.ig.odi.filter.CORSFilter;
import ch.hearc.ig.odi.injection.ServiceBinder;
import ch.hearc.ig.odi.injection.ServiceFeature;
import ch.hearc.ig.odi.restresources.PairsRessources;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Registers all resources with Jersey
 */
public class ResourceLoader extends ResourceConfig {

  public ResourceLoader() {
    register(ServiceFeature.class);
    register(PairsRessources.class);
    register(CORSFilter.class);
    registerInstances(new ServiceBinder());

  }

}

