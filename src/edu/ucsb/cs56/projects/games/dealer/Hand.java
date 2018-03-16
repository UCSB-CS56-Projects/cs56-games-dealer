package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;

/**
 *   This class is used to add cards to the hand.
 *
 * @author Kin Kwan Poon and Eric Xiao
 * @author Antonio Cantor
 * @version cs56 F16
 */

public class Hand {
    protected ArrayList<Card> hand;

    /**
     * Hand constructor to make hand arraylist be an empty hand
     * and the handSize be 0.
     */

    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Return the ith card in the hand
     *
     * @param i index of card to return
     */
    public Card get(int i) {
        return hand.get(i);
    }

    /**
     * Add a particular card to the hand
     *
     * @param c the card to draw
     */

     public void addtoHand(Card c) {
         hand.add(c);
         SoundEffect.playSound("deal",1,4);
     }

    /**
     * Add a number of cards to the hand
     *
     * @param numCards number of cards to draw
     * @param d the deck to draw from
     */

     public void addtoHand(int numCards, Deck d) {
         d.draw(numCards, this);
         SoundEffect.playSound("deal",1,4);
     }

    /**
     * Remove a particular card from the hand
     *
     * @param c the card to remove
     */

     public void remove(Card c) {
         hand.remove(c);
     }


    /**
     * Return the number of cards in hand
     */

    public int size() {
        return hand.size();
    }


    /**
     * Remove the last card from the hand
     */
     
     public void removeLastCard(){
    	 hand.remove(this.size()-1);
     }

    /**
     * Returns a random card from hand
     * @return Card 
     */
    public Card obtainRandomCard() {
        int randomNum = (int)(Math.random() * hand.size());
        Card card = hand.get(randomNum);
        return card;
    }

    /**
     * @return an ArrayList containing each card in the hand
     */
    public ArrayList<Card> getHand() {
	return hand;
    }

    /**
     * Overridden toString function to print cards in the hand.
     *
     * @return handResult a string of the cards in the hand
     */

    @Override
    public String toString() {
        String result = "Your hand:\n";
	for(int i = 0; i < this.size() - 1; i++) {
	    result += hand.get(i) + ", ";
	}
	result += hand.get(this.size() - 1);
	return result;
    }
}
