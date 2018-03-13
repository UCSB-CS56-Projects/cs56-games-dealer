package edu.ucsb.cs56.projects.games.dealer;

import java.io.Serializable;

/**
 * This class represents an individual card in a standard 52 card deck
 *
 * @author Kin Kwan Poon and Eric Xiao
 * @version cs56 F16
 */
public class Card implements Comparable<Card>, Serializable{
    private String rank;
    private String suit;
    private boolean hidden;

    /**
     * Construct a new Card with specific rank and suit.
     * 
     * @param rank
     * holds string name value of the rank
     * @param suit
     * holds string name value of the suit
     */
    public Card(String rank,String suit) {
    	this.rank=rank;
    	this.suit=suit;
    	hidden=false;
    }

    /**
     * Construct a new Card with specific rank and suit.
     * 
     * @param rankValue
     * holds int value of the rank
     * @param suit
     * holds string name value of the suit
     */    
    public Card(int rankValue, String suit){
        String rank = String.valueOf(rankValue);
        this.rank=rank;
        this.suit=suit;
    }

    /**
     * @return the rank of the card
     */
    public String getRank() {
    	if(!hidden)
    		return this.rank;
    	else
    		return "X";
    }

    /**
     * @return the suit of the card
     */
    public String getSuit() {
    	if(!hidden)
    		return this.suit;
    	else
    		return "X";
    }
    
    /**
     * Overridden toString function to print this Card.
     *
     * @return Returns the rank of suit of the Card
     */

    @Override
    public String toString() {
    	if(!hidden)
    		return rank + " of " + suit;
    	else
    		return "X of X";
    }

    /**
     * Determine if this Card and the other Card o are the same.
     * @param o
     * holds the status of object o where determines the equality of card values.
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

    /**
     * Compare this card to card c.
     * @return 1,0,-1 if this card is greater than / equals / less than the Card c.
     */
	@Override
	public int compareTo(Card c) {
		int rank=this.rankValue().compareTo(c.rankValue());
		int suit=this.suitVaule().compareTo(c.suitVaule());
		if(rank>0)
			return 1;
		else if(rank<0)
			return -1;
		else if(suit>0)
			return 1;
		else if(suit==0&&rank==0)
			return 0;
		else
			return -1;
	}
	
	/**
	 * @return the value of suit for comparable
	 */
	public Integer suitVaule(){
		switch(this.suit){
		case "Spades":
			return 4;
		case "Hearts":
			return 3;
		case "Clubs":
			return 2;
		case "Diamonds":
			return 1;
		default:
			System.out.println("Wrong suit deteced");
			return 0;
		}
	}
	
	/**
	 * @return the value of rank for comparable
	 */
	public Integer rankValue(){
		switch(this.rank){
		case "Ace":
			return 14;
		case "King":
			return 13;
		case "Queen":
			return 12;
		case "Jack":
			return 11;
		default:
			return Integer.parseInt(this.rank);
		}
	}

	/**
	 * For use in the game black jack only
	 * @param blackJack Is the game black jack or not
	 * @return the value of rank for comparable.
	 */
	public Integer rankValue(boolean blackJack){
		if(blackJack){
			if(hidden){
				return 0;
			}else{
				switch(this.rank){
				case "Ace":
					return 1;
				case "King":
					return 10;
				case "Queen":
					return 10;
				case "Jack":
					return 10;
				default:
					return Integer.parseInt(this.rank);
				}
			}
		}else 
			return rankValue();
	}
	
	/**
	 * Hide the card
	 */
	public void hide(){
		hidden=true;
	}
	
	/**
	 * Show the card
	 */
	public void showHidden(){
		hidden=false;
	}
}
