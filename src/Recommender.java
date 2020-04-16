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
	ArrayList<Integer> numOfLayover = new ArrayList<Integer>();
	
	public Recommender(ArrayList<Flight> flightList) {
		this.flightList = flightList;
	}
	/**
	 * Return a HashMap that filters out overbudget flights 
	 * and flights with more layover than preferred
	 * @param priceLimit
	 * @param layoverLimit
	 * @return ArrayList of Flightlist
	 */
	public ArrayList<Flight> getFlightDetails(int priceLimit, int layoverLimit) {
		int topCnt = 5;//fixed number of flights details (MVP restriction)
		ArrayList<Flight> tmpFlightList = new ArrayList<Flight>();
		for (Flight f : this.flightList) {
			if(tmpFlightList.size() >= topCnt) {
				break;
			}
			if (f.getFlightPrice() <= priceLimit) {
				// Ensure that the flight does not have more layover than given number
				if (f.getNumberLayover() <= layoverLimit) {
					tmpFlightList.add(f);
					numOfLayover.add(f.getNumberLayover());
				}

			}

		}
		return tmpFlightList;
	}
//	public ArrayList<String> getFlightNum(){
//		dataReader read = new dataReader ();
//		ArrayList<Flight> flightList = read.readCSV();
//		Recommender rec = new Recommender (flightList);
//		ArrayList<Flight> rankedFlightList = rec.getFlightDetails(gui.maxBudget,gui.maxLayovers);
//	}

}
