import java.util.ArrayList;
import java.util.HashMap;
/**
 * call the flights class and return a ArrayList with the flights info
 * 
 * @author issacwon
 *
 */
public class Recommender {
	ArrayList<Flights> flightList = new ArrayList<Flights>();
	
	
	public Recommender(ArrayList<Flights> flightList) {
		this.flightList = flightList;
	}
	/**
	 * Return a HashMap that filters out overbudget flights 
	 * and flights with more layover than preferred
	 * The list from the webscrapper is already ranked by the flight price
	 * 
	 * @param priceLimit
	 * @param layoverLimit
	 * @return ArrayList tmpFlightList
	 */
	public ArrayList<Flights> getFlightDetails(int priceLimit, int layoverLimit) {
		int topCnt = 5;//fixed number of flights details (MVP restriction)
		ArrayList<Flights> tmpFlightList = new ArrayList<Flights>();
		for (Flights f : this.flightList) {
			if(tmpFlightList.size() >= topCnt) {
				break;
			}
			if (f.getFlightPrice() <= priceLimit) {
				// Ensures that the flight does not have more layover than given number
				if (f.getNumberLayover() <= layoverLimit) {
					tmpFlightList.add(f);
					
				}

			}

		}
		return tmpFlightList;
	}

}
