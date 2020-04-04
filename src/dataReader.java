import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class dataReader {
	public static ArrayList<Flight> readCSV(){
		ArrayList<Flight> flight = new ArrayList<Flight>();
		File flightData = new File ("");
		try {
			Scanner scan = new Scanner(flightData);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String [] dataLine = line.split("|");
				//parse each column in the line
				int flightPrice = Integer.parseInt(dataLine[1].substring(1)); //create a substring by removing the $ sign
				String[] layover= dataLine[7].split("\\s+"); //split the parsed form ("2 stops (LAX, TPE)")
				int numberLayover = Integer.parseInt(layover[0]);//concatenate to make "2 Stops"
				String flightDetail = dataLine[6];// "Vancouver, BC, Canada - Vancouver Intl on 4/27 at 4:30 pm, lands in Beijing, China - Capital on 4/29 at 10:55 am"
				String duration = dataLine[9];
				Flight flg = new Flight(flightPrice,numberLayover,flightDetail,duration);
				flight.add(flg);
			}
			scan.close();
		}catch(FileNotFoundException e) {
			System.out.println("Cannot open the file");
			
		}
		return flight;
	}
	
}
