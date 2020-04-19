import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
//JSoup for Web html scraping
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//Selenium for active web loading
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class scrapes flight information from Kayak.com
 * and stores the data in a .txt file for processing.
 * <p>
 * This class consists of two main methods.
 * <li>
 * <b>ActiveWebHtmlLoading</b> method uses selenium WebDriver to instantiate and 
 * control Chrome web browser to get flight information from kayak.com with specific 
 * searching criteria including departure/arrival airports and dates. It stores
 * html source into a html file for scraping.
 * <li>
 * <b> WebHtmlScraping</b> method loads the stored html file and scrapes flight info in
 * interest. From the scraped flight information, each itinerary and flight data is stored
 * into a .txt file for data processing class.
 *  
 * @author cit591 Spring2020 team-8
 */
public class ActiveFlightWebScraping {

  ArrayList<Itinerary> itinerary = new ArrayList<>();

  private String url = null;
  private Document document;
  private String inputHtmlFileName = "KayakFlightInfo.html";
  private String scrapedFileName = "ScrapedFlightData.txt";
  private String baseUrl = "https://www.kayak.com/flights/";
  private boolean scrapingFileWritten = false;
  private int maxPageLoad = 2; //number of pages to load from kayak query
  private int debugMode = 0;

  /**
   * 
   */
  public ActiveFlightWebScraping() {
    
    //String baseUrl = "https://www.kayak.com/flights/";
    String departureAirport = "YVR";
    String arrivalAirport = "ARN";
    String departureDate = "2020-09-01";
    String returnDate = "2020-09-30";
    String ranking = "price_a";
    
    //initialize flight scraping info
    //kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=bestflight_a
    this.url = baseUrl
        + departureAirport + "-" + arrivalAirport 
        + "/" 
        + departureDate 
        + "/" 
        + returnDate 
        + "?sort=" 
        + ranking;
    
  }
  
  public ActiveFlightWebScraping(String departureAirport, String arrivalAirport, String departureDate, String returnDate) {
	    /*
	    //String baseUrl = "https://www.kayak.com/flights/";
	    departureAirport = departureAirport;
	    arrivalAirport = arrivalAirport;
	    departureDate = departureDate;
	    arrivalDate = arrivalDate;

	    */
	  
	    String ranking = "price_a";
	    
	    //initialize flight scraping info
	    //kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=bestflight_a
	    this.url = baseUrl
	        + departureAirport + "-" + arrivalAirport 
	        + "/" 
	        + departureDate 
	        + "/" 
	        + returnDate 
	        + "?sort=" 
	        + ranking;
	    
	  }
  
  
  
  /**
   * 
   * @param url
   */
  public ActiveFlightWebScraping(String url) {
    this(url, 0);
  }

  /**
   * 
   * @param url
   * @param debugMode
   */
  public ActiveFlightWebScraping(String url, int debugMode) {
    this.url = url;
    this.debugMode = debugMode;
    
    // debugMode check
    // mode 0 = normal operation mode
    // mode 1 = JUnit test using a known html file for testing
    if (debugMode == 1) {
      inputHtmlFileName = "JUnitTest" + inputHtmlFileName;
      scrapedFileName = "JUnitTest" + scrapedFileName;
    }
  }
  
  public void setDebugMode (int debugMode) {
    this.debugMode = debugMode;
  }

  /**
   * 
   */
  public void ActiveWebHtmlLoading() {

    ChromeOptions ops = new ChromeOptions();
    //ops.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) "
    //    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
    //ops.addArguments("--headless");
    ops.addArguments("--disable-notifications");
    ops.addArguments("--disable-popup-blocking");
    ChromeDriver browser = new ChromeDriver(ops);

    // browser.manage().window().setPosition(new Position(0,0));
    browser.manage().window().setSize(new Dimension(5, 5));
    // browser.navigate().to(baseurl);
    browser.get(url);

    try {

      TimeUnit.SECONDS.sleep(10);

    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
    }

    // get the actual value of the title
    String title = browser.getTitle();
    System.out.println(title);

    // refresh page to get rid of the pop-up
    browser.navigate().refresh();

    // load more pages for flight processing
    int page = 1;
    while (page <= maxPageLoad) {

      // add delays for page to load
      try {

        System.out.println("loading page..." + page);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("still loading...");
        TimeUnit.SECONDS.sleep(15);

      } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
      }
      
      try {
        // search and click on load more button
        String loadButtonXpath = "//a[@class = \"moreButton\"]";
        browser.findElement(By.xpath(loadButtonXpath)).click();
        
      } catch (Exception e) {
        
        System.out.println("Looks like there is no more result to load.");
        
      } finally {
        
        System.out.println("continue...");
                
      }
        
      page++;
    }

    // wait until page is loaded
    WebDriverWait wait = new WebDriverWait(browser, 30);
    wait.until(webDriver -> ((JavascriptExecutor) webDriver)
        .executeScript("return document.readyState").equals("complete"));

    System.out.println("page load complete.");

    FileWriter htmlFile;

    try {

      htmlFile = new FileWriter(inputHtmlFileName);
      BufferedWriter html = new BufferedWriter(htmlFile);

      html.write(browser.getPageSource());

      htmlFile.close();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    browser.close();

  }

  /**
   * 
   */
  public void WebHtmlScraping() {

    File fin;
    FileWriter fout;

    try {

      fin = new File(inputHtmlFileName);
      Document document = Jsoup.parse(fin, "UTF-8");

      fout = new FileWriter(scrapedFileName);
      BufferedWriter scraped = new BufferedWriter(fout);


      Itinerary itinerary = new Itinerary();

      Elements flightResults;

      // itinerary parsing start here

      flightResults = document.select("div.Base-Results-HorizonResult");

      for (Element result : flightResults) {

        itinerary.setRank(result.attr("aria-label").split(":")[0]);
        // System.out.println(itinerary.getRank());

        // Elements itineraryDetails = result.select("p");
        itinerary.setDetails(result.select("p").text());
        // System.out.println(itinerary.getDetails());

        // https://www.ca.kayak.com/book/
        // flight?code=IFAiezE8PO.18wgSzIaLAlgpzrTH2pViLaYAeeTFjgE.60354.84e5c6d3c7cc5944205fba986de42c5b&h=175613a35035&sub=E-1d2f0a07811
        Elements itineraryLink =
            result.select("div.resultWrapper div.resultInner " + "div.inner-grid div.col-price "
                + "div.Flights-Results-FlightPriceSection div.Common-Booking-MultiBookProvider a");
        itinerary.setLink("https://www.ca.kayak.com/" + itineraryLink.attr("href"));
        // System.out.println(itinerary.getLink());

        Elements itineraryPriceProvider =
            result.select("div.resultWrapper div.resultInner " + "div.inner-grid div.col-price "
                + "div.Flights-Results-FlightPriceSection div.Common-Booking-MultiBookProvider a ");
        // + "> span.price");
        itinerary.setPrice(itineraryPriceProvider.get(0).text().split(" ")[0]);
        // System.out.println(itinerary.getPrice());
        itinerary.setProvider(itineraryPriceProvider.get(0).text().split(" ")[1]);
        // System.out.println(itinerary.getProvider());

        // fly-out parsing
        // section times contains
        System.out.print("Flyout info...");
        Flights flyOut = new Flights();

        Elements resultFlyOutTime = result.select(
            "li.flight.with-gutter div.container " + "div.section.times div.top > span.time-pair");
        flyOut.setDepartureTime(resultFlyOutTime.get(0).text());
        // System.out.print(flyOut.getDepartureTime());
        flyOut.setArrivalTime(resultFlyOutTime.get(1).text());
        // System.out.print(flyOut.getArrivalTime());

        flyOut.setCarrier(result
            .select("li.flight.with-gutter div.container div.section.times div.bottom").text());
        // System.out.print(flyOut.getCarrier());

        flyOut.setStops(
            result.select("li.flight.with-gutter div.container div.section.stops").text());
        // System.out.print(flyOut.getStops());

        flyOut.setDuration(
            result.select("li.flight.with-gutter div.container div.section.duration div.top").get(0)
                .text());
        // System.out.print(flyOut.getDuration());

                		
        
        Elements resultFlyOutAirport =
            result.select("li.flight.with-gutter div.container div.section.duration div.bottom");
        String flyOutAirport = resultFlyOutAirport.text();
        System.out.println(flyOutAirport);
        // scraped string YVR - SFO
        // parse first three characters and the last three characters for airportCode
        flyOut.setDepartureAirport(flyOutAirport.substring(0,3));
        System.out.print("Dept airport for flying out is " + flyOut.getDepartureAirport()+"\n");
        flyOut.setArrivalAirport(flyOutAirport.substring(flyOutAirport.length() - 3));
        System.out.print("arrival airport for flying out is " + flyOut.getArrivalAirport()+"\n");

        Elements resultFlyOutDetails =
            result.select("div.detailsWrapper div.section-content div.content-card");// div.segment-row
                                                                                     // ");
        // + "div.right-column.segment-details div.planeDetails");
        flyOut.setFlightDetails(resultFlyOutDetails.get(0)
            .select("div.right-column.segment-details div.planeDetails").text());
        System.out.println(flyOut.getFlightDetails());

        System.out.print(flyOut.getFlights());
        System.out.print("\n");
        // }

        // fly-out parsing
        // section times contains
        System.out.print("Flyin info...");
        Flights flyIn = new Flights();

        Elements resultflyInTime =
            result.select("li.flight div.container div.section.times div.top > span.time-pair");
        flyIn.setDepartureTime(resultflyInTime.get(2).text());
        // System.out.print(flyIn.getDepartureTime());
        flyIn.setArrivalTime(resultflyInTime.get(3).text());
        // System.out.print(flyIn.getArrivalTime());

        flyIn.setCarrier(
            result.select("li.flight div.container div.section.times div.bottom").get(1).text());
        // System.out.print(flyIn.getCarrier());

        flyIn.setStops(result.select("li.flight div.container div.section.stops").get(1).text());
        // System.out.print(flyIn.getStops());

        flyIn.setDuration(
            result.select("li.flight div.container div.section.duration div.top").get(1).text());
        // System.out.print(flyIn.getDuration());

        //maybe try to parse with string index 0-2 and 6-8 or whatever
        Elements resultFlyInAirport =
            result.select("li.flight div.container div.section.duration div.bottom");
        String flyInAirport = resultFlyInAirport.get(1).text();
        System.out.println(flyInAirport);
        // scraped string YVR - SFO
        // parse first three characters and the last three characters for airportCode
        flyIn.setDepartureAirport(flyInAirport.substring(0,3));
        System.out.print("departure airport for flying home is " + flyIn.getDepartureAirport()+"\n");
        flyIn.setArrivalAirport(flyInAirport.substring(flyInAirport.length()-3));
        System.out.print("arrival airport for flying home is " + flyIn.getArrivalAirport()+"\n");

        Elements resultFlyInDetails =
            result.select("div.detailsWrapper div.section-content div.content-card");// div.segment-row
                                                                                     // ");
        // + "div.right-column.segment-details div.planeDetails");
        flyIn.setFlightDetails(resultFlyInDetails.get(1)
            .select("div.right-column.segment-details div.planeDetails").text());
        System.out.println(flyIn.getFlightDetails());

        System.out.print(flyIn.getFlights());
        System.out.print("\n");

        // }
        itinerary.setFlyOut(flyOut);
        itinerary.setFlyIn(flyIn);
        System.out.println(itinerary.getItinerary());
        scraped.append(itinerary.getItinerary() + "\n");

      }

      System.out.println("Scraping complete.");
      scrapingFileWritten = true;
      scraped.close();
      fout.close();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void Init(String departureAirport, String arrivalAirport
      , String departureDate, String arrivalDate, String ranking, int debugMode) {
    
    //initialize flight scraping info
    //kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=bestflight_a
    this.url = baseUrl 
        + departureAirport + "-" + arrivalAirport 
        + "/" 
        + departureDate 
        + "/" 
        + arrivalDate 
        + "?sort=" 
        + ranking;

    this.debugMode = debugMode;
    
  }
    
  public void run() {
    ActiveWebHtmlLoading();
    WebHtmlScraping();
  }
  
	  /**
	 * @return the scrapingFileWritten
	 */
	public boolean isScrapingFileWritten() {
		return scrapingFileWritten;
	}  
  /*
  public static void main(String[] args) {

    ActiveFlightWebScraping newFlight = new ActiveFlightWebScraping();
    newFlight.run();

  }
  */
  
}
