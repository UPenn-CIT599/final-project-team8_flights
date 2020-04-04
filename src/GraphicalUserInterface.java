/**Graphical User Interface class reads and stores data from user*/

import javax.accessibility.Accessible;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.util.Properties;



public class GraphicalUserInterface {
	
	//instance variables
	private String deptCity; //departure city
	private String destCity; //destination city
	private String deptTime; //deparure time
	private String deptdate; //depature date, in format MMDDYYYY
	boolean directFlight = false;
	int maxLayovers; //max allowed no of layovers
	int maxBudget; //max budget for total trip in $
	
	JFrame frame;
	JPanel p2;
	//CheckboxGroup chk;
	
	public static void main(String args[]) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.createGui();
		
	}
	
	void createGui() {
		
	    System.out.println("Status of direct flight: " + directFlight);
		
		
		JTextField tfDeparture, tfDestination, tfResult;
		
		frame = new JFrame();
		
		
		//JPanel p1 = new JPanel();
		//p1.setLayout(new BorderLayout());
		//p1.add(new JButton("AAAAAAAAAAAA"), BorderLayout.NORTH);
		
			
		
		
		JButton but1 = new JButton("GET ME FLIGHTS");

		
		//PANEL 2 - Flight departure and arrival location info
		p2 = new JPanel();
		p2.setLayout(new GridLayout(5,5));
	    tfDeparture =new JTextField(20); 
	    tfDestination = new JTextField(2);
	    tfDeparture.setBounds(50,50,150,20); 
	    
	    tfDeparture.addActionListener(null);
	    tfDestination.addActionListener(null); 
	    
	    p2.add(new JLabel("Enter departure city:"));
		p2.add(tfDeparture);
	    p2.add(new JLabel("Enter destination:"));
	    p2.add(tfDestination);
	    JCheckBox checkbox = new JCheckBox("Direct flights only");
	    p2.add(checkbox);
		p2.add(but1);
		
		//AbstractAction actionHandler = new CheckboxAction("Enable logging");
		//checkbox.setAction(actionHandler);
		checkbox.addActionListener(null);
		
	    final TextField tf=new TextField();  
	    tf.setBounds(50,50, 150,20);
	    p2.add(tf);
		
		//String output;
	    
	    
	    
	    System.out.println("Status of direct flight: " + directFlight);
	    

		
		
	    //ACTION LISTENER, need to figure out how class/method hierarchy acutally works
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
		
		
		
		class CheckboxAction extends AbstractAction {
		    public CheckboxAction(String text) {
		        super(text);
		    }
		 
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JCheckBox cbLog = (JCheckBox) e.getSource();
		        if (cbLog.isSelected()) {
		            System.out.println("Logging is enabled");
		        } else {
		            System.out.println("Logging is disabled");
		        }
		    }
		}
		
		checkbox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JCheckBox cb = (JCheckBox) event.getSource();
		        if (cb.isSelected()) {
		        	directFlight = true;
		            System.out.println("Checkbox selected!");
			    	p2.add(new JLabel("Searching for direct flights only"));
		        } else {
		            // check box is unselected, do something else
		        }
		    }
		});
		
		/*
		class CheckboxGroup{
			public void makeCheckbox() {
			 CheckboxGroup cbg = new CheckboxGroup();
			 p2.add(new JCheckbox("one", cbg, true));
			 p2.add(new JCheckbox("two", cbg, false));
			 p2.add(new JCheckbox("three", cbg, false));
		}
		}
		
		
		class JCheckBox extends JToggleButton implements Accessible {
			
		}
		*/
		
		
		
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 400);
		frame.setVisible(true); //actually displays GUI
		//container.add(p1, BorderLayout.CENTER);
		frame.add(p2, BorderLayout.SOUTH);
		//frame.add(p3, BorderLayout.EAST);
		
	    //new TextFieldExample();  
		
	}
}






