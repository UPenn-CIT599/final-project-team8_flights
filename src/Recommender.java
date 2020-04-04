import java.util.ArrayList;
import java.util.HashMap;

public class Recommender {
	ArrayList<Flight> flight = new ArrayList<Flight>();
	Gui guiClass = new Gui(); // create a class instance to get the budget

	public Recommender(ArrayList<Flight> flight) {
		this.flight = flight;
	}
	/*
	 * Return a HashMap that filters out overbudget flights and flights with more layover than preferred
	 */
	public HashMap<Integer, String> rankedFlights() {
		HashMap<Integer, String> flightRank = new HashMap<Integer, String>();
		int count = 1;
		double budget = guiClass.getBudget;
		int layover = guiClass.getBudget;
		for (Flight i : this.flight) {
			// Ensure that the price of the flight is below the budget given by the user
			if (i.getFlightPrice() < budget) {
				// Ensure that the flight does not have more layover than given number
				if (i.getNumberLayover() < layover) {
					String fightDetNPrice = i.getFlightDetail() + i.getFlightPrice();
					flightRank.put(count, fightDetNPrice);
					count++;
				}

			}

		}
		return flightRank;
	}
}
