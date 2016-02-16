package edu.ucsb.cs56.S12.ramonrovirosa.choicePoints;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.*;

/** BACPanel for our SimpleGui BAC Calculator application

     @author Ramon Rovirosa
     @author Shervin Shaikh
     @version CS56 Choice Points, Spring 2012, UCSB

     @author Raghav Raju
     @author Nick Poon
     @version lab07, W15, uCSB
*/


public class BACPanel extends JPanel{

    JTextField weightField;
    JTextField hoursField;

    static JTextArea BACArea;
    static JScrollPane scroller;

    JComboBox weightUnits;
    JComboBox gender;
    JComboBox beer;
    JComboBox wine;
    JComboBox hardLiquor;
    
    JPanel display;

    public BACPanel(){
	super(new BorderLayout());
	 
	 JPanel labelPanel = new JPanel(new GridLayout(7,1));
	 JPanel fieldPanel = new JPanel(new GridLayout(7,1));
	 display = new JPanel();
	 
	 add(labelPanel, BorderLayout.WEST);
	 add(fieldPanel, BorderLayout.CENTER);
	 add(display, BorderLayout.SOUTH);
	 
	 //create a weight label & text field
	 weightField = new JTextField();
	 weightField.setColumns(10);	 

	 JLabel weightLabel = new JLabel("Weight ",JLabel.RIGHT);
	 weightLabel.setLabelFor(weightField);

	 labelPanel.add(weightLabel);
	 
	 JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 weightPanel.add(weightField);
	 fieldPanel.add(weightPanel);

	//create a lbs/kgs combo box
	String weightArray[] = {"Pounds", "Kilograms"};

	 weightUnits = new JComboBox(weightArray);
	 
	 JLabel weightUnitsLabel = new JLabel("Units",JLabel.RIGHT);
	 weightUnitsLabel.setLabelFor(weightUnits);
	 labelPanel.add(weightUnitsLabel);
	 
	 JPanel weightUnitsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 weightUnitsPanel.add(weightUnits);
	 fieldPanel.add(weightUnitsPanel);

	 //create a hours text field and panel
	 hoursField = new JTextField();
	 hoursField.setColumns(10);	 

	 JLabel hoursLabel = new JLabel("Hours Drinking",JLabel.RIGHT);
	 hoursLabel.setLabelFor(hoursField);

	 labelPanel.add(hoursLabel);
	 
	 JPanel hoursPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 hoursPanel.add(hoursField);
	 fieldPanel.add(hoursPanel); 

	 //create a male/female combo box
	 String genderArray[] = {"Male", "Female"};

	 gender = new JComboBox(genderArray);
	 
	 JLabel genderLabel = new JLabel("Gender",JLabel.RIGHT);
	 genderLabel.setLabelFor(gender);
	 labelPanel.add(genderLabel);
	 
	 JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 genderPanel.add(gender);
	 fieldPanel.add(genderPanel); 

	 //create a beer label
	 String number[]  = {"0","1","2","3","4","5","6","7","8","9",
			"10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25"};

	 beer = new JComboBox(number);
	 
	 JLabel beerLabel = new JLabel("# of Beers",JLabel.RIGHT);
	 beerLabel.setLabelFor(beer);
	 labelPanel.add(beerLabel);
	 
	 JPanel beerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 beerPanel.add(beer);
	 fieldPanel.add(beerPanel); 

	 //create a wine label
	 wine = new JComboBox(number);
	 
	 JLabel wineLabel = new JLabel("Glasses of Wine",JLabel.RIGHT);
	 beerLabel.setLabelFor(wine);
	 labelPanel.add(wineLabel);
	 
	 JPanel winePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 winePanel.add(wine);
	 fieldPanel.add(winePanel); 

	 //create a Hard Liquor label
	 hardLiquor = new JComboBox(number);
	 
	 JLabel hardLiquorLabel = new JLabel("Hard Liquor Shots",JLabel.RIGHT);
	 beerLabel.setLabelFor(hardLiquor);
	 labelPanel.add(hardLiquorLabel);
	 
	 JPanel liquorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 liquorPanel.add(hardLiquor);
	 fieldPanel.add(liquorPanel); 

	 BACArea = new JTextArea(10, 20);
	 BACArea.setLineWrap(true);
	 BACArea.setRows(15);

	 scroller = new JScrollPane(BACArea);
	 scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	 scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
	 display.add(scroller);

	JButton submit = new JButton("Calculate BAC");
	
	submit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		BACArea.setText(null);
		boolean isMale;
		String gender1 = (String) gender.getSelectedItem();
		int hours;
		int weight;
		try
		{
			hours = Integer.parseInt(hoursField.getText());
			weight = Integer.parseInt(weightField.getText());

		}
		catch (NumberFormatException nfe)
		{
			hours = -1;
			weight = -1;

		}
		


		boolean isKilograms = false;
		String lbsOrKg = (String) weightUnits.getSelectedItem();
		int beer1 = Integer.parseInt( (String) beer.getSelectedItem());
		int wine1 = Integer.parseInt( (String) wine.getSelectedItem());
		int hardLiquor1 = Integer.parseInt( (String) hardLiquor.getSelectedItem());
		isMale = gender1.equals("Male") ? true : false ;
		isKilograms = lbsOrKg.equals("Kilograms") ? true : false ;
	
		double BAC = Calc.BAC(isMale, hours, weight, isKilograms, beer1, wine1, hardLiquor1);


		BACArea.append(BACMessage.GuiMessage(BAC) + "\n");
		
	    }
	    });

	  display.add(submit);
	  

    }

}
