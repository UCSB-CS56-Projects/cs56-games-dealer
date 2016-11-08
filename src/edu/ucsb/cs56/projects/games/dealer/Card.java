package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.lang.*;

/**
 * This class represents an individual card in a standard 52 card deck
 *
 *
 */
public class Card {
    private String rank;
    private String suit;

    public Card(String rank,String suit) {
	this.rank=rank;
	this.suit=suit;
    }

    public String toString() {
	return rank + " of " + suit;
    }

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

    public int hashCode(){
	return this.rank.hashCode()^this.suit.hashCode();
    }

}
