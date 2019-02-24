package ch.hearc.ig.odi.business;

public class UntappdBeer {
  private int beerId;
  private String beerName;
  private String style;
  private double abv;
  private int ibu;
  private String imgURL;
  private String brewery;

  public UntappdBeer(int beerId, String beerName, String style, double abv) {
    this.beerId = beerId;
    this.beerName = beerName;
    this.style = style;
    this.abv = abv;
  }

  public int getBeerId() {
    return beerId;
  }

  public void setBeerId(int beerId) {
    this.beerId = beerId;
  }

  public String getBeerName() {
    return beerName;
  }

  public void setBeerName(String beerName) {
    this.beerName = beerName;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public double getAbv() {
    return abv;
  }

  public void setAbv(double abv) {
    this.abv = abv;
  }

  public int getIbu() {
    return ibu;
  }

  public void setIbu(int ibu) {
    this.ibu = ibu;
  }

  public String getImgURL() {
    return imgURL;
  }

  public void setImgURL(String imgURL) {
    this.imgURL = imgURL;
  }

  public String getBrewery() {
    return brewery;
  }

  public void setBrewery(String brewery) {
    this.brewery = brewery;
  }
}
