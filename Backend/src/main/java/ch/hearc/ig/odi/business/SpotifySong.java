package ch.hearc.ig.odi.business;

public class SpotifySong {
  private String Songid;
  private String songName;
  private String artistName;
  private int numero;
  private String album;

  public SpotifySong(String songid, String songName) {
    this.Songid = songid;
    this.songName = songName;
  }

  public String getSongid() {
    return Songid;
  }

  public void setSongid(String songid) {
    Songid = songid;
  }

  public String getSongName() {
    return songName;
  }

  public void setSongName(String songName) {
    this.songName = songName;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }
}
