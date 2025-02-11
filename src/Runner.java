import java.util.concurrent.TimeUnit;

/**
 * The runner class essentially runs one instance of the overall program, which
 * are broken down into the following steps: 1. An instance of the GUI class is
 * created, which retrieves the various pieces of flight data from the user 2.
 * This data is then read into the webscraping class, which scrapes the web for
 * flight data and saves it into a csv file 3. The csv file is read by the
 * data-reader class, and post-processed using the data-processing/recommender
 * class 4. The report generator class produces a report for the user, based on
 * the recommendations from the recommender class.
 */

public class Runner {

	/**
	 * Main method runs once instance of the program, creating a GUI for the user to
	 * input flight parameters, scraping the web for flights meeting user's
	 * criteria, and generating a report
	 */
	public static void main(String args[]) {

		// Create new instance of GUI class
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.createGui();

		// opens up GUI window and runs while loop until user clicks search button
		int guiDone = 0;
		while (guiDone == 0) {

			try {

				TimeUnit.SECONDS.sleep(1);

			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}

			if (gui.isReadyToSearch()) {
				break;
			}
		}

		// create new instance of AirportCode class
		AirportCode airportCode = new AirportCode("airports.dat.txt", gui.getDeptCity(), gui.getDestCity());

		// inform user through terminal and exit program if user inputs an invalid
		// destination/departure city name,
		// or a max-budget value other than a positive integer. Otherwise, get airport
		// codes
		if (!airportCode.isValidDeptAirport()) {
			System.out.println(
					"Airport code could not be found for departure city. Please check the city name and/or spelling and try running the program again\n");
		}
		if (!airportCode.isValidDestAirport()) {
			System.out.println(
					"Airport code could not be found for destination city. Please check the city name and/or spelling and try running the program again\n");
		}
		if (!airportCode.isValidDeptAirport() || !airportCode.isValidDestAirport()) {
			System.exit(0);
		} else {
			try {
				int intMaxBudget = Integer.parseInt(gui.getMaxBudget());
				if (intMaxBudget <= 0) {
					System.out.println(
							"Maximum budget field requires a positive integer. Please run program again and check this field");
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.println(
						"Maximum budget field requires a positive integer. Please run program again and check this field");
				System.exit(0);
			}

			System.out.println("Starting web-scraper!\n");
			String deptAirportCode = airportCode.getDeptAirportCode();
			String destAirportCode = airportCode.getDestAirportCode();

			// get departure and return dates from GUI class, and pass these into the
			// constructor for a new instance of ActiveFlightWebScraping class
			String departureDate = gui.getDepYear() + "-" + gui.getDepMonth() + "-" + gui.getDepDay();
			String returnDate = gui.getRetYear() + "-" + gui.getRetMonth() + "-" + gui.getRetDay();

			ActiveFlightWebScraping activeWebScrape = new ActiveFlightWebScraping(deptAirportCode, destAirportCode,
					departureDate, returnDate);
			activeWebScrape.run();

			// run while loop until the web scraping is complete and the webscraping data
			// file has been written
			int i = 0;
			while (i == 0) {

				try {

					TimeUnit.SECONDS.sleep(1);

				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}

				if (activeWebScrape.isScrapingFileWritten()) {
					System.out.println("File has been written!! \n");
					break;
				}
			}

			// Create instance of the ReportGenerator Class, and generate flight
			// recommendation report
			ReportGenerator report = new ReportGenerator();

			int maxBudget = Integer.parseInt(gui.getMaxBudget());

			int maxLayovers = Integer.parseInt(gui.getMaxLayovers());
			boolean directFlight = gui.isDirectFlight();

			report.generateReport(maxBudget, maxLayovers, directFlight);

		}

		// exit gui once completed
		System.exit(0);

	}

}
