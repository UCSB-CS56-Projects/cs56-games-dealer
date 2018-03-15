package edu.ucsb.cs56.projects.games.dealer;

import java.io.Serializable;

/**
 * This class represents an individual card in a standard 52 card deck
 *
 * @author Kin Kwan Poon and Eric Xiao
 * @version cs56 F16
 */
public class Card implements Comparable<Card>, Serializable{
    private Rank rank;
    private Suit suit;
    private boolean hidden;


public enum Rank {
    X(0), Ace(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(13);

    private final int value;
    private Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public enum Suit {
    X(0), Diamonds(1), Clubs(2), Hearts(3), Spades(4);

    private final int value;
    private Suit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

    /**
     * Construct a new Card with specific rank and suit.
     * 
     * @param rankValue
     * holds int value of the rank
     * @param suit
     * holds string name value of the suit
     */
    public Card(Rank rank, Suit suit){
        this.rank=rank;
        this.suit=suit;
	this.hidden=false;
    }

    /**
     * Construct a new Card with specific rank and suit.
     * 
     * @param rank
     * holds string name value of the rank
     * @param suit
     * holds string name value of the suit
     */
    public Card(String rank,String suit) {
        if (rank == "Ace") {
            this.rank = Rank.Ace;
        } else if (rank == "2") {
            this.rank = Rank.Two;
        } else if (rank == "3") {
            this.rank = Rank.Three;
        } else if (rank == "4") {
            this.rank = Rank.Four;
        } else if (rank == "5") {
            this.rank = Rank.Five;
        } else if (rank == "6") {
            this.rank = Rank.Six;
        } else if (rank == "7") {
            this.rank = Rank.Seven;
        } else if (rank == "8") {
            this.rank = Rank.Eight;
        } else if (rank == "9") {
            this.rank = Rank.Nine;
        } else if (rank == "10") {
            this.rank = Rank.Ten;
        } else if (rank == "Jack") {
            this.rank = Rank.Jack;
        } else if (rank == "Queen") {
            this.rank = Rank.Queen;
        } else if (rank == "King") {
            this.rank = Rank.King;
        } else {
	    this.rank = Rank.X;
	}

	if (suit == "Diamonds") {
	    this.suit = Suit.Diamonds;
	} else if (suit == "Clubs") {
            this.suit = Suit.Clubs;
        } else if (suit == "Hearts") {
            this.suit = Suit.Hearts;
        } else if (suit == "Spades") {
            this.suit = Suit.Spades;
        } else {
	    this.suit = Suit.X;
	}

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

	if (rank == "1" || rank == "14") {
            this.rank = Rank.Ace;
        } else if (rank == "2") {
            this.rank = Rank.Two;
	} else if (rank == "3") {
            this.rank = Rank.Three;
        } else if (rank == "4") {
            this.rank = Rank.Four;
        } else if (rank == "5") {
            this.rank = Rank.Five;
        } else if (rank == "6") {
            this.rank = Rank.Six;
        } else if (rank == "7") {
            this.rank = Rank.Seven;
        } else if (rank == "8") {
            this.rank = Rank.Eight;
        } else if (rank == "9") {
            this.rank = Rank.Nine;
        } else if (rank == "10") {
            this.rank = Rank.Ten;
        } else if (rank == "11") {
            this.rank = Rank.Jack;
        } else if (rank == "12") {
            this.rank = Rank.Queen;
        } else if (rank == "13") {
            this.rank = Rank.King;
        } else {
            this.rank = Rank.X;
        }

        if (suit == "Diamonds") {
            this.suit = Suit.Diamonds;
        } else if (suit == "Clubs") {
            this.suit = Suit.Clubs;
        } else if (suit == "Hearts") {
            this.suit = Suit.Hearts;
        } else if (suit == "Spades") {
            this.suit = Suit.Spades;
        } else {
            this.suit = Suit.X;
        }

        hidden=false;

	}



    /**
     * @return the rank of the card
     */
    public Rank getRank() {
        if(!hidden)
                return this.rank;
        else
                return Rank.X;
    }

    /**
     * @return the suit of the card
     */
    public Suit getSuit() {
        if(!hidden)
                return this.suit;
        else
                return Suit.X;
    }


    /**
     * Overridden toString function to print this Card.
     *
     * @return Returns the rank of suit of the Card
     */

    @Override
    public String toString() {
    	if(!hidden) {
    		return rank + " of " + suit;
    	} else
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
		return suit.getValue();
	}
	
	/**
	 * @return the value of rank for comparable
	 */
	public Integer rankValue(){
		switch(this.rank){
		case Ace:
			return 14;
		default:
			return this.rank.getValue();
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
				case Ace:
					return 1;
				case King:
					return 10;
				case Queen:
					return 10;
				case Jack:
					return 10;
                                default:
					return rank.getValue();
				}
			}
		}
		else{ 
                    return this.rankValue();
                }
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
