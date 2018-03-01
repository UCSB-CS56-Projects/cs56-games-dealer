package edu.ucsb.cs56.projects.games.dealer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** GamesDealerPanel GUI for games-dealer project
 @author Tristan Starck and Kelly Bielaski
 @version CS56, Winter 2016, UCSB
 */

public class GamesDealerPanel extends JPanel{
    DealerPanelHelper helper; // Helper function that deals the hands and shuffles the deck.
    JTextField playerInput; // Where the user inputs how many hands they want
    String shuffledAns; // Answer to shuffle dropdown input.
    static JScrollPane cardDisplay; //
    static JScrollPane scroller; // Scroller where the application shows the card output for each hand
    JPanel cardOutputPanel;
    static JTextArea outputText;
    JPanel playerPrompt; //
    JPanel display; //
    Deck deck; // Deck being used while program is running.
    JPanel playerPromptsPanel; // Holds JPanel
    JPanel playerInputsPanel; //
    JTextField[] playerInputArray; // Array of JTextFields that holds the JTextField where the user inputs how many cards each player wants to draw
    int[] playerInputArrayInts; // Array of ints that hold how many cards each player wants to draw
    Hand[] hands; // Array of hands to store current hands for all players in.
    JButton continueButton; // Button that allows for continued drawing for current players.
    int total_count = 0;
    
    /**
     Constructor for GamesDealerPanel
     Builds all GUI components of the games dealer application.
     */
    public GamesDealerPanel(){
        super(new BorderLayout());
        // Holds JPanel playerInputPanelnumHands and playerInputPanelShuffle
        JPanel playerInputPanelLayout = new JPanel(new BorderLayout());
        // Panel that holds the how many hands label and holds the JPanel inputPanel.
        JPanel playerInputPanelnumHands = new JPanel(new FlowLayout());
        // Bottom 2/3rds of JFrame. Holds everything in bottom 2/3rds of the JFrame
        JPanel cardOutputPanel = new JPanel(new BorderLayout());
        display = new JPanel();
        
        add(playerInputPanelLayout, BorderLayout.NORTH);
        add(cardOutputPanel, BorderLayout.CENTER);
        
        playerInput=new JTextField(5);
        
        String prompt="How many hands do you want? (Enter an integer greater than 0 and less than 53)";
        // Label to display prompt for user to input how many hands they want.
        JLabel promptLabel=new JLabel(prompt);
        promptLabel.setLabelFor(playerInput);
        playerInputPanelnumHands.add(promptLabel);
        
        // Panel that holds the playerInput textField.
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(playerInput);
        playerInputPanelnumHands.add(inputPanel);
        playerInputPanelLayout.add(playerInputPanelnumHands, BorderLayout.NORTH);
        
        // Panel that holds the shuffleLabel and shuffleBoxPanel
        JPanel playerInputPanelShuffle=new JPanel(new FlowLayout());
        
        // Where player can select how they want to shuffle deck.
        JComboBox shuffleBox;
        String shuffleOptions[] = {"don't shuffle",
            "shuffle once before dealing",
            "shuffle after every set of cards is dealt"};
        shuffleBox = new JComboBox(shuffleOptions);
        
        // Label to display "Shuffle?"
        JLabel shuffleLabel = new JLabel("Shuffle?",JLabel.RIGHT);
        shuffleLabel.setLabelFor(shuffleBox);
        playerInputPanelShuffle.add(shuffleLabel);
        
        // Panel that holds shuffleBox
        JPanel shuffleBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        shuffleBoxPanel.add(shuffleBox);
        playerInputPanelShuffle.add(shuffleBoxPanel);
        playerInputPanelLayout.add(playerInputPanelShuffle, BorderLayout.SOUTH);
        
        deck=new Deck();
        
        /*
         Button used after user has input how many cards each player wants and how they want to shuffle the deck.
         Once the button is clicked, it clears cardOutputPanel, then calls the DealerHelperPanel function to get
         a string to display to the user each hand for every player, when the deck was shuffled, and what cards
         are remaining in the deck.
         */
        class displayCard implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                // Gets player input for when to shuffle the deck.
                shuffledAns = (String) shuffleBox.getSelectedItem();
                playerInputArrayInts= new int[playerInputArray.length];
          
                //put all the numbers read in from the playerInputArray into playerInputArrayInts
                for(int i=0; i<playerInputArray.length;i++){
                    
                    if(playerInputArrayInts!=null){
                        total_count = total_count +Integer.parseInt(playerInputArray[i].getText());
                        if(total_count <= 52 && Integer.parseInt(playerInputArray[i].getText()) > 0){
                            if(playerInputArray[i].getText()==("")){
                                //if the user leaves the text field empty set the default value as 0
                                playerInputArrayInts[i]=0;
                            }
                            else{
                                // Gets numbers from playerInputArray for how many cards each player wants to draw.
                                playerInputArrayInts[i] = Integer.parseInt(playerInputArray[i].getText());
                            }
                       
                        }
                        else
                        {
                         //If user enters number larger than the total number of cards on deck, reset the game
                            String prompt2="Please make sure the sum of cards for all players is less than 53, positive number only. Please reset.";
                            promptLabel.setText(prompt2);
                        }
                    }
                }
             
              if(total_count <= 52){
                // Clears cardOutputPanel
                cardOutputPanel.removeAll();
                // If game is reset, reinitialize hands.
                if(hands==null){
                    hands= new Hand[playerInputArray.length];
                }
                // Draws cards for every player from deck.
                helper=new DealerPanelHelper(playerInputArray.length, playerInputArrayInts,
                                             shuffledAns, deck, hands);
                deck=helper.getDeck();
                hands=helper.getHands();
                String cards = helper.playerCardString();
                
                // JTextArea where output for cards and deck gets displayed.
                JTextArea cardsTextArea = new JTextArea(cards);
                cardsTextArea.setLineWrap(true);
                
                // Holds the scroller which holds the textarea for the output.
                JPanel cardDisplayPanel = new JPanel(new BorderLayout());
                cardOutputPanel.add(cardDisplayPanel, BorderLayout.CENTER);
                
                scroller = new JScrollPane(cardsTextArea);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                cardDisplayPanel.add(scroller);
                cardOutputPanel.add(cardDisplayPanel);
                shuffleBoxPanel.add(continueButton); // Adds continue button next to shufflebox area.
                
                // Sets up cardOutputPanel after being cleaered to show card and deck output.
                cardOutputPanel.revalidate();
                cardOutputPanel.repaint();
               }
            }
        }
        JButton displayCardsButton = new JButton("Display Cards");
        displayCardsButton.addActionListener(new displayCard());
        
        
        
        /*
         Clears all GUI components from cardOutputPanel.
         Button used to first submit how many hands the user wants to play with.
         Displays the area where the user can enter how many cards they want to
         draw for each player. Also displays the displayCards button.
         
         */
        class submit implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                promptLabel.setText(prompt);
                // Clears all components in cardOutputPanel
                cardOutputPanel.removeAll();
                // Resets deck and hands.
                deck=new Deck();
                hands=null;
                total_count = 0;
                
                int numHands = 0;
                
                try
                {
                    // Gets user input for how many hands the user wants.
                    numHands = Integer.parseInt(playerInput.getText());
                    if(numHands < 1){
                        String prompt1="Invalid Number Entered. Enter an integer greater than 0 and less than 53)";
                        promptLabel.setText(prompt1);
                    }
                    else if(numHands > 52){
                        String prompt1="Invalid Number Entered. Enter an integer greater than 0 and less than 53)";
                        promptLabel.setText(prompt1);
                        numHands = 0;
                    }
                    System.out.println(numHands);
                    
                }
                catch (NumberFormatException nfe)
                {
                    String prompt1="Invalid Number Entered. Enter an integer greater than 0 and less than 53)";
                    promptLabel.setText(prompt1);
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
        }
        JButton submitButton = new JButton("Submit/Reset");
        submitButton.addActionListener(new submit());
        playerInputPanelnumHands.add(submitButton);
        

	class home implements ActionListener{
            private JPanel frame;
            public home(JPanel frame){
                this.frame= frame;
            }
            public void actionPerformed(ActionEvent e) {
	        JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
		MainGui.main(new String[0]);
            }
        }

	JButton homeButton = new JButton("Go Home");
	homeButton.addActionListener(new home(this)) ;      
	playerInputPanelnumHands.add(homeButton);
 
         class Continue implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                promptLabel.setText(prompt);
                cardOutputPanel.removeAll();
                shuffleBoxPanel.remove(continueButton);
                
                int user_input_hand;
                shuffledAns = (String) shuffleBox.getSelectedItem();
                user_input_hand = Integer.parseInt(playerInput.getText());
                //no need to reset the number of hand when you continue the game
                System.out.println(shuffledAns);
                playerInputArray = new JTextField[user_input_hand];
                
                GridLayout grid = new GridLayout(user_input_hand,1,1,1);
                playerPromptsPanel = new JPanel(grid);
                
                cardOutputPanel.add(playerPromptsPanel, BorderLayout.CENTER);
                
                for(int i=1; i<user_input_hand+1;i++){
                    JTextField playerCardInput=new JTextField(5);
                    playerInputArray[i-1]=playerCardInput;
                    String question="Player " + i + "/" + user_input_hand+": How many cards do you want? You have " + (52 - total_count) + " cards left";
                    JLabel playerPromptLabel=new JLabel(question, JLabel.RIGHT);
                    
                    playerPromptLabel.setLabelFor(playerCardInput);
                    JPanel playerPromptLabelPanel = new JPanel(new BorderLayout());
                    playerPromptLabelPanel.add(playerPromptLabel,BorderLayout.NORTH);
                    playerPromptsPanel.add(playerPromptLabelPanel);
                    
                    JPanel playerTextFieldPanel  = new JPanel();
                    playerTextFieldPanel.add(playerCardInput);
                    playerPromptsPanel.add(playerTextFieldPanel);
                }
                cardOutputPanel.add(displayCardsButton, BorderLayout.SOUTH);
                
                cardOutputPanel.revalidate();
                cardOutputPanel.repaint();
            }
        }
        continueButton= new JButton("Continue Drawing Cards");
        continueButton.addActionListener(new Continue());
    }
}
