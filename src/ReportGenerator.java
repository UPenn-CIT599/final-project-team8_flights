import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The ReportGenerator Class gets data from the Recommender Class, and generates
 * a report of the recommended flights for the user
 * 
 * @author issacwon
 *
 */
public class ReportGenerator {

	/**
	 * Generates a report with the top recommended flights based on their price For
	 * those top 5 flights, this will present top 5 cheapest flight details, the
	 * flight numbers and the booking links
	 * @param priceLimit
	 * @param layoverLimit
	 * @param directFlight
	 */
	public void generateReport(int priceLimit, int layoverLimit, boolean directFlight) {

		if (directFlight) {
			layoverLimit = 0;
		}

		File out = new File("report.txt");
		DataReader read = new DataReader();
		ArrayList<Flights> flightList = read.readCSV();
		Recommender rec = new Recommender(flightList);
		ArrayList<Flights> rankedFlightList = rec.getFlightDetails(priceLimit, layoverLimit);
		try (PrintWriter write = new PrintWriter(out)) {
			if (rankedFlightList.isEmpty()) {
				write.println(
						"Sorry, no flights meeting your criteria were found. Please adjust your parameters and try again");
				System.out.println(
						"Sorry, no flights meeting your criteria were found. Please adjust your parameters and try again");
			} else {
				write.println("Flights that meet your criteria are...");
				write.println();
				int countFlight = 1;
				for (Flights flight : rankedFlightList) {
					for (int i = 0; i < rankedFlightList.size(); i++) {
						write.println();
						write.println();
						write.println("Flight" + countFlight);
						write.println();
						write.print("Flight Detail: ");
						String[] details = flight.getFlightDetails().split("Leg ");
						for (String detail : details) {
							write.println(detail);
						}	
						write.println("Flight Number: " + "\n" + flight.getFlightNum());
						write.println("Link to Booking: " + "\n" + flight.getBookingLink());
						countFlight++;
					}
					break;
				}
				System.out.println("Your report is created. Search for 'report.txt'");

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot find the file, please check the spelling of the file");
		}
	}


}
