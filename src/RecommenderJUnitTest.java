import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.*;;

/**
 * Class to test Recommender class by checking if its input parameters are covered including
 * priceLimit and layoverLimit
 * <p>
 * This JUnit test class consists of 3 test cases:
 * <li>test 1-2 test Recommender classes by checking max budget search criteria.</li>
 * <li>test 3-5 test Recommender classes by checking max layover search criteria.</li>
 * <p>
 * <b>NOTE:</b> Run this test after ActiveFlightWebScrapingJUnitTest as the checking are based on a known 
 * JUnitKayakFlightinfo.html file which generates a known ScrapedFlightData.txt file.
 * </p>
 * <p>
 * <b>WARNING:</b> import java.junit.jupiter.api.* for BeforeAll and Test, as these are
 * JUnit 5 libraries, they are not compatible with JUnit4.
 * </p>
 */

public class RecommenderJUnitTest {
    
    private static Recommender rec;

    @BeforeAll
    public static void fileDeletionForTesting() {

      System.out.println("JUnit Test for Recommender class starts here.");
      
      DataReader read = new DataReader();
      ArrayList<Flights> flightList = read.readCSV();
      rec = new Recommender(flightList);

    }
    
    /**
     * <b>Test case 1</b> checks if Recommender class returns correct flight search with max budget parameter
     */
    @Test
    public void ReportGenMaxBudgetTest1() {
      rec.getFlightDetails(880, 2);
      System.out.println("test1) number of flights matching max budget search criteria: " + rec.getFlightListSize());
      assertEquals(2, rec.getFlightListSize(),
          "Expected number of flights : 2");
    }
    
    /**
     * <b>Test case 2</b> checks if Recommender class returns correct flight search if no match with max budget parameter
     */
    @Test
    public void ReportGenMaxBudgetTest2() {
      rec.getFlightDetails(885, 2);
      System.out.println("test2) number of flights matching max budget search criteria: " + rec.getFlightListSize());
      assertEquals(5, rec.getFlightListSize(),
          "Expected number of flights : 0");
    }

    /**
     * <b>Test case 3</b> checks if Recommender class returns correct flight search if no match with max budget parameter
     */
    @Test
    public void ReportGenMaxBudgetTest3() {
      rec.getFlightDetails(600, 2);
      System.out.println("test3) number of flights matching max budget search criteria: " + rec.getFlightListSize());
      assertEquals(0, rec.getFlightListSize(),
          "Expected number of flights : 0");
    }

    /**
     * <b>Test case 4</b> checks if Recommender class returns correct flight search with max layover parameter
     */
    @Test
    public void ReportGenMaxLayouverTest1() {
      rec.getFlightDetails(2000, 3);
      System.out.println("test4) number of flights matching max layover search criteria: " + rec.getFlightListSize());
      assertEquals(5, rec.getFlightListSize(),
          "Expected number of flights : 5");
    }
    
    /**
     * <b>Test case 5</b> checks if Recommender class returns correct flight search with max layover parameter
     */
    @Test
    public void ReportGenMaxLayoverTest2() {
      rec.getFlightDetails(2000, 2);
      System.out.println("test5) number of flights matching max layover search criteria: " + rec.getFlightListSize());
      assertEquals(5, rec.getFlightListSize(),
          "Expected number of flights : 5");
    }

    /**
     * <b>Test case 6</b> checks if Recommender class returns correct flight search with max layover parameter
     */
    @Test
    public void ReportGenMaxLayoverTest3() {
      rec.getFlightDetails(2000, 0);
      System.out.println("test6) number of flights matching max layover search criteria: " + rec.getFlightListSize());
      assertEquals(0, rec.getFlightListSize(),
          "Expected number of flights : 0");
    }

}
