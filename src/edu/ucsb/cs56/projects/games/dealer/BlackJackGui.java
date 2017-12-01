package edu.ucsb.cs56.projects.games.dealer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The Gui version of the game BlackJackGui
 * @author Kin Kwan Poon, Eric Xiao
 * @version UCSB, CS56, F16
 */

public class BlackJackGui extends JPanel{
    JPanel gamePanel;
    GridBagConstraints gridbagconstraints;
    JLabel dealerPoints,playerPoints;
    ArrayList<JLabel> images;
    JPanel resultPanel;
    BlackJackGameGui game;
    JButton hitButton;
    JButton standButton;
    int decided = 0;
    JTextField playerInput; // Where the user inputs how many hands they want
    int points = 10;
    int betpoint = 0;
    
    /**
     * Costructor of BlackJackGui
     */
    public BlackJackGui(){
        super();
        String message ="Welcome to BlackJack!";
        Start_Page(message); //message will show in the center of the start page
    }
    
    /**
     * Shows the welcom screen and Play button
     * @param message
     * Constructor of string a for welcome class
     */
     public void Start_Page(String message){
        JPanel Start_Page = new JPanel(new GridBagLayout());
        GridBagConstraints Grid = new GridBagConstraints();
        JLabel welcomePrompt = new JLabel(message);
        welcomePrompt.setFont(new Font("Sans Serif",Font.PLAIN,20));

        Grid.insets=new Insets(250,0,0,0);
        Grid.gridx=1;
        Grid.gridy=1;
        Start_Page.add(welcomePrompt,Grid);
        
        Grid.insets=new Insets(20,0,0,0);
        Grid.gridx=1;
        Grid.gridy=2;
        String prompt_bet="How many points do you want to bet? You currently have " + points +" points";
        // Label to display prompt for user to input how many hands they want.
        JLabel promptLabel=new JLabel(prompt_bet);
        promptLabel.setLabelFor(playerInput);
        Start_Page.add(promptLabel,Grid);
         
        Grid.insets=new Insets(0,0,0,0);
        Grid.gridx=1;
        Grid.gridy=3;
        playerInput=new JTextField(5);
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(playerInput);
        Start_Page.add(inputPanel,Grid);
        /**
         * A inner class for play button
         */
        class Play implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                //get player input for the betpoint
                betpoint = Integer.parseInt(playerInput.getText());
                //if player enters a invalid number, show prompt ask for another input
                if(betpoint < 0){    
                    String prompt_invalid="Invalid number entered. How many points do you want to bet? You currently have " + points +" points";
                    promptLabel.setText(prompt_invalid);
                    return;
                }
                else if(betpoint > 10){
                    String prompt_invalid="Invalid number entered. How many points do you want to bet? You currently have " + points +" points";
                    promptLabel.setText(prompt_invalid);
                    return;
                }
                //clear the page and enter the game
                Start_Page.removeAll();
                Start_Page.repaint();
                revalidate();
                Game();
                //enable the hit and stand buttons
                EnableButtons();
            }
        }
        JButton PlayButton = new JButton("Play");
        PlayButton.addActionListener(new Play());
        Grid.insets=new Insets(10,0,0,0);
        Grid.gridx=1;
        Grid.gridy=4;
        Start_Page.add(PlayButton,Grid);
        add(Start_Page);
    }
    
    
    /**
     * The actual game play in GUI
     */
    public void Game(){
        gamePanel = new JPanel(new GridBagLayout());
        gridbagconstraints = new GridBagConstraints();
        images = new ArrayList<JLabel>();
        JLabel dealerLabel= new JLabel("Dealer: ");
        JLabel playerLabel = new JLabel("Player: ");
        gbcConfig("dealer",0,0);
        gamePanel.add(dealerLabel,gridbagconstraints);
        
        gbcConfig("player",0,0);
        gamePanel.add(playerLabel, gridbagconstraints);
        gridbagconstraints.gridx=0;
        gridbagconstraints.gridy=0;
        add(gamePanel,gridbagconstraints);
        revalidate();
        repaint();
        
        game=new BlackJackGameGui();
        dealerPoints= new JLabel(Integer.toString(
                                                  game.getHouse().getHandValue()));
        playerPoints = new JLabel(Integer.toString(
                                                   game.getPlayer().getHandValue()));
        game.start();
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        
        
        /**
         * The inner class for hit button
         */
        class Hit implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent arg0) {
                  if(!game.getPlayer().isBusted() && decided == 0){
                        game.playerHit();
                      if(game.getPlayer().isBusted()){
                            points = points - betpoint;
                            DisableButtons();
                            ResultDisplay(game.getPlayer().busted(),game.result(true));
                      }
                    }  
                
            }
        }
        
        hitButton.addActionListener(new Hit());
        gridbagconstraints.anchor=GridBagConstraints.LAST_LINE_START;
        gridbagconstraints.insets=new Insets(0,0,0,10);
        gridbagconstraints.gridx=0;
        gridbagconstraints.gridy=3;
        gridbagconstraints.gridwidth=1;
        gamePanel.add(hitButton,gridbagconstraints);
        
        /**
         * The inner class of stand button
         */
        class Stand implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent arg0){
                
                  if(!game.getPlayer().isBusted() && decided == 0){
                        game.houseHit();
                      if(game.getHouse().isBusted()){
                          points = points + betpoint;
                          DisableButtons();
                          ResultDisplay(game.getHouse().busted(),game.result(true));
                      }
                        else if (game.getplayervalue() > game.gethousevalue()){
                            points = points + betpoint;
                            DisableButtons();
                            ResultDisplay("Dealer loses",game.result(true));
                        }
                        else{
                            points = points - betpoint;
                            DisableButtons();
                            ResultDisplay("Dealer Wins",game.result(true));
                            
                        }   
                }
            }
        }
        //add stand button to the panel
        standButton.addActionListener(new Stand());
        gridbagconstraints.anchor=GridBagConstraints.LAST_LINE_END;
        gridbagconstraints.gridx=0;
        gridbagconstraints.gridy=4;
        gridbagconstraints.gridwidth=1;
        gamePanel.add(standButton,gridbagconstraints);
        
    }
    
    /**
     * Shows the game result in GUI by making a new panel,
     * and show the contiue button for player to continue the game
     * It takes up to two parameter:
     * 1: If either dealer or player is busted
     * state1 would be a busted message, then state2 would be a win/lose message.
     * 2: If no one is busted, state1 would be the win/lose message,
     * and state2 would be a empty string.
     * @param state1 the win/lose state of player, or a busted state
     * @param state2 the win/lose  state of player
     */
     public void ResultDisplay(String state1,String state2){
        GridBagConstraints resultgrid = new GridBagConstraints();
        resultPanel = new JPanel(new GridBagLayout());
        JLabel resultLabel=new JLabel(state1);
        resultgrid.fill=GridBagConstraints.VERTICAL;
        resultgrid.gridx=0;
        resultgrid.gridy=1;
        resultPanel.add(resultLabel,resultgrid);
         
        resultgrid.insets=new Insets(20,0,0,0);
        resultgrid.gridx=0;
        resultgrid.gridy=3;
        
        String prompt_bet="How many points do you want to bet? You currently have " + points + " points";
        // Label to display prompt for user to input how many hands they want.
        JLabel promptLabel=new JLabel(prompt_bet);
        promptLabel.setLabelFor(playerInput);
        resultPanel.add(promptLabel,resultgrid);
         
        /**
         * The inner class of the continue button
         */
        class Continue implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                betpoint = Integer.parseInt(playerInput.getText());
                //check if player still have points to bet
                if (points == 0 && betpoint != 0){
                    //Start a new game with the message "you lost all of your points!"
                    //if player try to bet any points when dont have any left
                    resultPanel.removeAll();
                    gamePanel.removeAll();
                    decided = 0;
                    game.getPlayer().clearHand();
                    game.getHouse().clearHand();
                    gamePanel.revalidate();
                    gamePanel.repaint();
                    points = 10;
                    String message = "You lost all of your points!";
                    Start_Page(message);
                    return;
                }
               
                betpoint = Integer.parseInt(playerInput.getText());
                Settext(promptLabel,points, betpoint);
            }
        }
        //add input field to the panel
        resultgrid.insets=new Insets(0,0,0,0);
        resultgrid.gridx=0;
        resultgrid.gridy=4;
        playerInput=new JTextField(5);
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(playerInput);
        resultPanel.add(inputPanel,resultgrid);
        
        //add continue button to the panel
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new Continue());
        resultgrid.fill=GridBagConstraints.VERTICAL;
        resultgrid.insets=new Insets(0,0,0,0);
        resultgrid.gridx=0;
        resultgrid.gridy=5;
        resultPanel.add(continueButton,resultgrid);
        
        gridbagconstraints.anchor=GridBagConstraints.LINE_START;
        gridbagconstraints.gridx=0;
        gridbagconstraints.gridy=1;
        add(resultPanel,gridbagconstraints);
        decided = 1;
    }
    
    /**
     * To change the GridBagConstraints of the GridBagLayout.
     * @param hand Delaer hand or player hand
     * @param right the pixel to move to right
     * @param top the pixel to move to botttom
     */
    public void gbcConfig(String hand, int right , int top){
        if(hand.equals("dealer")){
            gridbagconstraints.fill=GridBagConstraints.HORIZONTAL;
            gridbagconstraints.anchor=GridBagConstraints.FIRST_LINE_START;
            gridbagconstraints.insets=new Insets(50+top,0,0,600-right);
            gridbagconstraints.gridx=0;
            gridbagconstraints.gridy=0;
        }else if(hand.equals("player")){
            gridbagconstraints.anchor= GridBagConstraints.LAST_LINE_START;
            gridbagconstraints.insets=new Insets(250,0,top,600-right);
            gridbagconstraints.gridx=0;
            gridbagconstraints.gridy=0;
        }
    }
    
    //disable hit and stand buttons
    public void DisableButtons(){
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }
    
    //enable hit and stand buttons
    public void EnableButtons(){
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
    }
    
    public void Settext(JLabel promp, int point, int bet){
        if(bet < 0 || bet > point){
            String promptreenter="Invalid number entered. How many points do you want to bet? You currently have " + points +" points";
            promp.setText(promptreenter);
        }
        else{
            resultPanel.removeAll();
            gamePanel.removeAll();
            decided = 0;
            game.getPlayer().clearHand();
            game.getHouse().clearHand();
            gamePanel.revalidate();
            gamePanel.repaint();
            Game();
            EnableButtons();
        }
    }
    
    /**
     * An inner class that extends the game logic class to add display to the gui
     */
    class BlackJackGameGui extends BlackJackGame{
        Card hiddenCard;
        public BlackJackGameGui(){
            super(4,"Player");
        }
        
        /**
         * Same as start method in BlackJackGame class
         */
        @Override
        public void start(){
            for(int i=0;i<2;i++){
                this.getHouse().addtoHand(1, d);
                if(i==0){
                    house.getHand().get(0).hide();
                    hiddenCard=house.getHand().get(0);
                }
                display(house);
                playerHit();
            }
        }
        
        
         public int getplayervalue(){
            return player.getHandValue();
        }
        
        public int gethousevalue(){
            return house.getHandValue();
        }
        
        /**
         * Same as playerHit method in BlackJackGame class
         */
        @Override
        public void playerHit(){
            player.addtoHand(1, d);
            display(player);
            if(player.isBusted())
                player.busted();
        }
        
        /**
         * Same as houseHit method in BlackJackGame class
         */
        @Override
        public void houseHit(){
            house.getHand().get(0).showHidden();
            images.get(0).setIcon(new ImageIcon(path(hiddenCard)));
            SoundEffect.playSound("deal", 1,4);
            display(house);
            while(house.isHitting()&&!house.isBusted()){
                house.addtoHand(1, d);
                display(house);
            }
            
        }
        /**
         * Same as result method in BlackJackGame class,but this one return a string
         */
        public String result(boolean gui){
            if((house.isBusted()||
                player.getHandValue()>house.getHandValue())
               &&!player.isBusted())
                return player.win();
            else if(player.isBusted()||
                    !house.isBusted()&&
                    player.getHandValue()<house.getHandValue())
                return player.lose();
            else
                return player.push();
        }
        
        /**
         * The method to display the card image of a Hand
         *@param h the Hand that need to be display
         */
        public void display(Hand h){
            int i=200;
            System.out.println(h.toString());
            for(Card c:h.getHand()){
                ImageIcon image = new ImageIcon(path(c));
                JLabel label = new JLabel("",image,JLabel.CENTER);
                images.add(label);
                if(h==house){
                    gbcConfig("dealer",i,0);
                    gamePanel.add(images.get(images.size()-1),gridbagconstraints);
                    gbcConfig("dealer",0,20);
                    pointsUpdate();
                    gamePanel.add(dealerPoints,gridbagconstraints);
                }else if (h==player){
                    gbcConfig("player",i,0);
                    gamePanel.add(images.get(images.size()-1),gridbagconstraints);
                    gbcConfig("player",0,20);
                    pointsUpdate();
                    gamePanel.add(playerPoints,gridbagconstraints);
                }
                gamePanel.revalidate();
                gamePanel.repaint();
                i+=200;
            }
            sleep(500);
        }
        
        /**
         * Update the total points of both dealer and player hand
         */
        public void pointsUpdate(){
            dealerPoints.setText(Integer.toString(house.getHandValue()));
            playerPoints.setText(Integer.toString(player.getHandValue()));
        }
        
        /**
         * returns a path of image to the specified card
         * @param c card to find path to
         */
        public String path(Card c) {
            String path = "images/";
            String suit = String.valueOf(c.getSuit().charAt(0)).toLowerCase();
            String rank = String.valueOf(c.getRank().charAt(0));
            if(!rank.equals("X")){
                if(rank.equals("A"))
                    rank = new String("1");
                else if (rank.equals("J"))
                    rank = new String("11");
                else if (rank.equals("Q"))
                    rank = new String("12");
                else if (rank.equals("K"))
                    rank = new String("13");
                else if(rank.equals("1"))
                    rank= new String("10");
                path += suit;
                if(!(rank.equals("10")||rank.equals("11") || rank.equals("12") || rank.equals("13")))
                    path += "0";
                path += rank;
            }else
                path+="back";
            path += ".png";
            return path;
        }
    }
}
