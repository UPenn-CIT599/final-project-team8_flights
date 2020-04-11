import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.*;;


/**
 * Test FlightWebScraping class's two main htmlScraping and htmlParsing methods;
 * call FlightWebScrapingRunner class, set debug mode to run both methods and 
 * generates the scraped and parsed .txt files for testing. 
 * 
 * First test checks htmlScraping method's top rank and rank 5 flight fare.
 * Second test checks htmlParsing method's lowest rank flight fare.
 * 
 * WARNING: import java.junit.jupiter.api.* for BeforeAll and Test, 
 * as these are JUnit 5 libraries, this is not compatible with JUnit4
 */
class FlightWebScrapingJUnitTest {

  static ArrayList<String> scrapedData;
  static ArrayList<String> parsedData;

  /**
   * Instantiate FlightWebScraping with flight info and set debug mode.
   * Read and store scraped data and parsed data as arrayList for testing.
   */
  @BeforeAll
  public static void fileGenerationForTesting() {
    scrapedData = new ArrayList<>();
    parsedData = new ArrayList<>();
    
    String baseUrl = "https://www.kayak.com/flights/";
    String departureAirport = "YVR";
    String arrivalAirport = "PEK";
    String departureDate = "2020-04-27";
    String arrivalDate = "2020-05-27";
    String ranking = "price_a";
    
    //initialize flight scraping info
    FlightWebScrapingRunner newFlight = new FlightWebScrapingRunner(baseUrl + ","
        + departureAirport + "," + arrivalAirport + "," + departureDate + ","
        + arrivalDate + "," + ranking);
    
    //set debug mode to 0 for scraping from a known flight html file
    newFlight.setDebugMode(0);
    newFlight.run();

    //set debug mode to 1 for scraping from a known flight html file
    newFlight.setDebugMode(1);
    newFlight.run();
    
    //set debug mode to 2 for parsing from a known scraped data file
    newFlight.setDebugMode(2);
    newFlight.run();
    
    String scrapedFileName = "scrapedDataTest.txt";
    String parsedFileName = "parsedDataTest.txt";
    
    File inputFile;
    
    inputFile = new File(scrapedFileName);

    // Read in and store scraped data to arrayList for testing
    try( Scanner in = new Scanner(inputFile) ){

        while(in.hasNextLine()) {
          scrapedData.add(in.nextLine());
        }
        in.close();
        
    } catch (IOException e ) {
        e.printStackTrace();
        System.out.println("Scraped File Read Fail!!!");
    }    
    
    inputFile = new File(parsedFileName);

    // Read in and store parsed data to arrayList for testing
    try( Scanner in = new Scanner(inputFile) ){

        while(in.hasNextLine()) {
          parsedData.add(in.nextLine());
        }
        in.close();
        
    } catch (IOException e ) {
        e.printStackTrace();
        System.out.println("Parsed File Read Fail!!!");
    }    
  }

  /**
   * 
   */
  public void FlightWebScrapingTestRun() {
    
  }
  
  /**
   * check if number of rows in the scraped data file is 14
   */
  @Test
  public void scrapedDataNumberOfRowsTest() {
    
    System.out.println("number of rows in scrapedData: " + scrapedData.size());
    assertEquals(14, scrapedData.size(), "Checks number of rows in scrapedDataTest.txt");
  }

  /**
   * check rank 1 flight price from the scraped data file is $1041
   */
  @Test
  public void scrapedRankOneFlightCost() {
    String rankOneFare = null;
    rankOneFare = scrapedData.get(1-1).split(":")[1].trim().split(" ")[0];
    System.out.println("rank 1: " + rankOneFare);
    assertEquals("$1041", rankOneFare, "Checks rank one flight fare!");
  }
  /**
   * check rank 5 flight price from the scraped data file is $1513
   */
  @Test
  public void scrapedRankFiveFlightCost() {
    String rankFiveFare = null;
    rankFiveFare = scrapedData.get(5-1).split(":")[1].trim().split(" ")[0];
    System.out.println("rank 5: " + rankFiveFare);
    assertEquals("$1513", rankFiveFare, "Checks rank five flight fare!");
  }


  /**
   * check if number of rows in the parsed data file is 15
   */
  @Test
  public void parsedDataNumberOfRowsTest() {
    
    System.out.println("number of rows in parsedData: " + parsedData.size());
    assertEquals(15, parsedData.size(), "Checks number of rows in parsedDataTest.txt");
  }

  /**
   * check lowest flight price from the parsed data file is $630
   */
  @Test
  public void parsedLowestRankFlightCost() {
    
    String lowestRankFare = null;
    
    lowestRankFare = parsedData.get(parsedData.size()-1).split("\\|")[1].trim();
    System.out.println("rank lowest: " + lowestRankFare);
    assertEquals("$1630", lowestRankFare, "Checks lowest rank fare!");
  }

}
