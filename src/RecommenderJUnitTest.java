import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.*;;

/**
 * Class to test ReportGenerator class by checking if its input parameters are covered including
 * priceLimit, layoverLimit, and directFlight
 * <p>
 * This JUnit test class consists of 3 test cases:
 * <li>test 1 test Airport and AirportReader classes by checking airports arraylist.</li>
 * <li>test 2 focus on testing AirportCode and its search method by changing departure city name parameter</li>
 * <li>test 3 focus on testing AirportCode and its search method by changing destination city name parameter</li>
 * <li>test 4 focus on testing AirportCode and its search method if city name parameter is empty</li>
 * </p>
 * <p>
 * <b>NOTE:</b> Run this test after ActiveFlightWebScrapingJUnitTest as the checking are based on a known 
 * JUnitKayakFlightinfo.html file which generates a known ScrapedFlightData.txt file.
 * </p>
 * <p>
 * <b>WARNING:</b> import java.junit.jupiter.api.* for BeforeAll and Test, as these are
 * JUnit 5 libraries, they are not compatible with JUnit4.
 * </p>
 */

public class ReportGeneratorJUnitTest {
    
    static ReportGenerator report = new ReportGenerator();
    static ArrayList<String> reportData = new ArrayList();


    @BeforeAll
    public static void fileDeletionForTesting() {

      System.out.println("JUnit Test for ReportGenerator class starts here.");
      
      String reportFileName = "report.txt";
      
      File file = new File(reportFileName); 
      
      if(file.delete()) 
      { 
          System.out.println("File deleted successfully"); 
      } 
      else
      { 
          System.out.println("File is already deleted"); 
      }
    }
    
    /**
     * <b>Test case 1</b> checks if ActiveWebScraping runs starts and run correctly by reading the page title
     */
    @Test
    public void ReportGenMaxBudgetTest() {
      
      report.generateReport(840, 5, false);      
      System.out.println("test1) page title: " + "test");
      assertEquals("YVR to ARN, 9/1 — 9/30", "test",
          "Expected webscraping page title is : Book now: YVR to ARN, 9/1 — 9/30");
    }

}
