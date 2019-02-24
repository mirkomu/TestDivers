package ch.hearc.ig.odi.client;

import ch.hearc.ig.odi.business.UntappdBeer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import org.json.*;

public class UntappdClient {

  String UNTAPPD_BASE_URL = "https://api.untappd.com/v4/";
  String CLIENT_ID = "93216526B0C187C51B67561DEE7C292724E0234E";
  String CLIENT_SECRET = "EA9579101FC9C33ABF7B6C97D2C2293DFFFA60FE";

  public UntappdBeer getBeerInfo(String PotentialName) throws JSONException {
    Client client = ClientBuilder.newClient();
    String jsonResponse = client.target(UNTAPPD_BASE_URL + "search/beer")
        .queryParam("client_id", CLIENT_ID)
        .queryParam("client_secret", CLIENT_SECRET)
        .queryParam("q", PotentialName)
        .request(MediaType.APPLICATION_JSON)
        .get(String.class);
    client.close();

    JSONObject obj = new JSONObject(jsonResponse);
    JSONObject firstBeerFound = obj.getJSONObject("response").getJSONObject("beers").getJSONArray("items").getJSONObject(0)
        .getJSONObject("beer");
    JSONObject firstBreweryFound = obj.getJSONObject("response").getJSONObject("beers").getJSONArray("items").getJSONObject(0)
        .getJSONObject("brewery");
    int bid = firstBeerFound.getInt("bid");
    String beer_name = firstBeerFound.getString("beer_name");
    String beer_style = firstBeerFound.getString("beer_style");
    double beer_abv = firstBeerFound.getDouble("beer_abv");
    UntappdBeer beerToReturn = new UntappdBeer(bid, beer_name, beer_style, beer_abv);
    beerToReturn.setBrewery(firstBreweryFound.getString("brewery_name"));

    return beerToReturn;
  }

}