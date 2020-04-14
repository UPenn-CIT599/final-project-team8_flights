/**The Graphical User Interface class sets up the GUI for user interaction. 
 * It reads and stores data various pieces of flight information from user, 
 * including the departure and arrival cities, the departure time and date,
 * and additional constraints such as if the user would prefer only direct
 * flights, or if not the max no of lay-overs, and the maximum budget for the
 * entire trip.
 * This information is then passed to the web-scraping class*/

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Properties;


public class GraphicalUserInterface {
	
	/*instance variables that will be read from the user and eventually passed 
	 * to other classes
	 */
	private String deptCity; //departure city
	private String destCity; //destination city
	private String deptTime; //departure time
	private String deptdate; //departure date, in format MMDDYYYY
    private String depDay, depMonth, depYear, retDay, retMonth, retYear;
	boolean directFlight = false; //initialize as false. True=only recommend direct flights
	int maxLayovers; //max allowed no of layovers
	int maxBudget; //max budget for total trip in $
	
	//declare other instance variables pertaining to the GUI
	JFrame frame, frame2;
	JPanel p1;
    JComboBox dep1, dep2, dep3, ret1, ret2, ret3; 
    JLabel depLabel, retLabel;
    


	
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
		 
		frame = new JFrame();
		
		

		
		//grab current date - not using this info yet...
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);        
        int date = Calendar.getInstance().get(Calendar.DATE);
        System.out.println("Current year is " + year);
        System.out.println("Current month is " + month);
        System.out.println("Current date is " + date);
  
        //array of strings for dates
        String s1[] = {"1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th","13th","14th","15th","16th","17th","18th","19th",
        		"20th","21st","22nd","23rd","24th","25th","26th","27th","28th","29th","30th","31st"};
        String days28[] = {"1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th","13th","14th","15th","16th","17th","18th","19th",
        		"20th","21st","22nd","23rd","24th","25th","26th","27th","28th","29th","30th","31st"};
        String days30[] = {"1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th","13th","14th","15th","16th","17th","18th","19th",
        		"20th","21st","22nd","23rd","24th","25th","26th","27th","28th","29th","30th","31st"};
        String days31[] = {"1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th","13th","14th","15th","16th","17th","18th","19th",
        		"20th","21st","22nd","23rd","24th","25th","26th","27th","28th","29th","30th","31st"};
        
        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
  
        String years[] = {"2020","2021"};
        
        
        // create checkbox 
        dep1 = new JComboBox(days31); 
        dep2 = new JComboBox(months);
        dep3 = new JComboBox(years);
        
        ret1 = new JComboBox(days31); 
        ret2 = new JComboBox(months);
        ret3 = new JComboBox(years);
  
        // add ItemListener 
        dep1.addItemListener(null); 
        dep2.addItemListener(null);
        dep3.addItemListener(null);
        
        ret1.addItemListener(null); 
        ret2.addItemListener(null);
        ret3.addItemListener(null);        
  
        // create labels 
        depLabel = new JLabel("Departure Date"); 
        retLabel = new JLabel("Return Date");
        //l1 = new JLabel("Jalpaiguri selected"); 
  
        // set color of text 
        //l.setForeground(Color.red); 
        //l1.setForeground(Color.blue); 
  
        // create a new panel 
        JPanel depPanel = new JPanel();
        JPanel retPanel = new JPanel();
  
        depPanel.add(depLabel); 
  
        // add combo-box to panel 
        depPanel.add(dep2); 
        depPanel.add(dep1);
        depPanel.add(dep3);
  
        //p1.add(l1); 
        
        retPanel.add(retLabel);
        retPanel.add(ret2);
        retPanel.add(ret1);
        retPanel.add(ret3);
  
        // add panel to frame 
        //frame.add(depPanel);
       // frame.add(retPanel);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//declaration of JTextField variables
		JTextField tfDeparture, tfDestination, tfResult;
		
		//create new instances of JFrame, JButton, JPanel, and JTextFields
		//frame = new JFrame();
		//frame2 = new JFrame();
		
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
		
		dep1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo
                // box.
                JComboBox dep1 = (JComboBox) event.getSource();

                // Print the selected items and the action command.
                depDay = dep1.getSelectedItem().toString();
                System.out.println("Dep day  = " + depDay);
            }
		});
		
		dep2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo
                // box.
                JComboBox dep2 = (JComboBox) event.getSource();

                // Print the selected items and the action command.
                depMonth = dep2.getSelectedItem().toString();
                System.out.println("Dep month  = " + depMonth);
            }
		});
		
		dep3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo
                // box.
                JComboBox dep3 = (JComboBox) event.getSource();

                // Print the selected items and the action command.
                depYear = dep3.getSelectedItem().toString();
                System.out.println("Dep year  = " + depYear);
            }
		});
		
		ret1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo
                // box.
                JComboBox ret1 = (JComboBox) event.getSource();

                // Print the selected items and the action command.
                retDay = ret1.getSelectedItem().toString();
                System.out.println("Ret day  = " + retDay);
            }
		});
		
		ret2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo
                // box.
                JComboBox ret2 = (JComboBox) event.getSource();

                // Print the selected items and the action command.
                retMonth = ret2.getSelectedItem().toString();
                System.out.println("Ret month  = " + retMonth);
                
                
                /*
                if(retMonth.equals("February")){
                	
                    //ret1.removeAllItems();
                    for(int i=1;i<5;i++) {
                    	ret1.removeItemAt(i);
                    }
                    
                    //ret1.removeAllItems();
                    for(int i=0;i<5;i++) {
                    	ret1.removeItemAt(i);
                    }
                    
                	ret1.addItem("1st");
                	//ret1.removeItem("29th");
                	//ret1.removeItem("30th");
                	//ret1.removeItem("31st");
                }
                //ret1.addItem("hi");
                
                */
            }
		});
		
		ret3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo
                // box.
                JComboBox ret3 = (JComboBox) event.getSource();

                // Print the selected items and the action command.
                retYear = dep3.getSelectedItem().toString();
                System.out.println("Ret year  = " + retYear);
            }
		});
 
		
		//Set the layout parameters of the GUI window
		frame.setLayout(new BorderLayout());
		frame.setSize(332, 250);
		frame.setVisible(true); //actually displays GUI
		
		frame.add(depPanel, BorderLayout.NORTH);
		frame.add(retPanel, BorderLayout.EAST);
		
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






