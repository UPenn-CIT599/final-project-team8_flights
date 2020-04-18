public class Flights {
  
  private String carrier = null;
  private String departureTime = null;
  private String arrivalTime = null;
  private String stops = null;
  private String duration = null;
  private String departureAirport = null;
  private String arrivalAirport = null;
  private String flightDetails = null;
  
  public Flights() {
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
    flightInfo = carrier + ("|") + flightDetails + ("|") + departureTime
        + ("|") + arrivalTime + ("|") + stops + ("|") + duration  
        + ("|") + departureAirport+ ("|") + arrivalAirport;
    return flightInfo;
    
  }
  
}
