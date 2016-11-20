package edu.ucsb.cs56.projects.games.dealer;

import java.util.*;

/**
 *  Plays a game of Blackjack
 *  @author Eric Xiao, Kin Kwan Poon
 *  @version CS56 Fall 2016
 */

public class Blackjack {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private int points;

    public void start() {
	playerHand.addtoHand(2, deck);
	dealerHand.addtoHand(2, deck);
    }
    
    /*
     * adds a card to the specified hand
     * @param h hand to add card to
     */
    public void hit(Hand h) {
	h.addtoHand(1, deck);	
    }
    /*
     * returns the number of points in a hand
     * @param h hand to calculate points for
     */ 
    public int points(Hand h) {
	int points = 0, numAce = 0;
	for(Card c : h.getHand()) {
	    String card = c.getRank();
	    if(card == "Ace") {
		points += 11;
		numAce++;
	    } else if (card == "2") {
		points += 2;
	    } else if (card == "3") {
		points += 3;
	    } else if (card == "4") {
		points += 4;
	    } else if (card == "5") {
		points += 5;
	    } else if (card == "6") {
		points += 6;
	    } else if (card == "7") {
		points += 7;
	    } else if (card == "8") {
		points += 8;
	    } else if (card == "9") {
		points += 9;
	    } else
		points += 10;	       
	}
	if(numAce > 0 && points > 21) {
	    while(points > 21) {
		numAce--;
		points -= 10;
	    }
	}
	return points;
    }

}
