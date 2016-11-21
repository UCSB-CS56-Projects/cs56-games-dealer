package edu.ucsb.cs56.projects.games.dealer;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 *
 *
 *
 */

public class BlackJackGui extends JPanel{
	BlackJackGame bjg;
	JPanel game;
	GridBagConstraints gbc;
	
	public BlackJackGui(){
		super();
		welcome();
	}
	
	public void welcome(){
		JPanel welcome = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel welcomePrompt = new JLabel("Welcome to BlackJack!");
		welcomePrompt.setFont(new Font("Sans Serif",Font.PLAIN,20));
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.CENTER;
		c.insets=new Insets(250,0,0,0);
		c.gridx=1;
		c.gridy=1;
		welcome.add(welcomePrompt,c);
		
		class play implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				welcome.removeAll();
				welcome.repaint();
				revalidate();
				game();
				
			}
			
		}
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new play());
		c.insets=new Insets(80,0,0,0);
		c.gridx=1;
		c.gridy=2;
		welcome.add(playButton,c);
		add(welcome);
	}
	public void game(){
		
		BlackJackGameGui bjgg;
		game = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints();
		JLabel dealerLabel= new JLabel("Dealer: ");
		JLabel playerLabel = new JLabel("Player: ");
		gbcConfig("dealer",0);
		game.add(dealerLabel,gbc);
		
		gbcConfig("player",0);
		game.add(playerLabel, gbc);
		add(game);
		revalidate();
		repaint();
		
		bjgg=new BlackJackGameGui();
		bjgg.start();
		
		class hit implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bjgg.playerHit();
				game.revalidate();
				game.repaint();
			}
		}
		
		JButton hitButton = new JButton("Hit");
		hitButton.addActionListener(new hit());
		gbc.anchor=GridBagConstraints.PAGE_END;
		gbc.insets=new Insets(0,0,0,199);
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.gridwidth=1;
		game.add(hitButton,gbc);
		
		
	
	}
	
	public void gbcConfig(String hand, int pos){
		if(hand.equals("dealer")){
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.anchor=GridBagConstraints.FIRST_LINE_START;
			gbc.insets=new Insets(50,0,0,600-pos);
			gbc.gridx=0;
			gbc.gridy=0;
		}else if(hand.equals("player")){
			gbc.anchor= GridBagConstraints.LAST_LINE_START;
			gbc.insets=new Insets(100,0,0,600-pos);
			gbc.gridx=0;
			gbc.gridy=3;
		}
	}
	
	class BlackJackGameGui extends BlackJackGame{
		
		public BlackJackGameGui(){
			super(4,"Player");
		}
		
		@Override
		public void start(){
			for(int i=0;i<2;i++){
				this.getHouse().addtoHand(1, d);
				if(i==0)
					this.getHouse().getHand().get(0).hide();
				display(house);
				playerHit();
			}
			
		}
		
		@Override
		public void playerHit(){
			player.addtoHand(1, d);
			display(player);
			if(player.isBusted())
				player.busted();
		}
		
		
		public void display(Hand h){
			int i=200;
			System.out.println(h.toString());
			for(Card c:h.getHand()){
				ImageIcon image = new ImageIcon(path(c));
				JLabel label = new JLabel("",image,JLabel.CENTER);
				if(h==house)
					gbcConfig("dealer",i);
				else if (h==player)
					gbcConfig("player",i);
				game.add(label,gbc);
				game.revalidate();
				game.repaint();
				
				i+=200;
			}
			sleep(500);
		}
		
		
	    /**
	     * returns a path to the specified card
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
