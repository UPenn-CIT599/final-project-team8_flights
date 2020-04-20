import java.util.concurrent.TimeUnit;

/**The runner class essentially runs one instance of the overall program, which are broken down into the following steps:
 * 1. An instance of the GUI class is created, which retrieves the various pieces of flight data from the user
 * 2. This data is then read into the webscraping class, which scrapes the web for flight data and saves it into a csv file
 * 3. The csv file is read by the data-reader class, and post-processed using the data-processing/recommender class
 * 4. The report generator class produces a report for the user, based on the recommendations from the recommender class. 
 */

public class Runner {
	
	/**Main method runs once instance of the program*/
	public static void main(String args[]) {
		
//1. GUI
		
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.createGui();
		
		int guiDone = 0;
		while(guiDone==0) {
			
		    try {

		        TimeUnit.SECONDS.sleep(1);

		      } catch (InterruptedException ie) {
		        Thread.currentThread().interrupt();
		      }
			
			//System.out.println("Status of Gui: " + gui.isReadyToSearch() + "\n");
			if(gui.isReadyToSearch()) {
				//System.out.println("Gui is ready to search \n");
				break;
			}
		}
		

//2. AIRPORT CODE CLASS
		
		AirportCode airportCode = new AirportCode("airportCodeList.txt", gui.getDeptCity(), gui.getDestCity());
		
		if(!airportCode.isValidAirports()) {
			System.out.println("Please enter valid cities. Try running the program again\n");
			System.exit(0);
		} else {		
		String deptAirportCode = airportCode.getDeptAirportCode();
		String destAirportCode = airportCode.getDestAirportCode();
		
		System.out.println("Dep airport code = " + deptAirportCode);
		System.out.println("Dest airport code = " + destAirportCode);
		
		
		
//3. GUI to WEB-SCRAPING		
		
		System.out.println("Do Chris's stuff now!!!");

		//String retDay = gui.getRetDay();
		//System.out.println("Return day to return = \n" + retDay);
		
	    String departureDate = gui.getDepYear() + "-" + gui.getDepMonth() + "-" + gui.getDepDay();
	    System.out.println("Departure date is: " + departureDate +"\n");
	    String returnDate = gui.getRetYear() + "-" + gui.getRetMonth() + "-" + gui.getRetDay();
	    System.out.println("Return date is: " + returnDate + "\n");

		ActiveFlightWebScraping activeWebScrape = new ActiveFlightWebScraping(deptAirportCode, destAirportCode, departureDate, returnDate);
		
		
		activeWebScrape.run();
		
		int i = 0;
		while(i==0) {
			
		    try {

		        TimeUnit.SECONDS.sleep(1);

		      } catch (InterruptedException ie) {
		        Thread.currentThread().interrupt();
		      }
			
			System.out.println("file not written yet \n");
			if(activeWebScrape.isScrapingFileWritten()) {
				System.out.println("file has been written!! \n");
				break;
			}
		}
		
		
//4. WEB-SCRAPING TO REPORT
		
		ReportGenerator report = new ReportGenerator();
	
			int maxBudget = Integer.parseInt(gui.getMaxBudget());
			System.out.println("Budget equal to" + maxBudget + "\n");


		int maxLayovers = Integer.parseInt(gui.getMaxLayovers());
		boolean directFlight = gui.isDirectFlight();
		
		report.generateReport(maxBudget,maxLayovers,directFlight);
		
		
		}

	}

}
