package ch.hearc.ig.odi.persistance;

import ch.hearc.ig.odi.business.SpotifySong;
import ch.hearc.ig.odi.business.UntappdBeer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.SqlDateDeserializer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class Conn {
  //Attributs
  private Connection cnn = null;

  //Ouvre la connection à la base de données
  public void openConnection() throws SQLException {
    // Se connecter à la BD Oracle
    cnn = new DBDOracleConnectionPoolDataSource().getConnection();
  }

  //Ferme la connection
  public void closeConnection() throws SQLException {
    cnn.close();
  }

  public void commit() throws SQLException {
    cnn.commit();
  }

  public HashMap<String, String> getPairs() throws SQLException {
    HashMap<String, String> pairs = new HashMap<>();
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT beer, song FROM MusicAndBeersPairs ")
        .append("ORDER BY beer ASC");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      //Exécute la requête SQL et recoit un curseur
      ResultSet rsCurseur = pStmt.executeQuery();
      // Parcours du curseur
      while (rsCurseur.next()) {
        pairs.put(rsCurseur.getString("beer"), rsCurseur.getString("song"));
      }
      // Fermeture du curseur
      rsCurseur.close();
    }
    return pairs;
  }

  public void addBeer(UntappdBeer beer) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL INSERT
    requeteSQL.append("Insert into UNTAPPDBEER (ID,NAME,STYLE,ABV,BREWERY) values (?,?,?,?,?)");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, String.valueOf(beer.getBeerId()));
      pStmt.setString(2, beer.getBeerName());
      pStmt.setString(3, beer.getStyle());
      pStmt.setString(4, String.valueOf(beer.getAbv()));
      pStmt.setString(5, beer.getBrewery());
      //Exécute la requête SQL et recoit un curseur
      pStmt.executeQuery();
    }
  }

  public String getStyleFromID(int idBeer) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT style FROM Untappdbeer WHERE ID = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, String.valueOf(idBeer));
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      return rs.getString("style");
    }
  }

  public void addSong(SpotifySong song) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL INSERT
    requeteSQL.append("Insert into SPOTIFYSONG (ID,NAME,ARTIST, ALBUM) values (?,?,?,?)");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, String.valueOf(song.getSongid()));
      pStmt.setString(2, song.getSongName());
      pStmt.setString(3, song.getArtistName());
      pStmt.setString(4, song.getAlbum());
      //Exécute la requête SQL et recoit un curseur
      pStmt.executeQuery();
    }
  }

  public int getNumeroFromUID(String id) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT Numero FROM SPOTIFYSONG WHERE ID = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, id);
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      return rs.getInt("Numero");
    }
  }

  public void addPair(int beerID, int songID) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL INSERT
    requeteSQL.append("Insert into MusicAndBeersPairs (beer,song) values (?,?)");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, String.valueOf(beerID));
      pStmt.setString(2, String.valueOf(songID));
      //Exécute la requête SQL et recoit un curseur
      pStmt.executeQuery();
    }
  }

  public void addPairIDs(String beerID, String songID) throws SQLException {
    StringBuilder requeteSQLselect = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQLselect.append("SELECT numero FROM Spotifysong WHERE id = ?");
    int songNum;
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQLselect.toString())) {
      pStmt.setString(1, songID);
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      songNum = rs.getInt("numero");
    }
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL INSERT
    requeteSQL.append("Insert into MusicAndBeersPairs (beer,song) values (?,?)");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, beerID);
      pStmt.setString(2, String.valueOf(songNum));
      //Exécute la requête SQL et recoit un curseur
      pStmt.executeQuery();
    }
  }

  public int getPairID(int beerID) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT song FROM MusicAndBeersPairs WHERE beer = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, String.valueOf(beerID));
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      return rs.getInt("song");
    }
  }

  public SpotifySong getPair(int beerID) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT s.ID, s.NAME, s.ARTIST, s.ALBUM FROM MusicAndBeersPairs m ")
        .append("JOIN SPOTIFYSONG s ON s.numero = m.SONG JOIN UNTAPPDBEER b on b.ID = m.BEER ")
        .append("WHERE b.ID = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, String.valueOf(beerID));
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      SpotifySong song = new SpotifySong(rs.getString("ID"), rs.getString("NAME"));
      return song;
    }
  }

  public SpotifySong getPairFromStyle(int beerID) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT s.ID, s.NAME, s.ARTIST, s.ALBUM FROM MusicAndBeerStylesPairs m ")
        .append("JOIN SPOTIFYSONG s ON s.numero = m.SONG JOIN UNTAPPDBEER b on b.style = m.style ")
        .append("WHERE b.ID = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, String.valueOf(beerID));
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      SpotifySong song = new SpotifySong(rs.getString("ID"), rs.getString("NAME"));
      return song;
    }
  }

  public boolean doesBeerExistinBD(int beerID) throws SQLException {
    boolean exist = false;
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT name FROM UntappdBeer WHERE id = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, String.valueOf(beerID));
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      if(!StringUtils.isBlank(rs.getString("name"))) {
        exist = true;
      }
    } catch (Exception e) {

    }
    return exist;
  }

  public int doesSongExistinBD(String idSong) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT numero FROM SpotifySong WHERE id = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, idSong);
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      return rs.getInt("numero");
    } catch (Exception e) {
      return 0;
    }
  }

  public String getStyleFromBeerinBD(String beerID) throws SQLException {
    StringBuilder requeteSQL = new StringBuilder();
    //Construit la requête SQL SELECT
    requeteSQL.append("SELECT style FROM UntappdBeer WHERE id = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQL.toString())) {
      pStmt.setString(1, beerID);
      //Exécute la requête SQL et recoit un curseur
      ResultSet rs = pStmt.executeQuery();
      rs.next();
      return rs.getString("style");
    }
  }

  public void insertPairStyle(String beerID, String songID) throws SQLException {
    StringBuilder requeteSQLstyle = new StringBuilder();
    //Construit la requête SQL INSERT
    requeteSQLstyle.append("Insert into MusicAndBeerStylesPairs (style,song) values (?,?)");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQLstyle.toString())) {
      String style = getStyleFromBeerinBD(beerID);
      int numeroSong = getNumeroFromUID(songID);
      pStmt.setString(1, style);
      pStmt.setString(2, String.valueOf(numeroSong));
      //Exécute la requête SQL et recoit un curseur
      pStmt.executeQuery();
    } catch (SQLException e) {
      throw e;
    }
  }

  public void updatePairStyle(String beerID, String songID) throws SQLException {
    StringBuilder requeteSQLstyle = new StringBuilder();
    //Construit la requête SQL INSERT
    requeteSQLstyle.append("UPDATE MusicAndBeerStylesPairs SET song = ? WHERE style = ?");
    try (PreparedStatement pStmt = cnn.prepareStatement(requeteSQLstyle.toString())) {
      pStmt.setString(1, String.valueOf(getNumeroFromUID(songID)));
      String style = getStyleFromBeerinBD(beerID);
      pStmt.setString(2, style);
      //Exécute la requête SQL et recoit un curseur
      int rowsUpdated = pStmt.executeUpdate();
      if(rowsUpdated <= 0) {
        insertPairStyle( beerID, songID);
      }
    }
  }
}