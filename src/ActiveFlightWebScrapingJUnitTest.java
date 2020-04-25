import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.*;;

/**
 * Class to test ActiveFlightWebScraping class by checking if the webdriver can
 * get the correct page title with a known url, scraping and parsing a known
 * flight data file, ScrapedFlightData.txt, and compare the result against the
 * known flight info.
 * <p>
 * This class consists of five test cases.
 * <li>test case 1 checks if ActiveWebScraping runs starts and run correctly by
 * reading the page title</li>
 * <li>test case 2 checks number of rows are in the scraped data file.</li>
 * <li>test case 3 checks row 1 flight price from the scraped data file</li>
 * <li>test case 4 checks row 9 flight price from the scraped data file</li>
 * <li>test case 5 checks row 2 flight departure time from the scraped data file
 * </li>
 * <li>test case 6 checks row 3 flight arrival time from the scraped data file
 * </li>
 * </p>
 * WARNING: import java.junit.jupiter.api.* for BeforeAll and Test, as these are
 * JUnit 5 libraries, this is not compatible with JUnit4
 */
class ActiveFlightWebScrapingJUnitTest {

	static ArrayList<String> scrapedData;
	static String url = "https://www.kayak.com/flights/YVR-ARN/2020-09-01/2020-09-30?sort=price_a";
	static int debugMode = 1;
	static String pageTitle = null;

	/**
	 * Instantiate ActiveFlightWebScraping with flight info and debug mode set to 1.
	 * Read and store scraped data and parsed data to ScrapedFlightData.txt for
	 * testing.
	 */
	@BeforeAll
	public static void fileGenerationForTesting() {

		scrapedData = new ArrayList<>();

		// initialize flight scraping info
		ActiveFlightWebScraping newFlight = new ActiveFlightWebScraping(url, debugMode);

		// set number of page to load (set it to 1, default is 3)
		newFlight.setPageLoad(1);
		newFlight.run();

		// waiting for sraping file to be written
		while (!newFlight.isScrapingFileWritten()) {
			System.out.println("waiting for scraping complete...");
		}
		pageTitle = newFlight.getPageTitle();

		String scrapedFileName = "ScrapedFlightData.txt";
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
	 * Test case 1 checks if ActiveWebScraping runs starts and run correctly by
	 * reading the page title
	 */
	@Test
	public void ActiveFlightWebScrapingRunTest() {

		System.out.println("page title: " + pageTitle);
		assertEquals("YVR to ARN, 9/1 — 9/30", pageTitle,
				"Expected webscraping page title is : Book now: YVR to ARN, 9/1 — 9/30");
	}

	/**
	 * Test case 2 checks if number of rows in the scraped data file
	 */
	@Test
	public void scrapedDataNumberOfRowsTest() {

		System.out.println("number of rows in scrapedData: " + scrapedData.size());
		assertEquals(45, scrapedData.size(), "Checks number of rows in scrapedDataTest.txt");
	}

	/**
	 * Test case 3 checks row 1 flight price from the scraped data file
	 */
	@Test
	public void scrapedRankOneFlightCost() {
		String rankOneFare = null;
		rankOneFare = scrapedData.get(1 - 1).split("\\|")[1].trim();
		System.out.println("rank 1 price: " + rankOneFare);
		assertEquals("$847", rankOneFare, "Checks rank one flight fare!");
	}

	/**
	 * Test case 4 checks row 9 flight price from the scraped data file
	 */
	@Test
	public void scrapedRankNineFlightCost() {
		String rankFiveFare = null;
		rankFiveFare = scrapedData.get(9 - 1).split("\\|")[1].trim();
		System.out.println("rank 9 price: " + rankFiveFare);
		assertEquals("$874", rankFiveFare, "Checks rank five flight fare!");
	}

	/**
	 * Test case 5 checks row 2 flight departure time from the scraped data file
	 */
	@Test
	public void scrapedRankTwoDeptTime() {
		String rankTwoDeptTime = null;
		rankTwoDeptTime = scrapedData.get(2 - 1).split("\\|")[5].trim();
		System.out.println("rank 2 departure time: " + rankTwoDeptTime);
		assertEquals("8:55 pm", rankTwoDeptTime, "Checks rank five flight fare!");
	}

	/**
	 * Test case 6 checks row 3 flight arrival time from the scraped data file
	 */
	@Test
	public void scrapedRankThreeArrivalTime() {
		String rankThreeArrTime = null;
		rankThreeArrTime = scrapedData.get(3 - 1).split("\\|")[6].trim();
		System.out.println("rank 3 arrival time: " + rankThreeArrTime);
		assertEquals("5:40 pm +1", rankThreeArrTime, "Checks rank five flight fare!");
	}

}
