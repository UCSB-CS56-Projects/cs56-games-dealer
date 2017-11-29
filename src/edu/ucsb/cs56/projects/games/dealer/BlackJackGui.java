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
    GridBagConstraints gbc;
    JLabel dealerPoints,playerPoints;
    ArrayList<JLabel> images;
    JPanel resultPanel;
    BlackJackGameGui bjgg;
    int decided = 0;
    JTextField playerInput; // Where the user inputs how many hands they want
    int decided = 0;
    int points = 10;
    int betpoint = 0;
    
    /**
     * Costructor of BlackJackGui
     */
    public BlackJackGui(){
        super();
        welcome();
    }
    
    /**
     * Shows the welcom screen and Play button
     */
     public void welcome(){
        JPanel welcome = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel welcomePrompt = new JLabel("Welcome to BlackJack!");
        welcomePrompt.setFont(new Font("Sans Serif",Font.PLAIN,20));

        c.insets=new Insets(250,0,0,0);
        c.gridx=1;
        c.gridy=1;
        welcome.add(welcomePrompt,c);
        
        c.insets=new Insets(20,0,0,0);
        c.gridx=1;
        c.gridy=2;
        String prompt="How many points do you want to bet? You currently have " + points +" points";
        // Label to display prompt for user to input how many hands they want.
        JLabel promptLabel=new JLabel(prompt);
        promptLabel.setLabelFor(playerInput);
        welcome.add(promptLabel,c);
         
        c.insets=new Insets(0,0,0,0);
        c.gridx=1;
        c.gridy=3;
        playerInput=new JTextField(5);
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(playerInput);
        welcome.add(inputPanel,c);
        /**
         * A inner class for play button
         */
        class play implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                betpoint = Integer.parseInt(playerInput.getText());
                if(betpoint < 0){    
                    String prompt2="Invalid number entered. How many points do you want to bet? You currently have " + points +" points";
                    promptLabel.setText(prompt2);
                    return;
                }
                else if(betpoint > 10){
                    String prompt2="Invalid number entered. How many points do you want to bet? You currently have " + points +" points";
                    promptLabel.setText(prompt2);
                    return;
                }
                System.out.println(betpoint);
                welcome.removeAll();
                welcome.repaint();
                revalidate();
                game();
            }
        }
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new play());
        c.insets=new Insets(10,0,0,0);
        c.gridx=1;
        c.gridy=4;
        welcome.add(playButton,c);  
        add(welcome);
    }
    
    
    /**
     * The actual game play in GUI
     */
    public void game(){
        gamePanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        images = new ArrayList<JLabel>();
        JLabel dealerLabel= new JLabel("Dealer: ");
        JLabel playerLabel = new JLabel("Player: ");
        gbcConfig("dealer",0,0);
        gamePanel.add(dealerLabel,gbc);
        
        gbcConfig("player",0,0);
        gamePanel.add(playerLabel, gbc);
        gbc.gridx=0;
        gbc.gridy=0;
        add(gamePanel,gbc);
        revalidate();
        repaint();
        
        bjgg=new BlackJackGameGui();
        dealerPoints= new JLabel(Integer.toString(
                                                  bjgg.getHouse().getHandValue()));
        playerPoints = new JLabel(Integer.toString(
                                                   bjgg.getPlayer().getHandValue()));
        bjgg.start();
        JButton hitButton = new JButton("Hit");
        JButton standButton = new JButton("Stand");
        
        
        /**
         * The inner class for hit button
         */
        class hit implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent arg0) {
                  if(!bjgg.getPlayer().isBusted() && decided == 0){
                        bjgg.playerHit();
                      if(bjgg.getPlayer().isBusted()){
                            points = points - betpoint;
                            setflase(hitButton,standButton);
                            resultDisplay(bjgg.getPlayer().busted(),bjgg.result(true));
                      }
                    }  
                
            }
        }
        
        hitButton.addActionListener(new hit());
        gbc.anchor=GridBagConstraints.LAST_LINE_START;
        gbc.insets=new Insets(0,0,0,10);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gamePanel.add(hitButton,gbc);
        
        /**
         * The inner class of stand button
         */
        class stand implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent arg0){
                
                  if(!bjgg.getPlayer().isBusted() && decided == 0){
                        bjgg.houseHit();
                      if(bjgg.getHouse().isBusted()){
                          points = points + betpoint;
                          standButton.setEnabled(false);
                          hitButton.setEnabled(false);
                            resultDisplay(bjgg.getHouse().busted(),bjgg.result(true));
                      }
                        else if (bjgg.getplayervalue() > bjgg.gethousevalue()){
                            points = points + betpoint;
                            setflase(hitButton,standButton);
                            resultDisplay("Dealer loses",bjgg.result(true));
                        }
                        else{
                            points = points - betpoint;
                            setflase(hitButton,standButton);
                            resultDisplay("Dealer Wins/",bjgg.result(true));
                            
                        }   
                }
            }
        }
        
        standButton.addActionListener(new stand());
        gbc.anchor=GridBagConstraints.LAST_LINE_END;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gamePanel.add(standButton,gbc);
        
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
        GridBagConstraints gbc1 = new GridBagConstraints();
        resultPanel = new JPanel(new GridBagLayout());
        JLabel resultLabel=new JLabel(state1);
        gbc1.fill=GridBagConstraints.VERTICAL;
        gbc1.gridx=0;
        gbc1.gridy=1;
        resultPanel.add(resultLabel,gbc1);
        
        
        
        if(!state2.equals("")){
            resultLabel=new JLabel(state2);
            gbc1.fill=GridBagConstraints.VERTICAL;
            gbc1.gridx=0;
            gbc1.gridy=2;
            resultPanel.add(resultLabel,gbc1);
        }
        
        gbc1.insets=new Insets(20,0,0,0);
        gbc1.gridx=0;
        gbc1.gridy=3;
        
        String prompt="How many points do you want to bet? You currently have " + points+" points";
        // Label to display prompt for user to input how many hands they want.
        JLabel promptLabel=new JLabel(prompt);
        promptLabel.setLabelFor(playerInput);
        resultPanel.add(promptLabel,gbc1);
        /*class New_Game implements ActionListener{
               @Override
               public void actionPerformed(ActionEvent e) {
                    betpoint = Integer.parseInt(playerInput.getText());
                         if(betpoint < 0 || betpoint > 10){
                              String promptreenter="Invalid number entered. You currently have " + points +" points";
                              promptLabel.setText(promptreenter);
                              return;
                          }
                          resultPanel.removeAll();
                          resultPanel.repaint();
                          revalidate();
                          game();
                      }
                  }
        */
        /**
         * The inner class of the continue button
         */
        class Continue implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                betpoint = Integer.parseInt(playerInput.getText());
                
                if (points == 0 && betpoint != 0){
                    resultPanel.removeAll();
                    gamePanel.removeAll();
                    decided = 0;
                    bjgg.getPlayer().clearHand();
                    bjgg.getHouse().clearHand();
                    gamePanel.revalidate();
                    gamePanel.repaint();
                    points = 10;
                    
                    
                    GridBagConstraints c = new GridBagConstraints();
                    JLabel welcomePrompt = new JLabel("You lost all of your points!");
                    welcomePrompt.setFont(new Font("Sans Serif",Font.PLAIN,20));
                    
                    c.insets=new Insets(250,0,0,0);
                    c.gridx=1;
                    c.gridy=1;
                    resultPanel.add(welcomePrompt,c);
                    
                    c.insets=new Insets(20,0,0,0);
                    c.gridx=1;
                    c.gridy=2;
                    String prompt="How many points do you want to bet? You currently have " + points +" points";
                    // Label to display prompt for user to input how many hands they want.
                    JLabel promptLabel=new JLabel(prompt);
                    promptLabel.setLabelFor(playerInput);
                    resultPanel.add(promptLabel,c);
                    
                    
                    c.insets=new Insets(0,0,0,0);
                    c.gridx=1;
                    c.gridy=3;
                    playerInput=new JTextField(5);
                    JPanel inputPanel = new JPanel(new FlowLayout());
                    inputPanel.add(playerInput);
                    resultPanel.add(inputPanel,c);
                
                    JButton playButton = new JButton("New Game");
                    playButton.addActionListener(new New_Game());
                    c.insets=new Insets(30,0,0,0);
                    c.gridx=1;
                    c.gridy=4;
                    resultPanel.add(playButton,c);
                    return;
                }
               
                betpoint = Integer.parseInt(playerInput.getText());
                if(betpoint < 0 || betpoint > points){
                    String prompt2="Invalid number entered. How many points do you want to bet? You currently have " + points +" points";
                    promptLabel.setText(prompt2);
                    return;
                }
                resultPanel.removeAll();
                gamePanel.removeAll();
                decided = 0;
                bjgg.getPlayer().clearHand();
                bjgg.getHouse().clearHand();
                gamePanel.revalidate();
                gamePanel.repaint();
                game();
               
            }
        }
        
        gbc1.insets=new Insets(0,0,0,0);
        gbc1.gridx=0;
        gbc1.gridy=4;
        playerInput=new JTextField(5);
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(playerInput);
        resultPanel.add(inputPanel,gbc1);
        
        
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new Continue());
        gbc1.fill=GridBagConstraints.VERTICAL;
        gbc1.insets=new Insets(0,0,0,0);
        gbc1.gridx=0;
        gbc1.gridy=5;
        resultPanel.add(continueButton,gbc1);
        
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridx=0;
        gbc.gridy=1;
        add(resultPanel,gbc);
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
            gbc.fill=GridBagConstraints.HORIZONTAL;
            gbc.anchor=GridBagConstraints.FIRST_LINE_START;
            gbc.insets=new Insets(50+top,0,0,600-right);
            gbc.gridx=0;
            gbc.gridy=0;
        }else if(hand.equals("player")){
            gbc.anchor= GridBagConstraints.LAST_LINE_START;
            gbc.insets=new Insets(250,0,top,600-right);
            gbc.gridx=0;
            gbc.gridy=0;
        }
    }
    
    public void setflase(JButton a,JButton b){
        a.setEnabled(false);
        b.setEnabled(false);
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
                    gamePanel.add(images.get(images.size()-1),gbc);
                    gbcConfig("dealer",0,20);
                    pointsUpdate();
                    gamePanel.add(dealerPoints,gbc);
                }else if (h==player){
                    gbcConfig("player",i,0);
                    gamePanel.add(images.get(images.size()-1),gbc);
                    gbcConfig("player",0,20);
                    pointsUpdate();
                    gamePanel.add(playerPoints,gbc);
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
