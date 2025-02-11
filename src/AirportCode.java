import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class to map the departure and destination cities to their three-letter airport
 * codes. It consists one main searchAirport method to screen for international airports
 * and return the corresponding airport code
 * 
 * @author cit591 Spring2020 team-8
 */
public class AirportCode {

	private ArrayList<Airport> totalAirports = new ArrayList<>();

	String deptAirportCode = "";
	String destAirportCode = "";
	boolean deptCodeFound = false;// set to true if can map departure city to airport code
	boolean destCodeFound = false;// set to true if can map destination city to airport code
	boolean validAirports = false;// set to true if both deptCodeFound and destCodeFound are true

	/**
	 * Creates an instance of the AirportCode.
	 * 
	 * @param filename - filename of the file containing cities and the
	 *                 corresponding airport code
	 * @param deptCity - name of departure city
	 * @param destCity - name of destination city
	 */
	public AirportCode(String filename, String deptCity, String destCity) {
		AirportReader readAirport = new AirportReader(filename);
		//AirportReader newAirport = new AirportReader(filename);
		this.totalAirports = readAirport.getAirports();

		if (!deptCity.isEmpty()) {
			deptAirportCode = searchAirport(deptCity);
		}

		if (deptAirportCode.length() == 3) {
			deptCodeFound = true;
		}

		if (!destCity.isEmpty()) {
			destAirportCode = searchAirport(destCity);
		}

		if (destAirportCode.length() == 3) {
			destCodeFound = true;
		}

		// if we can map both departure and destination cities to airport codes, set
		// boolean to true,
		// otherwise keep as false.
		if (deptCodeFound && destCodeFound) {
			validAirports = true;
		}
	}

	/**
	 * searchAirport method first creates an HashMap to map all airports with the same 
	 * city name from an instance of airportReader arraylist. and from the Hashmap, 
	 * it screens for international airports, strips down the other airports,
	 * and returns airport code
	 * 
	 * @param city - city name to search for airport
	 * @return airportCode - IATA code representing the airport
	 */
	public String searchAirport(String city) {

		String airportCode = "Not Found";
		HashMap<Integer, Airport> airportList = new HashMap<>();
		// parsedCity is introduced for multiple airport within the same city
		// for example Vancouver, there are multiple airports
		// if user enters Vancouver only, the next query is looking for "International"
		// airport
		// if user enters Vancouver.YVR, then the query is looking Vancouver and then
		// IATA code
		String[] parsedCity = city.split("\\.");

		int size = 0;

		for (Airport airport : totalAirports) {
			if (airport.getCityName().equalsIgnoreCase(parsedCity[0])) {
				if (!airport.getIATACode().isEmpty()) {
					airportList.put(size, airport);
					size++;
				}
			}
		}

		// if search result is empty
		// return "Not Found" string
		if (airportList.size() <= 0) {
			return airportCode;
		}

		// go over searched airportList
		for (int i = 0; i < size; i++) {
			String airportName = airportList.get(i).getAirportName();
			String IATACode = airportList.get(i).getIATACode();

			// checks if user included IATA code in city
			if ((parsedCity.length) > 1) {
				if (airportList.get(i).getIATACode().equalsIgnoreCase(parsedCity[1])) {
					airportCode = airportList.get(i).getIATACode();
					return airportCode;
				}
			}

			// checks if database IATACode column is empty or "\"
			// remove them from airportList
			else if (airportList.size() > 1 && (IATACode.equals("\"\"") | IATACode.isEmpty())) {
				airportList.remove(i);
			}

			// checks if airport is international airport by checking if airport name
			// contains "International" airport
			else if (airportList.size() > 1 && !airportName.contains("International")) {
				airportList.remove(i);
			}
		}
		// return the first value of airportList
		airportCode = airportList.get(airportList.keySet().toArray()[0]).getIATACode();
		return airportCode;
	}

	/**
	 * @return the deptAirportCode
	 */
	public String getDeptAirportCode() {
		return deptAirportCode;
	}

	/**
	 * @param deptAirportCode the deptAirportCode to set
	 */
	public void setDeptAirportCode(String deptAirportCode) {
		this.deptAirportCode = deptAirportCode;
	}

	/**
	 * @return the destAirportCode
	 */
	public String getDestAirportCode() {
		return destAirportCode;
	}

	/**
	 * @param destAirportCode the destAirportCode to set
	 */
	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}

	/**
	 * @return the validDeptAirports boolean - set to true if airport code found for
	 *         dept city
	 */
	public boolean isValidDeptAirport() {
		return deptCodeFound;
	}

	/**
	 * @return the validDeptAirports boolean - set to true if airport code found for
	 *         dest city
	 */
	public boolean isValidDestAirport() {
		return destCodeFound;
	}

	/*
	 * public static void main(String[] args) { AirportCode newAirport1 = new
	 * AirportCode("airports.dat.txt", "Stockholm", "Vancouver"); AirportCode
	 * newAirport2 = new AirportCode("airports.dat.txt", "Stockholm.arn",
	 * "Vancouver"); AirportCode newAirport3 = new AirportCode("airports.dat.txt",
	 * "test", "New York");
	 * 
	 * }
	 */

}
