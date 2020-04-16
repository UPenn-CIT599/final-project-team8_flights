import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**The ReportGenerator Class gets data from the Recommender Class, and generates a report 
 * of the recommended flights for the user
 */
public class ReportGenerator {
	
	/**
	 * Generates a report with the top recommended flights and their booking details,
	 * and writes to a txt file. 
	 */
	public void generateReport() {
		/*
		 * Write method that retrieves information from the data processing class 
		 * and generates a text report with recommended flight details
		 */
		GraphicalUserInterface gui = new GraphicalUserInterface();
		File out = new File("report.txt");
		dataReader read = new dataReader ();
		ArrayList<Flight> flightList = read.readCSV();
		Recommender rec = new Recommender (flightList);
		ArrayList<Flight> rankedFlightList = rec.getRankedFlights(gui.getMaxBudget(),gui.getMaxLayovers());//int priceLimit, int layoverLimit, int topCnt
		//for (int i = 0; i<10 ; i++) {
		try(PrintWriter write = new PrintWriter(out) ) {
			for (Flight flight : rankedFlightList) {
				write.println(flight.getFlightDetail());
			}
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot find the file, please check the spelling of the file");
		}
		
		
		
	}
	
	/**Draws recommended flight paths on a map of the world, and writes to an image file*/
	void drawFlightPaths() {
		/*to-do:
		 * Write method that takes in the departure and destination
		 * cities for the recommended flights, and draws out flight
		 * paths overlaid on a map of the world
		 */
	}
	
}
