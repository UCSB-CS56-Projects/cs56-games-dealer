package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.lang.*;
import java.security.*;

/**
 This class is to provide functions to a standard 52 cards deck such as shuffling.

 @author Jeremy White and Andrew Cooney
 @author Antonio Cantor
 @version cs56 W14 3/13/14
 */

public class Deck {

    private ArrayList<String> deck;


    /**
    Deck constructor to make deck arraylist contained an unshuffle deck.
     */

    public Deck(){
        deck = new ArrayList<String>(Arrays.asList(new String[]{
                "2 of Clubs", "3 of Clubs", "4 of Clubs", "5 of Clubs",
                "6 of Clubs", "7 of Clubs", "8 of Clubs", "9 of Clubs", "10 of Clubs",
                "Jack of Clubs", "Queen of Clubs", "King of Clubs", "Ace of Clubs",

                "2 of Diamonds", "3 of Diamonds", "4 of Diamonds", "5 of Diamonds",
                "6 of Diamonds", "7 of Diamonds", "8 of Diamonds", "9 of Diamonds", "10 of Diamonds",
                "Jack of Diamonds", "Queen of Diamonds", "King of Diamonds", "Ace of Diamonds",

                "2 of Hearts", "3 of Hearts", "4 of Hearts", "5 of Hearts",
                "6 of Hearts", "7 of Hearts", "8 of Hearts", "9 of Hearts", "10 of Hearts",
                "Jack of Hearts", "Queen of Hearts", "King of Hearts", "Ace of Hearts",

                "2 of Spades", "3 of Spades", "4 of Spades", "5 of Spades",
                "6 of Spades", "7 of Spades", "8 of Spades", "9 of Spades", "10 of Spades",
                "Jack of Spaces", "Queen of Spades", "King of Spades", "Ace of Spades"}));
    }

    /**
     This function is used to shuffle the deck
     by using the shuffle function from the Collections
     class and a SecureRandom seed. It randomly permutes deck using
     the seed.
     */

    public void shuffle(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[64];
        random.nextBytes(bytes);
        Collections.shuffle(deck,random);
    }

    /**
     Overridden toString function to print cards remaining in the deck.

	@return deckResult a string of the cards remaining in the deck
     */
	
	public String toString(){
		String deckResult="Your deck"+"\n";
        if (deck.size()==1){
            deckResult+=deck.get(0);
        }
		
        else{	
            for (int i = 0; i<deck.size(); i++){
                if (i%5==0){
                    deckResult+="\n";
				}
                if(i == deck.size()-1){
                    deckResult+=deck.get(i);
                }
                else{
                    deckResult+=deck.get(i) + ", ";
                }
            }
			if (deck.size()==0){
				deckResult="NO MORE CARDS IN THE DECK";
			}
        }
		return 	deckResult;
	}


    /**
	 Removes cards from the deck by xCards

     @param xCards the number of cards user inputted
     */

	public void removeCard(int xCards){
		int i = 0;
        for(Iterator<String> iterator = deck.iterator(); iterator.hasNext();) {
            String card = iterator.next();
            if(i<xCards){
                iterator.remove();
				i++;
            }
		}
	}



    /**
     This function is gain access to the deck array.

	 @return deck the arraylist of deck
     */

    public ArrayList<String> getDeck(){
        return deck;
    }

}
