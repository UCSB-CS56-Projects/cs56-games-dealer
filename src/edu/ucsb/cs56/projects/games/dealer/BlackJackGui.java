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
    GridBagConstraints gridBagConstraints;
    JLabel dealerPoints,playerPoints;
    ArrayList<JLabel> images;
    JPanel resultPanel;
    BlackJackGameGui game;
    JButton hitButton;
    JButton standButton;
    JLabel promptLabel;
    JPanel start;
    JLabel dealerLabel= new JLabel("Dealer: ");
    JLabel playerLabel = new JLabel("Player: ");
    int decided = 0;
    JTextField playerInput=new JTextField(5); // Where the user inputs how many hands they want
    int points = 10;
    int betpoint = 0;
    
    /**
     * Costructor of BlackJackGui
     */
    public BlackJackGui(){
        super();
        String message ="Welcome to BlackJack!";
        startPage(message); //message will show in the center of the start page
    }
    
    /**
     * Shows the welcom screen and Play button
     * @param message
     * Constructor of string a for welcome class
     */
     public void startPage(String message){
        start = new JPanel(new GridBagLayout());
        GridBagConstraints Grid = new GridBagConstraints();
        JLabel welcomePrompt = new JLabel(message);
        welcomePrompt.setFont(new Font("Sans Serif",Font.PLAIN,20));

        Grid.insets=new Insets(250,0,0,0);
        Grid.gridx=1;
        Grid.gridy=1;
        start.add(welcomePrompt,Grid);
        
        Grid.insets=new Insets(20,0,0,0);
        Grid.gridy=2;
        String prompt_bet="How many points do you want to bet? You currently have " + points +" points";
        // Label to display prompt for user to input how many hands they want.
        promptLabel=new JLabel(prompt_bet);
        promptLabel.setLabelFor(playerInput);
        start.add(promptLabel,Grid);
         
        Grid.gridy=3;
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(playerInput);
        start.add(inputPanel,Grid);
         //add play button
        JButton PlayButton = new JButton("Play");
        PlayButton.addActionListener(new Play());
        Grid.insets=new Insets(10,0,0,0);
        Grid.gridy=4;
        start.add(PlayButton,Grid);
        add(start);
    }
    
    /**
     * The actual game play in GUI
     */
    public void gamePlay(){
        gamePanel = new JPanel(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        images = new ArrayList<JLabel>();

        gbcConfig("dealer",0,0);
        gamePanel.add(dealerLabel,gridBagConstraints);
        
        gbcConfig("player",0,0);
        gamePanel.add(playerLabel, gridBagConstraints);
        add(gamePanel,gridBagConstraints);
        revalidate();
        repaint();
        
        game=new BlackJackGameGui();
        dealerPoints= new JLabel(Integer.toString(game.getHouse().getHandValue()));
        playerPoints = new JLabel(Integer.toString(game.getPlayer().getHandValue()));
        game.start();
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        
        //add hit button on game page
        hitButton.addActionListener(new Hit());
        gridBagConstraints.anchor=GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets=new Insets(0,0,0,10);
        gridBagConstraints.gridy=3;
        gamePanel.add(hitButton,gridBagConstraints);
        
        //add stand button on game page
        standButton.addActionListener(new Stand());
        gridBagConstraints.anchor=GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.gridy=4;
        gamePanel.add(standButton,gridBagConstraints);
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
     public void resultDisplay(String state1,String state2){
        GridBagConstraints resultgrid = new GridBagConstraints();
        resultPanel = new JPanel(new GridBagLayout());
        JLabel resultLabel=new JLabel(state1);
        resultgrid.fill=GridBagConstraints.VERTICAL;
        resultgrid.gridy=1;
        resultPanel.add(resultLabel,resultgrid);
         
        resultgrid.insets=new Insets(20,0,0,0);
        resultgrid.gridy=3;
        
        String prompt_bet="How many points do you want to bet? You currently have " + points + " points";
        // Label to display prompt for user to input how many hands they want.
        promptLabel=new JLabel(prompt_bet);
        promptLabel.setLabelFor(playerInput);
        resultPanel.add(promptLabel,resultgrid);
        
        resultgrid.gridy=4;
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(playerInput);
        resultPanel.add(inputPanel,resultgrid);
        //add continue button    
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new Continue());
        resultgrid.fill=GridBagConstraints.VERTICAL;
        resultgrid.gridy=5;
        resultPanel.add(continueButton,resultgrid);
        
        gridBagConstraints.anchor=GridBagConstraints.LINE_START;
        gridBagConstraints.gridy=1;
        add(resultPanel,gridBagConstraints);
        decided = 1;
    }
    
    
    class Continue implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            betpoint = Integer.parseInt(playerInput.getText());
            
            if (points == 0 && betpoint != 0){
                resultPanel.removeAll();
                gamePanel.removeAll();
                decided = 0;
                game.getPlayer().clearHand();
                game.getHouse().clearHand();
                gamePanel.revalidate();
                gamePanel.repaint();
                points = 10;
                //Start a new game with the message "you lost all of your points!"
                //if player try to bet any points when dont have any left
                String message = "You lost all of your points!";
                startPage(message);
                return;
            }
            
            betpoint = Integer.parseInt(playerInput.getText());
            Settext(betpoint);
        }
    }
    
    
    class Hit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(!game.getPlayer().isBusted() && decided == 0){
                game.playerHit();
                if(game.getPlayer().isBusted()){
                    points = points - betpoint;
                    DisableButtons();
                    resultDisplay(game.getPlayer().busted(),game.result(true));
                }
            }
            
        }
    }
    
    class Stand implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0){
            
            if(!game.getPlayer().isBusted() && decided == 0){
                game.houseHit();
                DisableButtons();
                if(game.getHouse().isBusted() || game.getplayervalue() > game.gethousevalue()){
                    points = points + betpoint;
                    resultDisplay("Dealer loses",game.result(true));
                    return;
                }
                points = points - betpoint;
                resultDisplay("Dealer Wins",game.result(true));
            }
        }
    }
    
    class Play implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            betpoint = Integer.parseInt(playerInput.getText());
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
            System.out.println(betpoint);
            start.removeAll();
            start.repaint();
            revalidate();
            gamePlay();
            EnableButtons();
        }
    }
    
    
    /**
     * To change the GridBagConstraints of the GridBagLayout.
     * @param hand Delaer hand or player hand
     * @param right the pixel to move to right
     * @param top the pixel to move to botttom
     */
    public void gbcConfig(String hand, int right , int top){
        if(hand.equals("dealer")){
            gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor=GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.insets=new Insets(50+top,0,0,600-right);
            gridBagConstraints.gridx=0;
            gridBagConstraints.gridy=0;
        }else if(hand.equals("player")){
            gridBagConstraints.anchor= GridBagConstraints.LAST_LINE_START;
            gridBagConstraints.insets=new Insets(250,0,top,600-right);
            gridBagConstraints.gridx=0;
            gridBagConstraints.gridy=0;
        }
    }
    
    public void DisableButtons(){
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }
    
    public void EnableButtons(){
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
    }
    
    public void Settext(int bet){
        if(bet < 0 || bet > points){
            String promptreenter="Invalid number entered. How many points do you want to bet? You currently have " + points +" points";
            promptLabel.setText(promptreenter);
        }
        else{
            resultPanel.removeAll();
            gamePanel.removeAll();
            decided = 0;
            game.getPlayer().clearHand();
            game.getHouse().clearHand();
            gamePanel.revalidate();
            gamePanel.repaint();
            gamePlay();
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
                    gamePanel.add(images.get(images.size()-1),gridBagConstraints);
                    gbcConfig("dealer",0,20);
                    pointsUpdate();
                    gamePanel.add(dealerPoints,gridBagConstraints);
                }else if (h==player){
                    gbcConfig("player",i,0);
                    gamePanel.add(images.get(images.size()-1),gridBagConstraints);
                    gbcConfig("player",0,20);
                    pointsUpdate();
                    gamePanel.add(playerPoints,gridBagConstraints);
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
