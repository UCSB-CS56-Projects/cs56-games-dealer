package edu.ucsb.cs56.projects.games.dealer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * GUI version of the game BlackJack
 * @author Eric Xiao, Kin Kwan Poon
 * @version CS56 F16
 *
 */

public class BlackJackGui extends JPanel {
    private JLabel output;

    public BlackJackGui() {
	super(new BorderLayout());
	/*  	    BlackJackGame bjg;
	    boolean shouldRun=true;
	    output = new JLabel("Starting a game of Blackjack");
	    this.add(output);
	    bjg=new BlackJackGame(1,"");

	    do{
		if(bjg.remainingCard()<10){
		    output.setText("There are not enough cards to play!\n"
				+ "The game will now exit");
		    break;
		}
		bjg.start();
		while(!bjg.getPlayer().isBusted()){
		    output.setText("Do you want to hit? (y/n)");
		}
		if(!bjg.getPlayer().isBusted())
		    bjg.houseHit();
		bjg.result();
		output.setText("\nDo you want to continue? (y/n)");
		    shouldRun=false;
		bjg.clearHands();
	    }while(shouldRun);
	    output.setText("Thanks for playing, Bye!");
	*/
	Card c = new Card("King", "Clubs");
	ImageIcon image = new ImageIcon(path(c));
	System.out.println(path(c));
	JLabel label = new JLabel("", image, JLabel.CENTER);
	this.add(label, BorderLayout.CENTER);
	output = new JLabel("Hello");
	this.add(output, BorderLayout.NORTH);
        }

    /**
     * returns a path to the specified card
     * @param c card to find path to
     */
    public String path(Card c) {
	String path = "images/";
	String suit = String.valueOf(c.getSuit().charAt(0)).toLowerCase();
	String rank = String.valueOf(c.getRank().charAt(0));
	if(rank.equals("A"))
	    rank = new String("1");
	else if (rank.equals("J"))
	    rank = new String("11");
	else if (rank.equals("Q"))
	    rank = new String("12");
	else if (rank.equals("K"))
	    rank = new String("13");
	path += suit;
	if(!(rank.equals("11") || rank.equals("12") || rank.equals("13")))
	    path += "0";
	path += rank;
	path += ".png";
	return path;
    }
    
    
 }
    

