package edu.ucsb.cs56.projects.games.dealer;

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

/** GamesDealerPanel GUI for games-dealer project

    @author Tristan Starck and Kelly Bielaski
    @version CS56, Winter 2016, UCSB

*/


public class GamesDealerPanel extends JPanel{

    JTextField playerInput;

    static JScrollPane cardDisplay;
    static JScrollPane scroller;
    JPanel cardOutputPanel;
    static JTextArea outputText;
    JPanel playerPrompt;
    JPanel display;

    JPanel playerPromptsPanel;
    JPanel playerInputsPanel;
    JTextField[] playerInputArray;
    
    public GamesDealerPanel(){
	super(new BorderLayout());
	
	 JPanel playerInputPanel = new JPanel(new FlowLayout());
	 JPanel cardOutputPanel = new JPanel(new BorderLayout());
	 display = new JPanel();
	 
	 add(playerInputPanel, BorderLayout.NORTH);
	 add(cardOutputPanel, BorderLayout.CENTER);

	 //set up the command line prompt(label) and text field for input
	 playerInput=new JTextField(5);
	 String prompt="How many hands do you want? (Enter an integer greater than 0)";
	 JLabel promptLabel=new JLabel(prompt, JLabel.RIGHT);
	 promptLabel.setLabelFor(playerInput);
	 playerInputPanel.add(promptLabel);

	 //playerInput= new JTextField(5);
	 JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 inputPanel.add(playerInput);
	 playerInputPanel.add(inputPanel);

	 //set up the pane for displaying the cards dealt
	 //for displaying the images of the cards, I think we want the JScrollPane https://da2i.univ-lille1.fr/doc/tutorial-java/uiswing/components/scrollpane.html

	 /*	 cardDisplay= new JScrollPane();

	 outputText= new JTextArea(1,1);
	 outputText.setLineWrap(true);
	 
	 scroller = new JScrollPane(outputText);
	 scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	 scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	 
	 cardDisplay.add(scroller);
	 */
	 	JButton submit = new JButton("Submit");
	 
	submit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// outputText.setText(null);
		int numHands;
		try
		{
		    numHands = Integer.parseInt(playerInput.getText());

		}
		catch (NumberFormatException nfe)
		{
		    numHands = 1;

		}
		playerInputArray = new JTextField[numHands];
		
		playerPromptsPanel = new JPanel(new GridLayout(numHands,1));
		playerInputsPanel = new JPanel(new GridLayout(numHands, 1));
		cardOutputPanel.add(playerPromptsPanel, BorderLayout.WEST);
		cardOutputPanel.add(playerInputsPanel, BorderLayout.EAST);
		for(int i=1; i<numHands+1;i++){
		   
		    JTextField playerCardInput=new JTextField(5);
		    playerInputArray[i-1]=playerCardInput;
		    String question="Player " +i+"/"+numHands+": How many cards do you want?";
		    JLabel playerPromptLabel=new JLabel(question, JLabel.RIGHT);
		    playerPromptLabel.setLabelFor(playerCardInput);
		    playerPromptsPanel.add(playerPromptLabel);

		    //playerInput= new JTextField(5);
		    JPanel playerTextFieldPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		    playerTextFieldPanel.add(playerCardInput);
		    playerInputsPanel.add(playerTextFieldPanel);
		}
		
		//	outputText.append(BACMessage.GuiMessage(BAC) + "\n");
		
	    }
	    });
	playerInputPanel.add(submit);
	// cardOutputPanel.add(cardDisplay);

	 
	 
	 /*
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
	 */
	 // display.add(submit);
	  
	
    }

}
