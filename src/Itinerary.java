public class Itinerary {
  
  private String rank = null;
  private String price = null;
  private String provider = null;
  private Flights flyOut = new Flights();
  private Flights flyIn = new Flights();
  private String link = null;
  private String details = null;
  
  public Itinerary() {  
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public Flights getFlyOut() {
    return flyOut;
  }

  public void setFlyOut(Flights flyOut) {
    this.flyOut = flyOut;
  }

  public Flights getFlyIn() {
    return flyIn;
  }

  public void setFlyIn(Flights flyIn) {
    this.flyIn = flyIn;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }
  
  public String getItinerary() {
    
    String itinerary = null;
    itinerary = rank + ("|") + price + ("|") + provider 
        + ("|") + flyOut.getFlights() + ("|") + flyIn.getFlights()  
        + ("|") + link + ("|") + details;
    return itinerary;
  }

}
