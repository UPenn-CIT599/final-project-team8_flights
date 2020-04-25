import java.util.*;
import java.io.*;

public class AirportReader {

	/*
	 * data processing columns 00 airportID - String //OpenFlights database unique
	 * ID 01 airportName - String 02 cityName - String 03 countryName - String 04
	 * IATACode - String 05 ICAOCode- String 06 latitude - Double 07 longtitude -
	 * Double 08 altitude - Integer // in feet 09 timezone - Double // hours offset
	 * from UTC 10 DST - String //daylight saving time 11 tzTimezone - String //eg
	 * America/Los_Angeles 12 type - String //airport;station;port;unknown 13 source
	 * - String //source of this data
	 */

	private ArrayList<Airport> readAirportCsv = new ArrayList<>();
	private int airportIDIndex = 0;
	private int airportNameIndex = 1;
	private int cityNameIndex = 2;
	private int countryNameIndex = 3;
	private int IATACodeIndex = 4;

	public AirportReader(String fileName) {

		int row = 0;
		File f = new File(fileName);
		try {
			Scanner scan = new Scanner(f);
			// scan.nextLine();// skips file header
			while (scan.hasNextLine()) {
				// System.out.println("processing..." + row);
				String[] columns = scan.nextLine().split(",");
				Integer airportID = integerToString(columns[airportIDIndex]);
				String airportName = columns[airportNameIndex].replace("\"", "");
				String cityName = "";
				if (!columns[cityNameIndex].equals("\"\"")) {
					cityName = columns[cityNameIndex].replace("\"", "");
				}
				String countryName = columns[countryNameIndex].replace("\"", "");
				String IATACode = "";
				if (!columns[IATACodeIndex].equalsIgnoreCase("\\n")) {
					IATACode = columns[IATACodeIndex].replace("\"", "");
				}

				Airport airport = new Airport(airportID, airportName, cityName, countryName, IATACode);

				readAirportCsv.add(airport);
				row++;
			}
			scan.close();

		} catch (FileNotFoundException e) {

			System.out.println("Warning! File not found.");
			e.printStackTrace();

		}
	}

	/**
	 * checks if String of integer is empty before running Integer.parseInt class;
	 * return -1 if empty, otherwise return integer by running Integer.parseInt
	 * 
	 * @param integer in String
	 * @return integer of String integer
	 */
	public int integerToString(String integer) {
		if (integer.isEmpty())
			return -1;
		else
			return Integer.parseInt(integer);
	}

	public ArrayList<Airport> getAirports() {
		return readAirportCsv;
	}
}
