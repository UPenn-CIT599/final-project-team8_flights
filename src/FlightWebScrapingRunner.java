/**
 * This is the runner class for FlightWebScraping. 
 * It takes flight url info from GUI class and parse it to the target url address. 
 * A hard-coded url address is used for testing before we integrate all classes.
 * 
 * 
 * @author cit591 Spring 2020 team8
 *
 */
public class FlightWebScrapingRunner {
  
  //debug mode configuration
  //0 = normal mode, no debug
  //1 = web scraping debug
  //2 = html parsing debug
  Integer debugMode = 0;
  
  String[] userInput = new String[6];
  String baseUrl = null;
  String departureAirport = null;
  String arrivalAirport = null;
  String departureDate = null;
  String arrivalDate = null;
  String ranking = null;
  String url = null;

  /**
   * FlightWebScrapingRunner constructor, 
   * parse input string to correct url address
   * 
   * @param userInput in comma separated String in this format:
   * "baseUrl,departureAirport,arrivalAirport,departureDate,
   * arrivalDate,ranking
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
   * url address getter
   * 
   * @return parsed url address String
   */
  public String getUrl() {
    return url;
  }

  /**
   * debugMode getter returns current debugMode;
   * 0 = normal mode, 1 = web scraping test mode, 2 = html parsing test mode
   * @return debugMode in Integer
   */
  public Integer getDebugMode() {
    return debugMode;
  }

  /**
   * debugMode setter sets current debugMode;
   * 0 = normal mode, 1 = web scraping test mode, 2 = html parsing test mode
   * @param debugMode in Integer to set current debug mode
   */
  public void setDebugMode(Integer debugMode) {
    this.debugMode = debugMode;
  }

  /**
   * Pass url address and debugMode for FlightWebScraping class to run
   */
  public void run() {
    FlightWebScraping flight = new FlightWebScraping(url);
    flight.htmlScrapingParsing();
  }
  
  /**
   * main class to run FlightWebScraping class, with url address "hard-coded"
   * remove this after integration
   * 
   
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
  */
}
