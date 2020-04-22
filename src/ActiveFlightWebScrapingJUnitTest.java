import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.*;;


/**
 * Class to test ActiveFlightWebScraping class scraped flight data file, ScrapedFlightData.txt.
 * <p>
 * 
 * <p>
 * First test checks generated scraped file's top rank and rank 5 flight fare. Second test checks
 * generated scraped file's lowest rank flight fare.
 * 
 * WARNING: import java.junit.jupiter.api.* for BeforeAll and Test, as these are JUnit 5 libraries,
 * this is not compatible with JUnit4
 */
class ActiveFlightWebScrapingJUnitTest {

  static ArrayList<String> scrapedData;
  static String url = "https://www.kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=price_a";
  //static String pageTitle = "YVR to ARN, 9/1 — 9/30";

  /**
   * Instantiate FlightWebScraping with flight info and set debug mode. Read and store scraped data
   * and parsed data as arrayList for testing.
   */
  @BeforeAll
  public static void fileGenerationForTesting() {
    scrapedData = new ArrayList<>();

    // initialize flight scraping info
    ActiveFlightWebScraping newFlight = new ActiveFlightWebScraping(url);

    // set debug mode to 1 for scraping from a known flight html file
    newFlight.setDebugMode(0);
    newFlight.run();
    System.out.println("page title: " + newFlight.getPageTitle());

    String scrapedFileName = "JUnitScrapedFlightData.txt";

    File inputFile;

    inputFile = new File(scrapedFileName);

    // Read in and store scraped data to arrayList for testing
    try (Scanner in = new Scanner(inputFile)) {

      //add page tile as the first line
      scrapedData.add(newFlight.getPageTitle());
      while (in.hasNextLine()) {
        scrapedData.add(in.nextLine());
      }
      in.close();

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Scraped File Read Fail!!!");
    }
  }

  /**
   * check if ActiveWebScraping runs correctly by reading the page title
   */
  @Test
  public void ActiveFlightWebScrapingRunTest() {
    
    System.out.println("Webscraping page title is : " + scrapedData.get(0));
    assertEquals("Book now: YVR to ARN, 9/1 — 9/30", scrapedData.get(0), 
        "Expected webscraping page title is : Book now: YVR to ARN, 9/1 — 9/30");
  }

  /**
   * check if number of rows in the scraped data file is 1 + 45
   */
  @Test
  public void scrapedDataNumberOfRowsTest() {

    System.out.println("number of rows in scrapedData: " + scrapedData.size());
    assertEquals(46, scrapedData.size(), "Checks number of rows in scrapedDataTest.txt");
  }

  /**
   * check rank 1 flight price from the scraped data file is $847
   */
  @Test
  public void scrapedRankOneFlightCost() {
    String rankOneFare = null;
    rankOneFare = scrapedData.get(1).split("\\|")[1].trim();
    System.out.println("rank 1 price: " + rankOneFare);
    assertEquals("$847", rankOneFare, "Checks rank one flight fare!");
  }

  /**
   * check rank 9 flight price from the scraped data file is $874
   */
  @Test
  public void scrapedRankFiveFlightCost() {
    String rankFiveFare = null;
    rankFiveFare = scrapedData.get(5).split("\\|")[1].trim();
    System.out.println("rank 9 price: " + rankFiveFare);
    assertEquals("$871", rankFiveFare, "Checks rank five flight fare!");
  }
}
