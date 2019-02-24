package ch.hearc.ig.odi.restresources;

import ch.hearc.ig.odi.business.SpotifySong;
import ch.hearc.ig.odi.business.UntappdBeer;
import ch.hearc.ig.odi.client.SpotifyClient;
import ch.hearc.ig.odi.client.UntappdClient;
import ch.hearc.ig.odi.persistance.Conn;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.SqlDateDeserializer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

@Path("pairing")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public class PairsRessources {

  private final Logger logger = LogManager.getLogger(PairsRessources.class.getName());
  private UntappdClient clientUntappd = new UntappdClient();
  private SpotifyClient clientSpotify = new SpotifyClient();
  private Conn connection = new Conn();

  @GET
  @Path("/test")
  @Produces(MediaType.APPLICATION_JSON)
  public String getTest() {

      return "toto";  }
/*
  @GET
  @Path("getbeer")
  public UntappdBeer getBeerFromUntappd(@QueryParam("beerName") String beerName) throws SQLException {
    try {
      connection.openConnection();
      UntappdBeer beer = clientUntappd.getBeerInfo(beerName);
      if(!connection.doesBeerExistinBD(beer.getBeerId())) {
        connection.addBeer(beer);
      }
      connection.commit();
      connection.closeConnection();
      return beer;
    } catch (JSONException e) {
      throw new WebApplicationException(Status.NOT_FOUND);
    }
  }

  @GET
  @Path("getsong")
  public SpotifySong getSongFromSpotify(@QueryParam("songName") String songName) throws SQLException {
    try {
      connection.openConnection();
      SpotifySong song = clientSpotify.getSongInfo(songName);
      int numeroSongBD = connection.doesSongExistinBD(song.getSongid());
      if (numeroSongBD == 0) {
        connection.addSong(song);
        numeroSongBD = connection.doesSongExistinBD(song.getSongid());
      }
      song.setNumero(numeroSongBD);
      connection.commit();
      connection.closeConnection();
      return song;
    } catch (JSONException e) {
      throw new WebApplicationException(Status.NOT_FOUND);
    }
  }

  @POST
  @Path("addpair")
  public void addPairing(@QueryParam("beerID") String beerID, @QueryParam("songID") String songID) throws SQLException {
    boolean alreadyAdded = false;
    connection.openConnection();
    try {
      connection.addPairIDs(beerID, songID);
    } catch (SQLException e) {
      alreadyAdded = true;
    }
    finally {
      connection.updatePairStyle(beerID, songID);
      connection.commit();
      connection.closeConnection();
      if(alreadyAdded) {
        throw new WebApplicationException(Status.CONFLICT);
      }
    }
  }

  @GET
  @Path("getpairedsong")
  public SpotifySong getSong(@QueryParam("beerName") String beerName) throws SQLException {
    try {
      connection.openConnection();
      UntappdBeer beer = clientUntappd.getBeerInfo(beerName);
      if(!connection.doesBeerExistinBD(beer.getBeerId())) {
        connection.addBeer(beer);
      }
      SpotifySong song;
      try {
        song = connection.getPair(beer.getBeerId());
      } catch (SQLException e) {
        try {
          song = connection.getPairFromStyle(beer.getBeerId());
        } catch (SQLException ex) {
          throw new WebApplicationException(Status.NO_CONTENT);
        }
      }
      connection.commit();
      connection.closeConnection();
      return song;
    } catch (JSONException e) {
      throw new WebApplicationException(Status.NOT_FOUND);
    }
  }
  */
}
