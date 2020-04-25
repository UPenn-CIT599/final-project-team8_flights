import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
// JSoup for Web html scraping
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
// Selenium for active web loading
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class to scrape flight information from Kayak.com and stores the data in a
 * .txt file for processing.
 * <p>
 * This class consists of two main methods.
 * <li><b>ActiveWebHtmlLoading</b> method uses selenium WebDriver binding tool
 * to instantiate and control Chrome web browser to get flight information from
 * kayak.com with specific searching criteria including departure/arrival
 * airports and dates. It stores html source into a html file for scraping.</li>
 * <li><b> WebHtmlScraping</b> method loads the stored html file and scrapes
 * flight info in interest. From the scraped flight information, each itinerary
 * and flight data is stored into a .txt file for data processing class.</li>
 * </p>
 * 
 * @author cit591 Spring2020 team-8
 */
public class ActiveFlightWebScraping {

	ArrayList<Itinerary> itinerary = new ArrayList<>();

	private String url = null;
	private Document document;
	private String inputHtmlFileName = "KayakFlightInfo.html";
	private String testHtmlFileName = "JUnitKayakFlightInfo.html";
	private String scrapedFileName = "ScrapedFlightData.txt";
	private String baseUrl = "https://www.kayak.com/flights/";
	private String pageTitle = null;
	private boolean scrapingFileWritten = false;
	private int pageLoad = 2; // number of pages to load from kayak query

	// debugMode
	// mode 0 = normal operation mode
	// mode 1 = JUnit test using a known html file for testing
	private int debugMode = 0;

	/**
	 * getPageTitle method is used for JUnit testing to match page title
	 * 
	 * @return String pageTitle
	 *         <p>
	 *         return String example: "YVR to JFK, 9/23 - 10/23"
	 *         </p>
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * setPageLoad method sets number of page to load to get more flights
	 * <p>
	 * the typical flights per page is 15 flights
	 * </p>
	 */
	public void setPageLoad(int pageLoad) {
		this.pageLoad = pageLoad;
	}

	/**
	 * setDbugMode method sets class debug mode.
	 * <li>debugMode = 0 for normal mode; a newly browsed html file is saved for
	 * flight info parsing
	 * <li>debugMode = 1 for debug mode; a known test html file,
	 * JUnitKayakFlightInfo.html, is used for flight info parsing
	 * </p>
	 *
	 */
	public void setDebugMode(int debugMode) {
		this.debugMode = debugMode;
	}

	/**
	 * default constructor
	 * <p>
	 * if no parameter is included, the default flight search is from YVR to ARN
	 * with departure flight date at 2020-09-01 and return flight date at 2020-09-30
	 */
	public ActiveFlightWebScraping() {

		// String baseUrl = "https://www.kayak.com/flights/";
		String departureAirport = "YVR";
		String arrivalAirport = "ARN";
		String departureDate = "2020-09-01";
		String returnDate = "2020-09-30";
		String ranking = "price_a";

		// initialize flight scraping info
		// kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=bestflight_a
		this.url = baseUrl + departureAirport + "-" + arrivalAirport + "/" + departureDate + "/" + returnDate + "?sort="
				+ ranking;

	}

	/**
	 * this constructor gets four user flight parameters from Runner/GUI class and
	 * concatenate them to generate a complete kayak url search
	 * 
	 * @param departureAirport the departure airport
	 * @param arrivalAirport   the arrival airport
	 * @param departureDate    the departure date
	 * @param returnDate       the return date
	 */
	public ActiveFlightWebScraping(String departureAirport, String arrivalAirport, String departureDate,
			String returnDate) {

		String ranking = "price_a";

		// initialize flight scraping info
		// E.g: http://kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=bestflight_a
		this.url = baseUrl + departureAirport + "-" + arrivalAirport + "/" + departureDate + "/" + returnDate + "?sort="
				+ ranking;

	}

	/**
	 * this constructor is used for testing purpose to accept a complete kayak url
	 * search
	 * <p>
	 * url example: //
	 * http://kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=bestflight_a
	 * 
	 * @param url the url for kayak flight search
	 */
	public ActiveFlightWebScraping(String url) {
		this(url, 0);
	}

	/**
	 * this constructor is used for JUnit testing purpose to accept a complete kayak
	 * url search, and with debug mode set to 1
	 * <p>
	 * url example: //
	 * http://kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=bestflight_a
	 * <p>
	 * debugMode = 0 or 1
	 * 
	 * @param url       the url for kayak flight search
	 * @param debugMode the debug mode, 0 or 1
	 */
	public ActiveFlightWebScraping(String url, int debugMode) {

		this.url = url;
		this.debugMode = debugMode;

	}

	/**
	 * ActiveWebHtmlLoading method uses Selenium Webdriver binding tool together
	 * with chromedriver to active browse kayak flight search site.
	 * <p>
	 * This active browsing bypasses kayak pop window and provides load pages
	 * function to allow user to load more flight results.
	 * <p>
	 * The complete html source is saved to inputHtmlFileName.html for html
	 * scraping/parsing
	 */
	public void activeWebHtmlLoading() {

		ChromeOptions ops = new ChromeOptions();
		// ops.addArguments("--headless");
		ops.addArguments("--disable-notifications");
		ops.addArguments("--disable-popup-blocking");
		ChromeDriver browser = new ChromeDriver(ops);

		// browser.manage().window().setPosition(new Position(0,0));
		browser.manage().window().setSize(new Dimension(10, 10));

		System.out.println("Active web scraping starts.");
		// browser.navigate().to(baseurl);
		browser.get(url);

		// get the actual value of the title
		pageTitle = browser.getTitle();
		System.out.println(pageTitle);

		// refresh page to get rid of the pop-up
		browser.navigate().refresh();

		// load more pages for flight processing
		int page = 1;
		while (page <= pageLoad) {

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
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));

		System.out.println("page load complete.");

		FileWriter htmlFile;

		// store browser file for parsing
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
	 * webHtmlScraping method uses JSoup libraries to scrape flight info from a
	 * kayak html source file.
	 * <p>
	 * The scraped information is stored in scrapedFileName for data processing
	 */
	public void webHtmlScraping() {

		File fin;
		FileWriter fout;

		try {

			// set to a known html file if debugMode is 1
			if (debugMode == 1) {
				inputHtmlFileName = testHtmlFileName;
			}

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
				itinerary.setDetails(result.select("p").text());

				// https://www.ca.kayak.com/book/
				// flight?code=IFAiezE8PO.18wgSzIaLAlgpzrTH2pViLaYAeeTFjgE.60354.84e5c6d3c7cc5944205fba986de42c5b&h=175613a35035&sub=E-1d2f0a07811
				Elements itineraryLink = result
						.select("div.resultWrapper div.resultInner " + "div.inner-grid div.col-price "
								+ "div.Flights-Results-FlightPriceSection div.Common-Booking-MultiBookProvider a");
				itinerary.setLink("https://www.ca.kayak.com/" + itineraryLink.attr("href"));

				Elements itineraryPriceProvider = result
						.select("div.resultWrapper div.resultInner " + "div.inner-grid div.col-price "
								+ "div.Flights-Results-FlightPriceSection div.Common-Booking-MultiBookProvider a ");
				// + "> span.price");
				itinerary.setPrice(itineraryPriceProvider.get(0).text().split(" ")[0]);
				itinerary.setProvider(itineraryPriceProvider.get(0).text().split(" ")[1]);

				// fly-out (departure flight) flight info parsing
				Flights flyOut = new Flights();

				Elements resultFlyOutTime = result
						.select("li.flight.with-gutter div.container " + "div.section.times div.top > span.time-pair");
				flyOut.setDepartureTime(resultFlyOutTime.get(0).text());
				flyOut.setArrivalTime(resultFlyOutTime.get(1).text());

				flyOut.setCarrier(
						result.select("li.flight.with-gutter div.container div.section.times div.bottom").text());

				flyOut.setStops(result.select("li.flight.with-gutter div.container div.section.stops").text());

				flyOut.setDuration(result.select("li.flight.with-gutter div.container div.section.duration div.top")
						.get(0).text());

				Elements resultFlyOutAirport = result
						.select("li.flight.with-gutter div.container div.section.duration div.bottom");
				String flyOutAirport = resultFlyOutAirport.text();
				flyOut.setDepartureAirport(flyOutAirport.substring(0, 3));
				flyOut.setArrivalAirport(flyOutAirport.substring(flyOutAirport.length() - 3));

				Elements resultFlyOutDetails = result.select("div.detailsWrapper div.section-content div.content-card");
				flyOut.setFlightDetails(
						resultFlyOutDetails.get(0).select("div.right-column.segment-details div.planeDetails").text());

				Flights flyIn = new Flights();

				Elements resultflyInTime = result
						.select("li.flight div.container div.section.times div.top > span.time-pair");
				flyIn.setDepartureTime(resultflyInTime.get(2).text());
				flyIn.setArrivalTime(resultflyInTime.get(3).text());

				flyIn.setCarrier(result.select("li.flight div.container div.section.times div.bottom").get(1).text());

				flyIn.setStops(result.select("li.flight div.container div.section.stops").get(1).text());

				flyIn.setDuration(result.select("li.flight div.container div.section.duration div.top").get(1).text());

				Elements resultFlyInAirport = result.select("li.flight div.container div.section.duration div.bottom");
				String flyInAirport = resultFlyInAirport.get(1).text();
				flyIn.setDepartureAirport(flyInAirport.substring(0, 3));
				flyIn.setArrivalAirport(flyInAirport.substring(flyInAirport.length() - 3));

				Elements resultFlyInDetails = result.select("div.detailsWrapper div.section-content div.content-card");

				flyIn.setFlightDetails(
						resultFlyInDetails.get(1).select("div.right-column.segment-details div.planeDetails").text());

				itinerary.setFlyOut(flyOut);
				itinerary.setFlyIn(flyIn);
				// System.out.println(itinerary.getItinerary());
				scraped.append(itinerary.getItinerary() + "\n");
				fout.append(itinerary.getItinerary() + "\n");

			}

			System.out.println("Scraping complete.");
			scrapingFileWritten = true;
			scraped.close();
			fout.close();

		} catch (IOException e) {
			System.out.println("file IO exception! double check your inputHtmlFile source.");
			e.printStackTrace();
		}

	}

	/**
	 * This method re-initializes class instance variables for testing only
	 * 
	 * @param departureAirport the departure airport
	 * @param arrivalAirport   the arrival airport
	 * @param departureDate    the departure date
	 * @param arrivalDate      the arrival date
	 * @param ranking          the flight search ranking
	 * @param debugMode        the debug mode, 0 or 1
	 */
	public void init(String departureAirport, String arrivalAirport, String departureDate, String arrivalDate,
			String ranking, int debugMode) {

		// initialize flight scraping info
		// kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=bestflight_a
		this.url = baseUrl + departureAirport + "-" + arrivalAirport + "/" + departureDate + "/" + arrivalDate
				+ "?sort=" + ranking;

		this.debugMode = debugMode;

	}

	/**
	 * this method calls activeWebHtmlLoading and webHtmlScraping methods.
	 * <p>
	 * call this run method to execute the complete ActiveFlightWebScraping class.
	 */
	public void run() {
		activeWebHtmlLoading();
		webHtmlScraping();
	}

	/**
	 * this method is used to flag when the scrapedFileName file is written
	 * complete.
	 * <p>
	 * use this flag to determine when ActiveFlightWebScraping class completes and
	 * the generated file can be accessed.
	 * 
	 * @return the scrapingFileWritten
	 */
	public boolean isScrapingFileWritten() {
		return scrapingFileWritten;
	}

}
