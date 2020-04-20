import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class AirportCode {
	
	private HashMap<Integer, String> airportCodeData; // key=index; value=flightData Class
	String deptAirportCode;
	String destAirportCode;
	boolean deptCodeFound = false;
	boolean destCodeFound = false;
	boolean validAirports = false;
	
	public AirportCode(String filename, String deptCity, String destCity) {
		File file = new File(filename);
		airportCodeData = new HashMap<Integer, String>();
		String scannedCity;

		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); // skip the first line with the headers
			
			while (scanner.hasNextLine()) {
				
				String codesRow = scanner.nextLine();
				String[] columnData = codesRow.split(",");
				String readCity = columnData[0];
				
				if(deptCity.equalsIgnoreCase(readCity)) {
					deptAirportCode = columnData[1];
					deptCodeFound = true;
				}
				
				if(destCity.equalsIgnoreCase(readCity)) {
					destAirportCode = columnData[1];
					destCodeFound = true;
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(deptCodeFound && destCodeFound) {
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
	 * @return the validAirports
	 */
	public boolean isValidAirports() {
		return validAirports;
	}
	
}
