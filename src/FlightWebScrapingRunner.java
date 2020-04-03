
public class FlightWebScrapingRunner {
  
  //debug mode
  //0 = no debug
  //1 = web scraping debug
  //2 = html parsing debug
  Integer debugMode = 2;
  
  String[] userInput = new String[6];
  String baseUrl = null;
  String departureAirport = null;
  String arrivalAirport = null;
  String departureDate = null;
  String arrivalDate = null;
  String ranking = null;
  String url = null;

  /**
   * 
   * @param userInput
   */
  public FlightWebScrapingRunner(String userInput) {
    
    this.userInput = userInput.split(",");
    baseUrl = this.userInput[0];
    departureAirport = this.userInput[1];
    arrivalAirport = this.userInput[2];
    departureDate = this.userInput[3];
    arrivalDate = this.userInput[4];
    ranking = this.userInput[5];
    this.url = baseUrl + departureAirport + "-" + arrivalAirport + "/"
        + departureDate + "/" + arrivalDate + "?sort=" + ranking;
   
  }
  
  /**
   * 
   * @return
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * 
   */
  public void run() {
    FlightWebScraping flight = new FlightWebScraping(url, debugMode);
    flight.HtmlScrapingParsing();
  }
  
  public static void main(String[] args) {
    
    String baseUrl = "https://www.kayak.com/flights/";
    String departureAirport = "YVR";
    String arrivalAirport = "PEK";
    String departureDate = "2020-04-27";
    String arrivalDate = "2020-05-27";
    String ranking = "price_a";
    
    FlightWebScrapingRunner newFlight = new FlightWebScrapingRunner(baseUrl + ","
        + departureAirport + "," + arrivalAirport + "," + departureDate + ","
        + arrivalDate + "," + ranking);
    
    newFlight.run();
  }
}
