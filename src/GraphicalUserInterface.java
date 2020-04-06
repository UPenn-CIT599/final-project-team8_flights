/**The Graphical User Interface class sets up the GUI for user interaction. 
 * It reads and stores data various pieces of flight information from user, 
 * including the departure and arrival cities, the departure time and date,
 * and additional constraints such as if the user would prefer only direct
 * flights, or if not the max no of lay-overs, and the maximum budget for the
 * entire trip.
 * This information is then passed to the */

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.util.Properties;


public class GraphicalUserInterface {
	
	/*instance variables that will be read from the user and eventually passed 
	 * to other classes
	 */
	private String deptCity; //departure city
	private String destCity; //destination city
	private String deptTime; //departure time
	private String deptdate; //departure date, in format MMDDYYYY
	boolean directFlight = false; //initialize as false. True=only recommend direct flights
	int maxLayovers; //max allowed no of layovers
	int maxBudget; //max budget for total trip in $
	
	//declare other instance variables pertaining to the GUI
	JFrame frame;
	JPanel p1;

	
	/**main driver code for testing the Graphical User Interface Class.
	 * This will eventually be run by the Runner Class*/
	public static void main(String args[]) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.createGui();
	}
	
	
	
	/**The createGui method creates the frames, panels, and other user-interactive components of the GUI*/
	void createGui() {
		
		/**Design milestone notes for TA: createGui method currently sets up a frame to retrieve the departure and destination cities, 
		 * and if the user prefers direct flights only
		 * 
		 * TO-DO:
		 * Add buttons for date selector (likely calendar-type format)
		 * Add fields to retrieve departure times
		 * Add field for max no. of layovers
		 * Add action listeners for other various buttons
		 */
		 
		
		//declaration of JTextField variables
		JTextField tfDeparture, tfDestination, tfResult;
		
		//create new instances of JFrame, JButton, JPanel, and JTextFields
		frame = new JFrame();
		JButton but1 = new JButton("GET ME FLIGHTS");
		p1 = new JPanel();
		p1.setLayout(new GridLayout(5,5));
	    tfDeparture =new JTextField(20); //text-box for retrieving the departure city
	    tfDestination = new JTextField(2); //text-box for retrieving the destination city
	    tfDeparture.setBounds(50,50,150,20); 
	    
	    //add action listeners for the departure and destination cities
	    tfDeparture.addActionListener(null);
	    tfDestination.addActionListener(null); 
	    
	    //add features to JPanel
	    p1.add(new JLabel("Enter departure city:"));
		p1.add(tfDeparture);
	    p1.add(new JLabel("Enter destination:"));
	    p1.add(tfDestination);
	    JCheckBox checkbox = new JCheckBox("Direct flights only");
	    p1.add(checkbox);
		p1.add(but1);
		
		checkbox.addActionListener(null);
		
	    final TextField tf=new TextField();  
	    tf.setBounds(50,50, 150,20);
	    p1.add(tf);
	    
		
	    /**Action listener for textbox retrives the text entered by the user
	     * not the user prefers direct flights only
	     */
		but1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){
		        deptCity = tfDeparture.getText();  
		        destCity = tfDestination.getText();
		        if(directFlight) {
	            tf.setText("Getting direct flights from " + deptCity + " to " + destCity);  
		        } else {
		            tf.setText("Getting flights from " + deptCity + " to " + destCity); 
		        }        
	    }
		});

		
	    /**Action listener "listens" to the checkbox checking whether or 
	     * not the user prefers direct flights only
	     */		
		checkbox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JCheckBox cb = (JCheckBox) event.getSource();
		        if (cb.isSelected()) {
		        	directFlight = true;
		            System.out.println("Checkbox selected!");
			    	p1.add(new JLabel("Searching for direct flights only"));
		        } else {
		            // check box is unselected, do something else
		        }
		    }
		});
		
		//Set the layout parameters of the GUI window
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 400);
		frame.setVisible(true); //actually displays GUI
		frame.add(p1, BorderLayout.SOUTH);
	}
	
	/**Determines if the inputs from the user are valid or not. 
	 * Checks the instance variables of the GraphicalUserInterface class
	 * If not valid, prints message to user
	 */
	boolean isValidInput() {
		//code to check validity of inputs goes here
		return true; //tentatively return true
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
	 * @return the deptTime
	 */
	public String getDeptTime() {
		return deptTime;
	}



	/**
	 * @return the deptdate
	 */
	public String getDeptdate() {
		return deptdate;
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
	public int getMaxLayovers() {
		return maxLayovers;
	}



	/**
	 * @return the maxBudget
	 */
	public int getMaxBudget() {
		return maxBudget;
	}
	
	
}






