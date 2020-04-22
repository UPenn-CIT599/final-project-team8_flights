
/**The AirportCode class maps the departure and destination cities 
 * to their three-letter airport codes. 
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class AirportCode {

	String deptAirportCode;
	String destAirportCode;
	boolean deptCodeFound = false;//set to true if can map departure city to airport code
	boolean destCodeFound = false;//set to true if can map destination city to airport code
	boolean validAirports = false;//set to true if both deptCodeFound and destCodeFound are true

	/**Creates an instance of the AirportCode. 
	 * @param filename - filename of the file containing cities and the corresponding airport code
	 * @param deptCity - name of departure city
	 * @param destCity - name of destination city
	 */
	public AirportCode(String filename, String deptCity, String destCity) {
		File file = new File(filename);
		//airportCodeData = new HashMap<Integer, String>();

		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); // skip the first line with the headers

			while (scanner.hasNextLine()) {
				
				//read a row of data, split data into city and airport code
				String codesRow = scanner.nextLine();
				String[] columnData = codesRow.split(",");
				String readCity = columnData[0];

				//store dept airport code if departure city correctly maps to it
				if (deptCity.equalsIgnoreCase(readCity)) {
					deptAirportCode = columnData[1];
					deptCodeFound = true;
				}

				//store dest airport code if destination city correctly maps to it				
				if (destCity.equalsIgnoreCase(readCity)) {
					destAirportCode = columnData[1];
					destCodeFound = true;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if we can map both departure and destination cities to airport codes, set boolean to true, otherwise keep as false.
		if (deptCodeFound && destCodeFound) {
			validAirports = true;
		}
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
	 * @return the validAirports boolean - set to true if airport codes found for both dept and dest cities
	 */
	public boolean isValidAirports() {
		return validAirports;
	}

}
