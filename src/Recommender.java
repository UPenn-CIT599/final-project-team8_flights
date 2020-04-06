import java.util.ArrayList;
import java.util.HashMap;
/*
 * call the flight class and return a hashmap with the flight info
 * that meet the condition parameters set by the users from GUI class
 */
public class Recommender {
	ArrayList<Flight> flight = new ArrayList<Flight>();
	GraphicalUserInterface gui = new GraphicalUserInterface(); // create a class instance to get the budget

	public Recommender(ArrayList<Flight> flight) {
		this.flight = flight;
	}
	/*
	 * Return a HashMap that filters out overbudget flights 
	 * and flights with more layover than preferred
	 */
	public HashMap<Integer, Flight> rankedFlights() {
		HashMap<Integer, Flight> flightRank = new HashMap<Integer, Flight>();
		int count = 1;
		double budget = gui.getmaxBudget;
		int layover = gui.getmaxLayovers;
		for (Flight i : this.flight) {
			// Ensure that the price of the flight is below the budget given by the user
			if (i.getFlightPrice() < budget) {
				// Ensure that the flight does not have more layover than given number
				if (i.getNumberLayover() < layover) {
					flightRank.put(count, i);
					count++;
				}

			}

		}
		return flightRank;
	}
}
