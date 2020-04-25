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
 * This JUnit test class consists of five test cases:
 * <li>First test checks first row's airport name.</li>
 * <li>Second test checks first row's airport IATA Code.</li>
 * <li>Third test checks</li>
 * <li>Forth test checks</li>
 * <li>Fifty test checks</li>
 * </p>
 * WARNING: import java.junit.jupiter.api.* for BeforeAll and Test, as these are
 * JUnit 5 libraries, they are not compatible with JUnit4.
 */

public class AirportCodeJUnitTest {

	static AirportReader readAirport = new AirportReader("airports.dat.txt");
	static ArrayList<Airport> airports = readAirport.getAirports();

//  @BeforeAll

	/**
	 * Test case 1 checks if AirportReader and Airport classes start and run
	 * correctly by reading number of airports
	 */
	@Test
	public void AirportReaderNumberOfAirportsTest() {

		System.out.println("number of airports " + airports.size());
		assertEquals(7698, airports.size(), "Expected webscraping page title is : Book now: YVR to ARN, 9/1 — 9/30");
	}

	@Test
	public void AirportReaderRow1AirportNameTest() {

		System.out.println("name of airport in the first row is " + airports.get(0).getAirportName());
		assertEquals("Goroka Airport", airports.get(0).getAirportName(), "Expected airport name is : Goroka Airport");
	}

	@Test
	public void AirportReaderRow1AirportCodeTest() {

		System.out.println("IATA code of airport in the first row is " + airports.get(0).getIATACode());
		assertEquals("GKA", airports.get(0).getIATACode(), "Expected airport code is : GKA");
	}

	@Test
	public void AirportReaderLastRowAirportNameTest() {

		System.out.println("name of airport in the last row is " + airports.get(airports.size() - 1).getAirportName());
		assertEquals("Melitopol Air Base", airports.get(airports.size() - 1).getAirportName(),
				"Expected airport name is : Melitopol Air Base");
	}

	@Test
	public void AirportCodeDeptStockholmTest1() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm", "Vancouver");
		System.out.println("departure airport code is " + newAirport.getDeptAirportCode());
		assertEquals("BMA", newAirport.getDeptAirportCode(), "Expected airport code is : BMA");
	}

	@Test
	public void AirportCodeDeptStockholmTest2() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm.arn", "Vancouver");
		System.out.println("departure airport code is " + newAirport.getDeptAirportCode());
		assertEquals("ARN", newAirport.getDeptAirportCode(), "Expected airport code is : ARN");
	}

	@Test
	public void AirportCodeDeptStockholmTest3() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stckhm", "Vancouver");
		System.out.println("departure airport code is " + newAirport.getDeptAirportCode());
		assertEquals("Melitopol Air Base", newAirport.getDeptAirportCode(), "Expected airport code is : Not Found");
	}

	@Test
	public void AirportCodeDeptStockholmTest4() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm.arn", "Vancouver");
		System.out.println("departure airport code is " + newAirport.getDeptAirportCode());
		System.out.println("is departure airport valid? " + newAirport.isValidDeptAirport());
		assertEquals(true, newAirport.isValidDeptAirport(), "Expected result is : true");
	}

	@Test
	public void AirportCodeDeptStockholmTest5() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stckhm", "Vancouver");
		System.out.println("departure airport code is " + newAirport.getDeptAirportCode());
		System.out.println("is departure airport valid? " + newAirport.isValidDeptAirport());
		assertEquals(false, newAirport.isValidDeptAirport(), "Expected result is : false");
	}

	@Test
	public void AirportCodeDestVancouverTest1() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockhm", "Vancouver");
		System.out.println("destination airport code is " + newAirport.getDestAirportCode());
		assertEquals("YVR", newAirport.getDestAirportCode(), "Expected airport code is : YVR");
	}

	@Test
	public void AirportCodeDestVancouverTest2() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockhm", "Vancouver.YVR");
		System.out.println("destination airport code is " + newAirport.getDestAirportCode());
		assertEquals("YVR", newAirport.getDestAirportCode(), "Expected airport code is : YVR");
	}

	@Test
	public void AirportCodeDestVancouverTest3() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockhm", "Vancvr");
		System.out.println("destination airport code is " + newAirport.getDestAirportCode());
		assertEquals("Not Found", newAirport.getDestAirportCode(), "Expected airport code is : Not Found");
	}

	@Test
	public void AirportCodeDestVancouverTest4() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm", "Vancouver");
		System.out.println("destination airport code is " + newAirport.getDestAirportCode());
		System.out.println("is destination airport valid? " + newAirport.isValidDestAirport());
		assertEquals(true, newAirport.isValidDestAirport(), "Expected result is : true");
	}

	@Test
	public void AirportCodeDestVancouverTest5() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm", "Vancvr");
		System.out.println("destination airport code is " + newAirport.getDestAirportCode());
		System.out.println("is destination airport valid? " + newAirport.isValidDestAirport());
		assertEquals(false, newAirport.isValidDestAirport(), "Expected result is : false");
	}

	@Test
	public void AirportCodeDeptEmptyTest() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "", "Vancouver");
		System.out.println("departure airport code is " + newAirport.getDeptAirportCode());
		System.out.println("is departure airport valid? " + newAirport.isValidDeptAirport());
		assertEquals(false, newAirport.isValidDeptAirport(), "Expected result is : false");
	}

	@Test
	public void AirportCodeDestEmptyTest() {

		AirportCode newAirport = new AirportCode("airports.dat.txt", "Stockholm", "");
		System.out.println("destination airport code is " + newAirport.getDestAirportCode());
		System.out.println("is destination airport valid? " + newAirport.isValidDestAirport());
		assertEquals(false, newAirport.isValidDestAirport(), "Expected result is : false");
	}

}
