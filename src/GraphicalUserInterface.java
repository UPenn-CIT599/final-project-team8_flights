
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Properties;

/**
 * The Graphical User Interface class sets up the GUI for user interaction. It
 * reads and stores data various pieces of flight information from user,
 * including the departure and arrival cities, the departure time and date, and
 * additional constraints such as if the user would prefer only direct flights,
 * or if not the max no of lay-overs, and the maximum budget for the entire
 * trip. This information is then passed to the web-scraping and report
 * generator classes
 */
public class GraphicalUserInterface {

	/*
	 * instance variables that will be read from the user and eventually passed to
	 * other classes
	 */
	private String deptCity; // departure city
	private String destCity; // destination city
	private String depDay, depMonth, retDay, retMonth, depYear, retYear;
	boolean directFlight = false; // initialize as false. True=only recommend direct flights
	String maxLayovers = "1"; // max allowed no of layovers
	String maxBudget; // max budget for total trip in $
	String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	boolean readyToSearch = false; // boolean to determine whether user is ready to search for flights. Set to true
									// when user clicks "go"

	// declare other instance variables pertaining to the graphical user interface
	// buttons
	JFrame frame, frame2;
	JPanel p1, p2;
	JComboBox dep1, dep2, dep3, ret1, ret2, ret3, layoverList;
	JLabel depLabel, retLabel;

	/**
	 * The createGui method creates the frames, panels, and other user-interactive
	 * components of the GUI
	 */
	void createGui() {

		frame = new JFrame();

		// create arrays of strings for date selectors
		String days31[] = { "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th",
				"13th", "14th", "15th", "16th", "17th", "18th", "19th", "20th", "21st", "22nd", "23rd", "24th", "25th",
				"26th", "27th", "28th", "29th", "30th", "31st" };
		String years[] = { "2020", "2021" };

		// grab current date - not using this info yet...
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int date = Calendar.getInstance().get(Calendar.DATE);

		// set default departure date to today's date
		depDay = days31[date - 1];
		depMonth = months[month];
		depYear = Integer.toString(year);

		// set default return date to exactly one month from now
		retDay = days31[date - 1];
		retMonth = months[month + 1];
		retYear = Integer.toString(year);

		// create drop-down lists for the departure day, month, and year fields. Set to
		// default dept date.
		dep1 = new JComboBox(days31); // departure days
		dep2 = new JComboBox(months); // departure months
		dep3 = new JComboBox(years); // departure years

		dep1.setSelectedIndex(date - 1);
		dep2.setSelectedIndex(month);
		dep3.setSelectedIndex(year - 2020);

		// create drop-down lists for the return day, month, and year fields. Set to
		// default return date.
		ret1 = new JComboBox(days31); // return days
		ret2 = new JComboBox(months); // return months
		ret3 = new JComboBox(years); // return years

		ret1.setSelectedIndex(date - 1);
		ret2.setSelectedIndex(month + 1);
		ret3.setSelectedIndex(year - 2020);

		// add ItemListeners for all date drop-down selectors
		dep1.addItemListener(null);
		dep2.addItemListener(null);
		dep3.addItemListener(null);
		ret1.addItemListener(null);
		ret2.addItemListener(null);
		ret3.addItemListener(null);

		// create labels for departure and return dates
		depLabel = new JLabel("Departure Date");
		retLabel = new JLabel("Return Date");
		JPanel depPanel = new JPanel();
		JPanel retPanel = new JPanel();

		// create panels for departure and return dates. Add respective comboboxes to
		// panels.
		depPanel.add(depLabel);
		depPanel.add(dep2);
		depPanel.add(dep1);
		depPanel.add(dep3);

		retPanel.add(retLabel);
		retPanel.add(ret2);
		retPanel.add(ret1);
		retPanel.add(ret3);

		// declaration of JTextField variables
		JTextField tfDeparture, tfDestination;

		// create search button that user clicks when ready to search for flights
		JButton searchButton = new JButton("SEARCH FOR FLIGHTS");

		// create new JPanel
		p1 = new JPanel();
		p1.setLayout(new GridLayout(5, 5));
		tfDeparture = new JTextField(20); // text-box for retrieving the departure city
		tfDestination = new JTextField(2); // text-box for retrieving the destination city
		tfDeparture.setBounds(50, 50, 150, 20);

		// add action listeners for the departure and destination cities
		tfDeparture.addActionListener(null);
		tfDestination.addActionListener(null);

		// add prompts to JPanel
		p1.add(new JLabel("Enter departure city:"));
		p1.add(tfDeparture);
		p1.add(new JLabel("Enter destination:"));
		p1.add(tfDestination);

		// create a checkbox and action listener for direct flights
		JCheckBox checkbox = new JCheckBox("Direct flights only");
		checkbox.addActionListener(null);

		// create a textfield for user to enter their max budget
		TextField tfMaxBudget = new TextField();

		// create a dropdown list of max number of layovers
		String layover[] = { "1", "2", "3" };
		layoverList = new JComboBox(layover);

		// Add remaining GUI elements to JPanel and format JFrame
		p1.add(new JLabel("Max no of layovers"));
		p1.add(layoverList);
		// p1.add(new JLabel(" "));
		p1.add(new JLabel("Maximum budget (USD$)"));
		p1.add(tfMaxBudget);
		p1.add(checkbox);
		p1.add(searchButton);

		// Set the layout parameters of the GUI window
		frame.setLayout(new BorderLayout());
		frame.setSize(332, 250);
		frame.setVisible(true); // actually displays GUI

		frame.add(depPanel, BorderLayout.NORTH);
		frame.add(retPanel, BorderLayout.EAST);

		frame.add(p1, BorderLayout.SOUTH);

		/**
		 * Action listener for text-box retrieves the text entered by the user not the
		 * user prefers direct flights only
		 * 
		 * @param a new instance of an ActionListener
		 */
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set departure/destination city per text entered by user
				deptCity = tfDeparture.getText();
				destCity = tfDestination.getText();

				// set max budget per text entered by user. If no text entered, set default to
				// 5000
				maxBudget = tfMaxBudget.getText();
				if (maxBudget.isEmpty()) {
					maxBudget = "5000";
				}

				// clean up departure/return days for returning to main. Strip the day suffix
				// ("th, st, etc.") and prefix with 0 for single digit days.
				depDay = depDay.substring(0, depDay.length() - 2);
				if (depDay.length() == 1) {
					depDay = "0" + depDay;
				}

				for (int i = 1; i <= 12; i++) {
					if (depMonth.equals(months[i - 1])) {
						depMonth = Integer.toString(i);
					}
				}

				if (depMonth.length() == 1) {
					depMonth = "0" + depMonth;
				}

				retDay = retDay.substring(0, retDay.length() - 2);
				if (retDay.length() == 1) {
					retDay = "0" + retDay;
				}

				for (int i = 1; i <= 12; i++) {
					if (retMonth.equals(months[i - 1])) {
						retMonth = Integer.toString(i);
					}
				}
				if (retMonth.length() == 1) {
					retMonth = "0" + retMonth;
				}

				// set readyToSearch boolean to true upon the user clicking the search button.
				readyToSearch = true;
			}
		});

		/**
		 * Action listener "listens" to the check-box checking whether or not the user
		 * prefers direct flights only
		 * 
		 * @param a new instance of an ActionListener
		 */
		checkbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JCheckBox cb = (JCheckBox) event.getSource();
				if (cb.isSelected()) {
					directFlight = true;
				} else {
					directFlight = false;
				}
			}
		});

		/**
		 * Action listener for retrieving the flight departure day
		 * 
		 * @param a new instance of an ActionListener
		 */
		dep1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox dep1 = (JComboBox) event.getSource();
				depDay = dep1.getSelectedItem().toString();
			}
		});

		/**
		 * Action listener for retrieving the flight departure month. Updates list of
		 * departure days based on month selected.
		 * 
		 * @param a new instance of an ActionListener
		 */
		dep2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				JComboBox dep2 = (JComboBox) event.getSource();

				// Print the selected items and the action command.
				depMonth = dep2.getSelectedItem().toString();

				// corrects drop down-menu for days available if February selected as month
				if (depMonth.equals("February")) {
					dep1.removeItem("29th");
					dep1.removeItem("30th");
					dep1.removeItem("31st");
				}

				// corrects drop down-menu for days available if a 31-day month is selected
				if (depMonth.equals("January") || depMonth.equals("March") || depMonth.equals("May")
						|| depMonth.equals("July") || depMonth.equals("August") || depMonth.equals("October")
						|| depMonth.equals("December")) {
					if (dep1.getItemCount() < 29) {
						dep1.addItem("29th");
					}
					if (dep1.getItemCount() < 30) {
						dep1.addItem("30th");
					}
					if (dep1.getItemCount() < 31) {
						dep1.addItem("31st");
					}
				}

				// corrects drop down-menu for days available if a 30-day month is selected
				if (depMonth.equals("April") || depMonth.equals("June") || depMonth.equals("September")
						|| depMonth.equals("November")) {
					if (dep1.getItemCount() < 29) {
						dep1.addItem("29th");
					}
					if (dep1.getItemCount() < 30) {
						dep1.addItem("30th");
					}
					if (dep1.getItemCount() > 30) {
						dep1.removeItem("31st");
					}
				}

			}
		});

		/**
		 * Action listener for retrieving the flight departure year.
		 * 
		 * @param a new instance of an ActionListener
		 */
		dep3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				JComboBox dep3 = (JComboBox) event.getSource();

				// Print the selected items and the action command.
				depYear = dep3.getSelectedItem().toString();
			}
		});

		/**
		 * Action listener for retrieving the flight return day.
		 * 
		 * @param a new instance of an ActionListener
		 */
		ret1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Get the source of the component, which is our combo
				// box.
				JComboBox ret1 = (JComboBox) event.getSource();

				// Print the selected items and the action command.
				retDay = ret1.getSelectedItem().toString();
			}
		});

		/**
		 * Action listener for retrieving the flight return month. Updates list of
		 * departure days based on month selected.
		 * 
		 * @param a new instance of an ActionListener
		 */
		ret2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Get the source of the component, which is our combo
				// box.
				JComboBox ret2 = (JComboBox) event.getSource();

				// Print the selected items and the action command.
				retMonth = ret2.getSelectedItem().toString();

				// corrects drop down-menu for days available if February selected as month
				if (retMonth.equals("February")) {
					ret1.removeItem("29th");
					ret1.removeItem("30th");
					ret1.removeItem("31st");
				}

				// corrects drop down-menu for days available if a 31-day month is selected
				if (retMonth.equals("January") || retMonth.equals("March") || retMonth.equals("May")
						|| retMonth.equals("July") || retMonth.equals("August") || retMonth.equals("October")
						|| retMonth.equals("December")) {
					if (ret1.getItemCount() < 29) {
						ret1.addItem("29th");
					}
					if (ret1.getItemCount() < 30) {
						ret1.addItem("30th");
					}
					if (ret1.getItemCount() < 31) {
						ret1.addItem("31st");
					}
				}

				// corrects drop down-menu for days available if a 30-day month is selected
				if (retMonth.equals("April") || retMonth.equals("June") || retMonth.equals("September")
						|| retMonth.equals("November")) {
					if (ret1.getItemCount() < 29) {
						ret1.addItem("29th");
					}
					if (ret1.getItemCount() < 30) {
						ret1.addItem("30th");
					}
					if (ret1.getItemCount() > 30) {
						ret1.removeItem("31st");
					}
				}

			}
		});

		/**
		 * Action listener for retrieving the flight reurn year.
		 * 
		 * @param a new instance of an ActionListener
		 */
		ret3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				JComboBox ret3 = (JComboBox) event.getSource();

				// Print the selected items and the action command.
				retYear = ret3.getSelectedItem().toString();

				// adjusts the days available for February depending on if the year is a
				// leap-year
				if (Integer.parseInt(retYear) % 4 == 0 && retMonth.equals("February")) {
					if (ret1.getItemAt(29) == null) {
						ret1.addItem("29th");
					}
				}
				if (retYear.equals("2021") && retMonth.contentEquals("February")) {
					ret1.removeItem("29th");
				}

			}
		});

		/**
		 * Action listener for retrieving the maximum specified number of layovers.
		 * 
		 * @param a new instance of an ActionListener
		 */
		layoverList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox layoverList = (JComboBox) event.getSource();
				maxLayovers = layoverList.getSelectedItem().toString();
			}
		});

	}

	/**
	 * @return the deptCity
	 */
	public String getDeptCity() {
		return deptCity;
	}

	/**
	 * @return the destCity
	 */
	public String getDestCity() {
		return destCity;
	}

	/**
	 * @return the directFlight
	 */
	public boolean isDirectFlight() {
		return directFlight;
	}

	/**
	 * @return the maxLayovers
	 */
	public String getMaxLayovers() {
		return maxLayovers;
	}

	/**
	 * @return the maxBudget
	 */
	public String getMaxBudget() {
		return maxBudget;
	}

	/**
	 * @return the depDay
	 */
	public String getDepDay() {
		return depDay;
	}

	/**
	 * @param depDay the depDay to set
	 */
	public void setDepDay(String depDay) {
		this.depDay = depDay;
	}

	/**
	 * @return the depMonth
	 */
	public String getDepMonth() {
		return depMonth;
	}

	/**
	 * @param depMonth the depMonth to set
	 */
	public void setDepMonth(String depMonth) {
		this.depMonth = depMonth;
	}

	/**
	 * @return the retDay
	 */
	public String getRetDay() {
		return retDay;
	}

	/**
	 * @param retDay the retDay to set
	 */
	public void setRetDay(String retDay) {
		this.retDay = retDay;
	}

	/**
	 * @return the retMonth
	 */
	public String getRetMonth() {

		return retMonth;
	}

	/**
	 * @param retMonth the retMonth to set
	 */
	public void setRetMonth(String retMonth) {
		this.retMonth = retMonth;
	}

	/**
	 * @return the depYear
	 */
	public String getDepYear() {
		return depYear;
	}

	/**
	 * @param depYear the depYear to set
	 */
	public void setDepYear(String depYear) {
		this.depYear = depYear;
	}

	/**
	 * @return the retYear
	 */
	public String getRetYear() {
		return retYear;
	}

	/**
	 * @param retYear the retYear to set
	 */
	public void setRetYear(String retYear) {
		this.retYear = retYear;
	}

	/**
	 * @param deptCity the deptCity to set
	 */
	public void setDeptCity(String deptCity) {
		this.deptCity = deptCity;
	}

	/**
	 * @param destCity the destCity to set
	 */
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	/**
	 * @param directFlight the directFlight to set
	 */
	public void setDirectFlight(boolean directFlight) {
		this.directFlight = directFlight;
	}

	/**
	 * @return the readyToSearch boolean. True if search button has been selected by
	 *         user.
	 */
	public boolean isReadyToSearch() {
		return readyToSearch;
	}

	/**
	 * @param readyToSearch the readyToSearch to set
	 */
	public void setReadyToSearch(boolean readyToSearch) {
		this.readyToSearch = readyToSearch;
	}

}
