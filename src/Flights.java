import java.security.Policy.Parameters;

/**
 * Represents the all data points scrapped from Kayak.com 
 * 
 * @author issacwon
 *
 */
public class Flights {

	private String carrier = null;
	private String departureTime = null;
	private String arrivalTime = null;
	private String stops = null;
	private String duration = null;
	private String departureAirport = null;
	private String arrivalAirport = null;
	private String flightDetails = null;
	private int flightPrice;
	private int numberLayover;
	private String flightNum;
	private String bookingLink;

	/**
	 * Represents one line from the csv file (once scrapped) that is passed on to
	 * dataReader class
	 * 
	 * @param price
	 * @param numLayover
	 * @param flightDet
	 * @param flyNum
	 * @param link
	 */

	public Flights(int price, int numLayover, String flightDet, String flyNum, String link) {
		this.flightPrice = price;
		this.numberLayover = numLayover;
		this.flightDetails = flightDet;
		this.bookingLink = link;
		this.flightNum = flyNum;

	}
	
	public Flights() {
		
	}

	public int getFlightPrice() {
		return flightPrice;
	}

	public void setFlightPrice(int flightPrice) {
		this.flightPrice = flightPrice;
	}

	public int getNumberLayover() {
		return numberLayover;
	}

	public void setNumberLayover(int numberLayover) {
		this.numberLayover = numberLayover;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getBookingLink() {
		return bookingLink;
	}

	public void setBookingLink(String bookingLink) {
		this.bookingLink = bookingLink;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getStops() {
		return stops;
	}

	public void setStops(String stops) {
		this.stops = stops;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public String getFlightDetails() {
		return flightDetails;
	}

	public void setFlightDetails(String flightDetails) {
		this.flightDetails = flightDetails;
	}

	public String getFlights() {

		String flightInfo = null;
		flightInfo = carrier + ("|") + flightDetails + ("|") + departureTime + ("|") + arrivalTime + ("|") + stops
				+ ("|") + duration + ("|") + departureAirport + ("|") + arrivalAirport;
		return flightInfo;

	}
}
