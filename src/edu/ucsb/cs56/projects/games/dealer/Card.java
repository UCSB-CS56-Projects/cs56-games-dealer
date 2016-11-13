package edu.ucsb.cs56.projects.games.dealer;

/**
 * This class represents an individual card in a standard 52 card deck
 *
 *
 * @author Kin Kwan Poon and Eric Xiao
 * @version cs56 F16
 */
public class Card {
    private String rank;
    private String suit;

    /**
     * Construct a new Card with specific rank and suit.
     * 
     * @param rank,suit
     */
    public Card(String rank,String suit) {
	this.rank=rank;
	this.suit=suit;
    }

    /**
     * Overridden toString function to print this Card.
     *
     * @retrun Returns the rank of suit of the Card
     */

    @Override
    public String toString() {
	return rank + " of " + suit;
    }

    /**
     * Determine if this Card and the other Card o are the same.
     *
     * @param o
     * @return Returns true if two Cards are same, false if not.
     */
    @Override
    public boolean equals(Object o){
	if(o==null)
	    return false;
	if(!(o instanceof Card))
	    return false;
	Card c=(Card) o;
	if(this.rank.equals(c.rank)&&this.suit.equals(c.suit))
	    return true;
	else
	    return false;
    }

    /**
     *  Returns a hash code for this Card
     *
     * @return Returns the hashcode of the Card
     */
    @Override
    public int hashCode(){
	return this.rank.hashCode()^this.suit.hashCode();
    }

}
