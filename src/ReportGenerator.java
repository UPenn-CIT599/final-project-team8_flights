import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The ReportGenerator Class gets data from the Recommender Class, and generates
 * a report of the recommended flights for the user
 */
public class ReportGenerator {

	/**
	 * Generates a report with the top recommended flights based on their price
	 * For those top flights, this will present top cheapest flight details and the flight numbers
	 */
	public void generateReport(int priceLimit, int layoverLimit, boolean directFlight) {
		/*
		 * Write method that retrieves information from the data processing class and
		 * generates a text report with recommended flight details
		 * gui.getMaxBudget(),gui.getMaxLayovers()
		 */
		
		if(directFlight) {
			layoverLimit=0;
		}
		
		File out = new File("report.txt");
		dataReader read = new dataReader();
		ArrayList<Flight> flightList = read.readCSV();
		Recommender rec = new Recommender(flightList);
		ArrayList<Flight> rankedFlightList = rec.getFlightDetails(priceLimit, layoverLimit);

		try (PrintWriter write = new PrintWriter(out)) {
			write.println("Top 5 cheapest flights");
			write.println();
			write.println("Flight details: ");
			for (Flight flight : rankedFlightList) {
				write.println(flight.getFlightDetail());
			}
			write.println();
			write.println("Flight numbers: ");
			for (Flight fly: rankedFlightList) {
				write.println(fly.getFlightNum1());
			}
			System.out.println("Your report is created. Search for 'report.txt'");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot find the file, please check the spelling of the file");
		}
	}

//	public static void main(String[] args) {
//		ReportGenerator r = new ReportGenerator();
//		r.generateReport();
//	}

	/**
	 * Draws recommended flight paths on a map of the world, and writes to an image
	 * file
	 */
//	void drawFlightPaths() {
	/*
	 * to-do: Write method that takes in the departure and destination cities for
	 * the recommended flights, and draws out flight paths overlaid on a map of the
	 * world
	 */
}
