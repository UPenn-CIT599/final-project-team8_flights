import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.*;;


/**
 * Class to test ActiveFlightWebScraping class by checking if the webdriver can get the correct page title
 * with a known url, scraping and parsing a known flight data file, ScrapedFlightData.txt, 
 * and compare the result against the known flight info.
 * <p>
 * This class consists of five test cases.
 * <p>
 * First test checks generated scraped file's top rank and rank 5 flight fare. Second test checks
 * generated scraped file's lowest rank flight fare.
 * </p>
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

    String scrapedFileName = "JUnitScrapedFlightData.txt";
    File inputFile;
    
    System.out.println("JUnit Test for ActiveFlightWebScraping class starts here.");
    inputFile = new File(scrapedFileName);

    // Read in and store scraped data to arrayList for testing
    try (Scanner in = new Scanner(inputFile)) {

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
   * check if ActiveWebScraping runs starts and run correctly by reading the page title
   */
  @Test
  public void ActiveFlightWebScrapingRunTest() {
    // initialize flight scraping info
    ActiveFlightWebScraping newFlight = new ActiveFlightWebScraping(url, 1);

    // set number of page to load (default is 3)
    newFlight.setPageLoad(1);
    newFlight.run();
    
    // waiting for sraping file to be written
    while(!newFlight.isScrapingFileWritten()) {
      System.out.println("waiting for scraping complete...");
    }
    
    System.out.println("page title: " + newFlight.getPageTitle());
    assertEquals("YVR to ARN, 9/1 — 9/30", newFlight.getPageTitle(), 
        "Expected webscraping page title is : Book now: YVR to ARN, 9/1 — 9/30");
  }

  /**
   * check if number of rows in the scraped data file is 45
   */
  @Test
  public void scrapedDataNumberOfRowsTest() {

    System.out.println("number of rows in scrapedData: " + scrapedData.size());
    assertEquals(45, scrapedData.size(), "Checks number of rows in scrapedDataTest.txt");
  }

  /**
   * check rank 1 flight price from the scraped data file is $847
   */
  @Test
  public void scrapedRankOneFlightCost() {
    String rankOneFare = null;
    rankOneFare = scrapedData.get(1-1).split("\\|")[1].trim();
    System.out.println("rank 1 price: " + rankOneFare);
    assertEquals("$847", rankOneFare, "Checks rank one flight fare!");
  }

  /**
   * check rank 9 flight price from the scraped data file is $874
   */
  @Test
  public void scrapedRankFiveFlightCost() {
    String rankFiveFare = null;
    rankFiveFare = scrapedData.get(9-1).split("\\|")[1].trim();
    System.out.println("rank 9 price: " + rankFiveFare);
    assertEquals("$874", rankFiveFare, "Checks rank five flight fare!");
  }
  
  /**
   * check rank 2 flight departure time from the scraped data file is 8:55 pm
   */
  @Test
  public void scrapedRankTwoDeptTime() {
    String rankTwoDeptTime = null;
    rankTwoDeptTime = scrapedData.get(2-1).split("\\|")[5].trim();
    System.out.println("rank 2 departure time: " + rankTwoDeptTime);
    assertEquals("8:55 pm", rankTwoDeptTime, "Checks rank five flight fare!");
  }
  
  /**
   * check rank 3 flight arrival time from the scraped data file is $874
   */
  @Test
  public void scrapedRankThreeArrivalTime() {
    String rankThreeArrTime = null;
    rankThreeArrTime = scrapedData.get(3-1).split("\\|")[6].trim();
    System.out.println("rank 3 arrival time: " + rankThreeArrTime);
    assertEquals("5:40 pm +1", rankThreeArrTime, "Checks rank five flight fare!");
  }


}
