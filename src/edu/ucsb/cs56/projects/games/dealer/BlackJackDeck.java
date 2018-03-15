package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;

/**
 * This class contains the Card subclass BlackJackCard.
 * @author Ernesto Sandoval, Adil Truong
 * @version UCSB, cs56, W18
 */

public class BlackJackDeck extends Deck {
    /**
     * Deck constructor to make deck arraylist contained an unshuffle deck.
     */

    public BlackJackDeck() {
	deck = new ArrayList<Card>();
	for(int i=0;i<4;i++) {
	    for(int j=0;j<13;j++){
		deck.add(new BlackJackCard(ranks[j],suits[i]));
	    }
	}
    }

    /**
     * Deck Constructor to make numOfDecks Decks into one Deck 
     * for games that require more than one deck. 
     * @param numOfDecks
     holds integer value of numbers of decks at play
     */
    public BlackJackDeck(int numOfDecks) {
    	deck = new ArrayList<Card>();
    	for (int k=0;k<numOfDecks;k++){
    		for(int i=0;i<4;i++) {
    			for(int j=0;j<13;j++){
    				deck.add(new BlackJackCard(ranks[j],suits[i]));
    			}
    		}
        }
    }

}

