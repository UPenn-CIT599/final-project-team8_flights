
/**
 * The AirportCode class maps the departure and destination cities to their three-letter airport
 * codes.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AirportCode {

  private ArrayList<Airport> totalAirports = new ArrayList<>();

  String deptAirportCode;
  String destAirportCode;
  boolean deptCodeFound = false;// set to true if can map departure city to airport code
  boolean destCodeFound = false;// set to true if can map destination city to airport code
  boolean validAirports = false;// set to true if both deptCodeFound and destCodeFound are true

  /**
   * Creates an instance of the AirportCode.
   * 
   * @param filename - filename of the file containing cities and the corresponding airport code
   * @param deptCity - name of departure city
   * @param destCity - name of destination city
   */
  public AirportCode(String filename, String deptCity, String destCity) {
    // File file = new File(filename);
    // airportCodeData = new HashMap<Integer, String>();

    AirportReader readAirport = new AirportReader("airports.dat.txt");
    // AirportReader newAirport = new AirportReader(filename);
    this.totalAirports = readAirport.getAirports();

    deptAirportCode = searchAirport(deptCity);

    if (deptAirportCode.length() == 3) {
      deptCodeFound = true;
    }

    destAirportCode = searchAirport(destCity);

    if (destAirportCode.length() == 3) {
      destCodeFound = true;
    }

    /*
     * try { Scanner scanner = new Scanner(file); scanner.nextLine(); // skip the first line with
     * the headers
     * 
     * while (scanner.hasNextLine()) {
     * 
     * //read a row of data, split data into city and airport code String codesRow =
     * scanner.nextLine(); String[] columnData = codesRow.split(","); String readCity =
     * columnData[0];
     * 
     * //store dept airport code if departure city correctly maps to it if
     * (deptCity.equalsIgnoreCase(readCity)) { deptAirportCode = columnData[1]; deptCodeFound =
     * true; }
     * 
     * //store dest airport code if destination city correctly maps to it if
     * (destCity.equalsIgnoreCase(readCity)) { destAirportCode = columnData[1]; destCodeFound =
     * true; } } scanner.close(); } catch (FileNotFoundException e) { // TODO Auto-generated catch
     * block e.printStackTrace(); }
     */

    // if we can map both departure and destination cities to airport codes, set boolean to true,
    // otherwise keep as false.
    if (deptCodeFound && destCodeFound) {
      validAirports = true;
    }
  }

  public String searchAirport(String city) {

    String airportCode = "Not Found";
    HashMap<Integer, Airport> airportList = new HashMap<>();
    // parsedCity is introduced for multiple airport within the same city
    // for example Vancouver, there are multiple airports
    // if user enters Vancouver only, the next query is looking for "International" airport
    // if user enters Vancouver.YVR, then the query is looking Vancouver and then IATA code
    String[] parsedCity = city.split("\\.");

    int size = 0;
    
    for (Airport airport : totalAirports) {
      if (airport.getCityName().equalsIgnoreCase(parsedCity[0])) {
        airportList.put(size, airport);
        size++;
      }
    }

    // if search result is empty
    // return "Not Found" string
    if (airportList.size() <= 0) {
      System.out.println(airportCode);
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
          System.out.println(airportCode);
          return airportCode;
        }
      }
      
      // checks if database IATACode column is empty or "\"
      // remove them from airportList
      else if (IATACode.equals("\"\"") | IATACode.isEmpty()) {
        airportList.remove(i);
      }
      
      // checks if airport is international airport by checking if airport name 
      // contains "International" airport
      else if (airportList.size() > 1 && !airportName.contains("International")) {
        airportList.remove(i);
      }
    }
    System.out.println("filtered airport number: " + airportList.size());
    // return the first value of airportList
    airportCode = airportList.get(airportList.keySet().toArray()[0]).getIATACode();
    System.out.println(airportCode);
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
   * @return the validAirports boolean - set to true if airport codes found for both dept and dest
   *         cities
   */
  public boolean isValidAirports() {
    return validAirports;
  }

  /*
  public static void main(String[] args) {
    AirportCode newAirport1 = new AirportCode("airports.dat.txt", "Stockholm", "Vancouver");
    AirportCode newAirport2 = new AirportCode("airports.dat.txt", "Stockholm.arn", "Vancouver");
    AirportCode newAirport3 = new AirportCode("airports.dat.txt", "test", "New York");
    
  }
  */
  
  

}
