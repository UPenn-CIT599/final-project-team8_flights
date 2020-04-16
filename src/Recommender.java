import java.util.ArrayList;
import java.util.HashMap;
/**
 * call the flight class and return a hashmap with the flight info
 * that meet the condition parameters set by the users from GUI class
 * @author issacwon
 *
 */
public class Recommender {
	ArrayList<Flight> flightList = new ArrayList<Flight>();
	GraphicalUserInterface gui = new GraphicalUserInterface(); // create a class instance to get the budget

	public Recommender(ArrayList<Flight> flightList) {
		this.flightList = flightList;
	}
	/**
	 * Return a HashMap that filters out overbudget flights 
	 * and flights with more layover than preferred
	 * @param priceLimit
	 * @param layoverLimit
	 * @param topCnt
	 * @return
	 */
	public ArrayList<Flight> getRankedFlights(int priceLimit, int layoverLimit) {
		int topCnt = 5;
		ArrayList<Flight> tmpFlightList = new ArrayList<Flight>();
		ArrayList<Integer> numOfLayover = new ArrayList<Integer>();//TODO add flight number
		for (Flight f : this.flightList) {
			if(tmpFlightList.size() >= topCnt) {
				break;
			}
			if (f.getFlightPrice() <= priceLimit) {
				// Ensure that the flight does not have more layover than given number
				if (f.getNumberLayover() <= layoverLimit) {
					tmpFlightList.add(f);
				}

			}

		}
		return tmpFlightList;
	}

}
