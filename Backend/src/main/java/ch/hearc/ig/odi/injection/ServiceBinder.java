/*
 * 2018. Cours outils de développement intégré. ulysse.rosselet@he-arc.ch
 */

package ch.hearc.ig.odi.injection;

import ch.hearc.ig.odi.restresources.PairsRessources;
import java.text.ParseException;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Performs the singleton binding for the RestService mockup persistence object.
 */
public class ServiceBinder extends AbstractBinder {

  @Override
  protected void configure() {
    try {
      //bind(new MockPersistance()).to(MockPersistance.class); //
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
