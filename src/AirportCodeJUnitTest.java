import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.*;;

/**
 * Class to test AirportCode class by checking if the AirportReader and Airport
 * classes reads airports.dat.txt database correctly and if AirportCode
 * searchAirport method outputs expected airport code.
 * <p>
 * This JUnit test class consists of 16 test cases:
 * <li>test 1-4 test Airport and AirportReader classes by checking airports arraylist.</li>
 * <li>test 5-9 focus on testing AirportCode and its search method by changing departure city name parameter</li>
 * <li>test 10-14 focus on testing AirportCode and its search method by changing destination city name parameter</li>
 * <li>test 15-16 focus on testing AirportCode and its search method if city name parameter is empty</li>
 * </p>
 * WARNING: import java.junit.jupiter.api.* for BeforeAll and Test, as these are
 * JUnit 5 libraries, they are not compatible with JUnit4.
 */

public class AirportCodeJUnitTest {

	static AirportReader readAirport = new AirportReader("airports.dat.txt");
	static ArrayList<Airport> airports = readAirport.getAirports();

	/**
	 * <b>Test case 1</b> checks if AirportReader and Airport classes start and run
	 * correctly by reading total number of airports reads into the arraylist
	 */
	@Test
	public void AirportReaderNumberOfAirportsTest() {

		System.out.println("test1) number of airports " + airports.size());
		assertEquals(7698, airports.size(), "Expected webscraping page title is : Book now: YVR to ARN, 9/1 — 9/30");
	}

	/**
     * <b>Test case 2</b> checks if city name in the first element of airports arraylist 
     * is read correctly 
     */
	@Test
	public void AirportReaderRow1AirportNameTest() {

		System.out.println("test2) name of airport in the first row is " + airports.get(0).getAirportName());
		assertEquals("Goroka Airport", airports.get(0).getAirportName(), "Expected airport name is : Goroka Airport");
	}

	 /**
     * <b>Test case 3</b> checks if IATA code in the first element of airports arraylist 
     * is read correctly 
     */
	@Test
	public void AirportReaderRow1AirportCodeTest() {

		System.out.println("test3) IATA code of airport in the first row is " + airports.get(0).getIATACode());
		assertEquals("GKA", airports.get(0).getIATACode(), "Expected airport code is : GKA");
	}

    /**
     * <b>Test case 4</b> checks if airport name in the last element of airports arraylist 
     * is read correctly 
     */
	@Test
	public void AirportReaderLastRowAirportNameTest() {

		System.out.println("test4) name of airport in the last row is " + airports.get(airports.size() - 1).getAirportName());
		assertEquals("Melitopol Air Base", airports.get(airports.size() - 1).getAirportName(),
				"Expected airport name is : Melitopol Air Base");
	}
	
    /**
     * <b>Test case 5</b> checks if AirportCode class and its search method returns correct airport
     * code when passing a known departure city 
     */
	@Test
	public void AirportCodeDeptStockholmTest1() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm", "Vancouver");
		System.out.println("test5) departure airport code is " + newAirport.getDeptAirportCode());
		assertEquals("BMA", newAirport.getDeptAirportCode(), "Expected airport code is : BMA");
	}
    
    /**
     * <b>Test case 6</b> checks if AirportCode class and its search method returns correct airport
     * code when passing a known departure city with known airport code
     */
	@Test
	public void AirportCodeDeptStockholmTest2() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm.arn", "Vancouver");
		System.out.println("test6) departure airport code is " + newAirport.getDeptAirportCode());
		assertEquals("ARN", newAirport.getDeptAirportCode(), "Expected airport code is : ARN");
	}
    
    /**
     * <b>Test case 7</b> checks if AirportCode class and its search method returns correct "Not Found"
     * String when passing a misspelled departure city name
     */
	@Test
	public void AirportCodeDeptStockholmTest3() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stckhm", "Vancouver");
		System.out.println("test7) departure airport code is " + newAirport.getDeptAirportCode());
		assertEquals("Not Found", newAirport.getDeptAirportCode(), "Expected airport code is : Not Found");
	}

    /**
     * <b>Test case 8</b> checks if AirportCode class and its search method returns correct boolean
     * when departure city is entered correctly
     */
	@Test
	public void AirportCodeDeptStockholmTest4() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm.arn", "Vancouver");
		System.out.println("test8) departure airport code is " + newAirport.getDeptAirportCode());
		System.out.println("is departure airport valid? " + newAirport.isValidDeptAirport());
		assertEquals(true, newAirport.isValidDeptAirport(), "Expected result is : true");
	}

    /**
     * <b>Test case 9</b> checks if AirportCode class and its search method returns correct boolean
     * when departure city is entered misspelled
     */
	@Test
	public void AirportCodeDeptStockholmTest5() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stckhm", "Vancouver");
		System.out.println("test9) departure airport code is " + newAirport.getDeptAirportCode());
		System.out.println("is departure airport valid? " + newAirport.isValidDeptAirport());
		assertEquals(false, newAirport.isValidDeptAirport(), "Expected result is : false");
	}
    
    /**
     * <b>Test case 10</b> checks if AirportCode class and its search method returns correct airport
     * code when passing a known destination city 
     */
	@Test
	public void AirportCodeDestVancouverTest1() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockhm", "Vancouver");
		System.out.println("test10) destination airport code is " + newAirport.getDestAirportCode());
		assertEquals("YVR", newAirport.getDestAirportCode(), "Expected airport code is : YVR");
	}
	
    /**
     * <b>Test case 11</b> checks if AirportCode class and its search method returns correct airport
     * code when passing a known destination city with known airport code
     */
	@Test
	public void AirportCodeDestVancouverTest2() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockhm", "Vancouver.YVR");
		System.out.println("test11) destination airport code is " + newAirport.getDestAirportCode());
		assertEquals("YVR", newAirport.getDestAirportCode(), "Expected airport code is : YVR");
	}

    /**
     * <b>Test case 12</b> checks if AirportCode class and its search method returns correct "Not Found"
     * String when passing a misspelled destination city name
     */
	@Test
	public void AirportCodeDestVancouverTest3() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockhm", "Vancvr");
		System.out.println("test12) destination airport code is " + newAirport.getDestAirportCode());
		assertEquals("Not Found", newAirport.getDestAirportCode(), "Expected airport code is : Not Found");
	}

    /**
     * <b>Test case 13</b> checks if AirportCode class and its search method returns correct boolean
     * when destination city is entered correctly
     */
	@Test
	public void AirportCodeDestVancouverTest4() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm", "Vancouver");
		System.out.println("test13) destination airport code is " + newAirport.getDestAirportCode());
		System.out.println("is destination airport valid? " + newAirport.isValidDestAirport());
		assertEquals(true, newAirport.isValidDestAirport(), "Expected result is : true");
	}

    /**
     * <b>Test case 14</b> checks if AirportCode class and its search method returns correct boolean
     * when destination city is entered misspelled
     */
	@Test
	public void AirportCodeDestVancouverTest5() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm", "Vancvr");
		System.out.println("test14) destination airport code is " + newAirport.getDestAirportCode());
		System.out.println("is destination airport valid? " + newAirport.isValidDestAirport());
		assertEquals(false, newAirport.isValidDestAirport(), "Expected result is : false");
	}

    /**
     * <b>Test case 15</b> checks if AirportCode class and its search method returns correct boolean
     * when departure city field is empty
     */
	@Test
	public void AirportCodeDeptEmptyTest() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "", "Vancouver");
		System.out.println("test15) departure airport code is " + newAirport.getDeptAirportCode());
		System.out.println("is departure airport valid? " + newAirport.isValidDeptAirport());
		assertEquals(false, newAirport.isValidDeptAirport(), "Expected result is : false");
	}

    /**
     * <b>Test case 16</b> checks if AirportCode class and its search method returns correct boolean
     * when destination city field is empty
     */
	@Test
	public void AirportCodeDestEmptyTest() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm", "");
		System.out.println("test16) destination airport code is " + newAirport.getDestAirportCode());
		System.out.println("is destination airport valid? " + newAirport.isValidDestAirport());
		assertEquals(false, newAirport.isValidDestAirport(), "Expected result is : false");
	}

}
