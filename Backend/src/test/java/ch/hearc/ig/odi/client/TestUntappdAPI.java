package ch.hearc.ig.odi.client;

import static org.junit.Assert.assertThat;

import ch.hearc.ig.odi.business.UntappdBeer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Comparator;

public class TestUntappdAPI {

  UntappdClient clientUntappd = new UntappdClient();

  @Test
  public void testGetPunkIPABeer() throws Exception {
    UntappdBeer actual = clientUntappd.getBeerInfo("Punk IPA");
    UntappdBeer expected = new UntappdBeer(5702, "Punk IPA", "IPA - American", 5.6D);
    Assert.assertEquals(actual.getBeerId(), expected.getBeerId());

  }
}
