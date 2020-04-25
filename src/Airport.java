public class Airport {

	private int airportID = 156;
	private String airportName = "Vancouver International Airport";
	private String cityName = "Vancouver";
	private String countryName = "Canada";
	private String IATACode = "YVR";

	public Airport(Integer airportID, String airportName, String cityName, String countryName, String IATACode) {

		this.airportID = airportID;
		this.airportName = airportName;
		this.cityName = cityName;
		this.countryName = countryName;
		this.IATACode = IATACode;

	}

	public int getAirportID() {
		return airportID;
	}

	public String getAirportName() {
		return airportName;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getIATACode() {
		return IATACode;
	}

}
