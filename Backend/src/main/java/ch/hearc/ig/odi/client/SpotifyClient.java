package ch.hearc.ig.odi.client;

import ch.hearc.ig.odi.business.SpotifySong;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SpotifyClient {

  String SPOTIFY_ACCOUNT_URL = "https://accounts.spotify.com/api/token";
  String SPOTIFY_API_BASE_URL = "https://api.spotify.com/v1/";
  String BASE64_CREDENTIALS = "ODg5NDZhODI1NDdkNDg3NmI3ODVkZWZkYzcxYjg1NTI6NTYzNjY3OTM1NjA2NDBlYjkyN2ViNTYwM2Y3ZWY5ZDQ";

  String token = "";

  public String getAccessToken() {
    Client client = ClientBuilder.newClient();
    Form form = new Form();
    form.param("grant_type", "client_credentials");
    Entity entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED);
    String jsonResponse = client.target(SPOTIFY_ACCOUNT_URL)
        .request()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
        .header(HttpHeaders.AUTHORIZATION, "Basic " + BASE64_CREDENTIALS)
        .post(entity, String.class);
    client.close();

    JSONObject obj = new JSONObject(jsonResponse);
    String tokenReceived = obj.getString("access_token");

    return tokenReceived;
  }

  public SpotifySong getSongInfo(String trackName) throws JSONException {
    if(StringUtils.isBlank(token)) {
      this.token = getAccessToken();
    }

    Client client = ClientBuilder.newClient();
    String jsonResponse = client.target(SPOTIFY_API_BASE_URL + "search")
        .queryParam("q", trackName)
        .queryParam("type", "track")
        .request()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
        .get(String.class);
    client.close();

    JSONObject obj = new JSONObject(jsonResponse);
    JSONObject track = obj.getJSONObject("tracks").getJSONArray("items").getJSONObject(0);
    String id = track.getString("id");
    String name = track.getString("name");
    String artist = "";
    for(int i = 0; i<track.getJSONArray("artists").length(); i++) {
      artist = artist + track.getJSONArray("artists").getJSONObject(i).getString("name") + ", ";
    }
    artist = artist.substring(0, artist.length() - 2);
    String album = track.getJSONObject("album").getString("name");
    SpotifySong songToReturn = new SpotifySong(id, name);
    songToReturn.setArtistName(artist);
    songToReturn.setAlbum(album);

    return songToReturn;
  }
}
