/**
 * represents each flight status that is 1 row from the csv file read in dataReader class
 * @author issacwon
 *
 */
public class Flight {
	private int flightPrice;
	private int numberLayover;
	private String flightDetail;
	private String flightNum;

	/**
	 * Represents one line from the csv file that is passed on to dataReader class
	 * @param price
	 * @param numLayover
	 * @param flightDet
	 * @param dur
	 * @param flyNum1
	 * @param flyNum2
	 * @param flyNum3
	 * @param flyNum4
	 */
	public Flight(int price, int numLayover, String flightDet, String flyNum) {
		this.flightPrice = price;
		this.numberLayover = numLayover;
		this.flightDetail = flightDet;
	
		this.flightNum = flyNum;

	}

	public String getFlightNum1() {
		return flightNum;
	}

	public void setFlightNum1(String flightNum) {
		this.flightNum = flightNum;
	}
	/*
	 * getter for flight price
	 */
	public int getFlightPrice() {
		return flightPrice;
	}

	/*
	 * setter for flight price
	 */
	public void setFlightPrice(int flightPrice) {
		this.flightPrice = flightPrice;
	}

	/*
	 * getter for number of layovers
	 */
	public int getNumberLayover() {
		return numberLayover;
	}

	/*
	 * setter for number of layovers
	 */
	public void setNumberLayover(int numberLayover) {
		this.numberLayover = numberLayover;
	}

	/*
	 * getter for flight detail
	 */
	public String getFlightDetail() {
		return flightDetail;
	}

	/*
	 * setter for flight detail
	 */
	public void setFlightDetail(String flightDetail) {
		this.flightDetail = flightDetail;
	}
	
}
