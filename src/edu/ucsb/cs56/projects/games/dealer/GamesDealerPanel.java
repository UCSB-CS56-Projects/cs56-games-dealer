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
    DealerPanelHelper helper;
    JTextField playerInput;
    String shuffledAns;
    static JScrollPane cardDisplay;
    static JScrollPane scroller;
    JPanel cardOutputPanel;
    static JTextArea outputText;
    JPanel playerPrompt;
    JPanel display;
    Deck deck;
    JPanel playerPromptsPanel;
    JPanel playerInputsPanel;
    JTextField[] playerInputArray;
    int[] playerInputArrayInts;
    Hand[] hands;
    JButton continueButton;
    
    public GamesDealerPanel(){
	super(new BorderLayout());
	JPanel playerInputPanelLayout = new JPanel(new BorderLayout());
	JPanel playerInputPanelnumHands = new JPanel(new FlowLayout());
	JPanel cardOutputPanel = new JPanel(new BorderLayout());
	display = new JPanel();
	 
	add(playerInputPanelLayout, BorderLayout.NORTH);
	add(cardOutputPanel, BorderLayout.CENTER);

	playerInput=new JTextField(5);
	String prompt="How many hands do you want? (Enter an integer greater than 0 and less than 11)";
	JLabel promptLabel=new JLabel(prompt);
	promptLabel.setLabelFor(playerInput);
	playerInputPanelnumHands.add(promptLabel);

	JPanel inputPanel = new JPanel(new FlowLayout());
	inputPanel.add(playerInput);
	playerInputPanelnumHands.add(inputPanel);
	playerInputPanelLayout.add(playerInputPanelnumHands, BorderLayout.NORTH);

	JPanel playerInputPanelShuffle=new JPanel(new FlowLayout());
	JComboBox shuffleBox;
	String shuffleOptions[] = {"don't shuffle", "shuffle once before dealing","shuffle after every set of cards is dealt"};
	shuffleBox = new JComboBox(shuffleOptions);
	 
	JLabel shuffleLabel = new JLabel("Shuffle?",JLabel.RIGHT);
	shuffleLabel.setLabelFor(shuffleBox);
	playerInputPanelShuffle.add(shuffleLabel);
	 
	JPanel shuffleBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	shuffleBoxPanel.add(shuffleBox);
	playerInputPanelShuffle.add(shuffleBoxPanel);
	playerInputPanelLayout.add(playerInputPanelShuffle, BorderLayout.SOUTH);

	deck=new Deck();

	JButton displayCardsButton = new JButton("Display Cards");
	displayCardsButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    shuffledAns = (String) shuffleBox.getSelectedItem();
		    playerInputArrayInts= new int[playerInputArray.length];
		    //put all the numbers read in from the playerInputArray into playerInputArrayInts
		    for(int i=0; i<playerInputArray.length;i++){
			if(playerInputArrayInts!=null){
			    try
				{
				    if(playerInputArray[i].getText()==("")){                  //if the user leaves the text field empty set the default value as 0
					playerInputArrayInts[i]=0;
				    }
				    else{
					playerInputArrayInts[i] = Integer.parseInt(playerInputArray[i].getText());
					if(playerInputArrayInts[i] < 0)
					    playerInputArrayInts[i] = 0;                       //set the default if the player asks for a negative number of cards is 0
				    }
				}
			    catch (NumberFormatException nfe)
				{
	
				    playerInputArrayInts[i] = 0;
				
				}
			}
		    }
		    cardOutputPanel.removeAll();
		    if(hands==null){
			hands= new Hand[playerInputArray.length];
		    }
		    helper=new DealerPanelHelper(playerInputArray.length, playerInputArrayInts, shuffledAns, deck, hands);
		    deck=helper.getDeck();
		    hands=helper.getHands();
		    String cards = helper.playerCardString();                        

		    JTextArea cardsTextArea = new JTextArea(cards);
		    cardsTextArea.setLineWrap(true);
		    
		    
		    JPanel cardDisplayPanel = new JPanel(new BorderLayout());
		    cardOutputPanel.add(cardDisplayPanel, BorderLayout.CENTER);
		    
		    scroller = new JScrollPane(cardsTextArea);
		    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		    cardDisplayPanel.add(scroller);
		    cardOutputPanel.add(cardDisplayPanel);
		    shuffleBoxPanel.add(continueButton);
		    cardOutputPanel.revalidate();
		    cardOutputPanel.repaint();

		}
	    });
	
	JButton submit = new JButton("Submit/Reset");
	submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    cardOutputPanel.removeAll();
		    deck=new Deck();
		    hands=null;

		    int numHands;

		    try
			{
			    numHands = Integer.parseInt(playerInput.getText());
			    if(numHands < 1)
				numHands = 1;
			    if(numHands > 10)
				numHands = 10;
			    System.out.println(numHands);

			}
		    catch (NumberFormatException nfe)
			{
			    numHands = 1;

			}
		    System.out.println(shuffledAns);
		    playerInputArray = new JTextField[numHands];

		    GridLayout grid = new GridLayout(numHands,1,1,1);
		    playerPromptsPanel = new JPanel(grid);
    	      
		    cardOutputPanel.add(playerPromptsPanel, BorderLayout.CENTER);
	
		    for(int i=1; i<numHands+1;i++){
		   
			JTextField playerCardInput=new JTextField(5);
			playerInputArray[i-1]=playerCardInput;
			String question="Player " +i+"/"+numHands+": How many cards do you want?";
			JLabel playerPromptLabel=new JLabel(question, JLabel.RIGHT);
			playerPromptLabel.setLabelFor(playerCardInput);
			JPanel playerPromptLabelPanel = new JPanel(new BorderLayout());
			playerPromptLabelPanel.add(playerPromptLabel,BorderLayout.NORTH);
			playerPromptsPanel.add(playerPromptLabelPanel);

			JPanel playerTextFieldPanel  = new JPanel(new FlowLayout());
			playerTextFieldPanel.add(playerCardInput);
			playerPromptsPanel.add(playerTextFieldPanel);
		    }
		    cardOutputPanel.add(displayCardsButton, BorderLayout.SOUTH);
	        
		    cardOutputPanel.revalidate();
		    cardOutputPanel.repaint();
		}
	    });
	playerInputPanelnumHands.add(submit);


	continueButton= new JButton("Continue Drawing Cards");
	continueButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    cardOutputPanel.removeAll();
		    shuffleBoxPanel.remove(continueButton);

		    int numHands;
		    shuffledAns = (String) shuffleBox.getSelectedItem();
		    try
			{
			    numHands = Integer.parseInt(playerInput.getText());
			    if(numHands < 1)
				numHands = 1;
			    if(numHands > 10)
				numHands = 10;
			    System.out.println(numHands);

			}
		    catch (NumberFormatException nfe)
			{
			    numHands = 1;

			}
		    System.out.println(shuffledAns);
		    playerInputArray = new JTextField[numHands];

		    GridLayout grid = new GridLayout(numHands,1,1,1);
		    playerPromptsPanel = new JPanel(grid);
		
		    cardOutputPanel.add(playerPromptsPanel, BorderLayout.CENTER);
	
		    for(int i=1; i<numHands+1;i++){
		   
			JTextField playerCardInput=new JTextField(5);
			playerInputArray[i-1]=playerCardInput;
			String question="Player " +i+"/"+numHands+": How many cards do you want?";
			JLabel playerPromptLabel=new JLabel(question, JLabel.RIGHT);
			playerPromptLabel.setLabelFor(playerCardInput);
			JPanel playerPromptLabelPanel = new JPanel(new BorderLayout());
			playerPromptLabelPanel.add(playerPromptLabel,BorderLayout.NORTH);
			playerPromptsPanel.add(playerPromptLabelPanel);

			JPanel playerTextFieldPanel  = new JPanel(new FlowLayout());
			playerTextFieldPanel.add(playerCardInput);
			playerPromptsPanel.add(playerTextFieldPanel);
		    }
		    cardOutputPanel.add(displayCardsButton, BorderLayout.SOUTH);
	        
		    cardOutputPanel.revalidate();
		    cardOutputPanel.repaint();
		}
	    });
 
	
    }

}
