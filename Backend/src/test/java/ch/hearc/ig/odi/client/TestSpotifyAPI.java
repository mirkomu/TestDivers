package ch.hearc.ig.odi.client;

import static org.hamcrest.CoreMatchers.is;

import ch.hearc.ig.odi.business.SpotifySong;
import org.junit.Assert;
import org.junit.Test;
import org.apache.commons.lang3.StringUtils;

public class TestSpotifyAPI {
  SpotifyClient clientSpotify = new SpotifyClient();

  @Test
  public void testGetAPIToken() {
    String actual = clientSpotify.getAccessToken();
    Assert.assertFalse(StringUtils.isBlank(actual));
  }

  @Test
  public void testgetSongInfo() {
    SpotifySong actual = clientSpotify.getSongInfo("One Kiss");
    Assert.assertThat(actual.getSongid(), is("7ef4DlsgrMEH11cDZd32M6"));
  }
}
