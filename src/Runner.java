/**The runner class essentially runs one instance of the overall program, which are broken down into the following steps:
 * 1. An instance of the GUI class is created, which retrieves the various pieces of flight data from the user
 * 2. This data is then read into the webscraping class, which scrapes the web for flight data and saves it into a csv file
 * 3. The csv file is read y the data-reader class, and post-processed using the data-processing/recommender class
 * 4. The report generator class produces a report for the user, based on the recommendations from the recommender class. 
 */

public class Runner {
	
	/**Main method runs once instance of the program*/
	public static void main(String args[]) {
		//create an instance of GUI class
		//create an instance of the web-scraping class
		//create an instance of the data-reading and data-processing classes
		//create an instance of the report generator class
	}

}
