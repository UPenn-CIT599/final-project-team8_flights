import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Parse the CSV file to pass the data to Flight class row by row
 * @author issacwon
 *
 */
public class dataReader {
	/**
	 * Read the given CSV file generated by FlightWebScraping class
	 * and parse the data and convert to the appropriate variable format 
	 * @return ArrayList flight
	 */
	public ArrayList<Flights> readCSV(){
		ArrayList<Flights> flightList = new ArrayList<Flights>();
		File flightData = new File ("newFlight_scraped.txt"); //ScrapedFlightData
		try {
			Scanner scan = new Scanner(flightData);
			//scan.nextLine();//skip the first line
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] dataLine = line.split("\\|");
				int flightPrice = Integer.parseInt(dataLine[1].replace("$","").trim());//2
				String[] layover = dataLine[7].split(" ");
				int numberLayover = Integer.parseInt(layover[0]);
				String flightDetail = dataLine[20];
				String flightLink = dataLine[19];
				String flyNum = dataLine[4]; //TODO update the data location based on Chris's output
				Flights flight = new Flights(flightPrice,numberLayover,flightDetail,flyNum,flightLink);
				flightList.add(flight);
				
			}
			scan.close();
		}catch(FileNotFoundException e) {
			System.out.println("Cannot open the file");
			
		}
		return flightList;
	}
	
}
