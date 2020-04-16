/**
 * represents each flight status that is 1 row from the csv file read in dataReader class
 * @author issacwon
 *
 */
public class Flight {
	private int flightPrice;
	private int numberLayover;
	private String flightDetail;
	private String duration;
	private String flightNum1;
	private String flightNum2;
	private String flightNum3;
	private String flightNum4;
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
	public Flight(int price, int numLayover, String flightDet, String dur, String flyNum1, String flyNum2,String flyNum3, String flyNum4) {
		this.flightPrice = price;
		this.numberLayover = numLayover;
		this.flightDetail = flightDet;
		this.duration = dur;
		this.flightNum1 = flyNum1;
		this.flightNum2 = flyNum2;
		this.flightNum3 = flyNum3;
		this.flightNum3 = flyNum4;
	}

	public String getFlightNum1() {
		return flightNum1;
	}

	public void setFlightNum1(String flightNum1) {
		this.flightNum1 = flightNum1;
	}

	public String getFlightNum2() {
		return flightNum2;
	}

	public void setFlightNum2(String flightNum2) {
		this.flightNum2 = flightNum2;
	}

	public String getFlightNum3() {
		return flightNum3;
	}

	public void setFlightNum3(String flightNum3) {
		this.flightNum3 = flightNum3;
	}
	
	public String getFlightNum4() {
		return flightNum4;
	}

	public void setFlightNum4(String flightNum4) {
		this.flightNum4 = flightNum4;
	}

	/*
	 * getter for duration
	 */
	public String getDuration() {
		return duration;
	}
	/*
	 * setter for duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
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
