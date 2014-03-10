package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.lang.*;
import java.security.*;

/**
 This class is to provide functions to a standard 52 cards deck such as shuffling.

 @author Jeremy White and Andrew Cooney
 @author Antonio Cantor
 @version cs56 W14 2/27/14
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
     This function is used to print the deck.
     */
    public void printDeck(){
        System.out.println("Your deck:");

        if (deck.size()==1){
            System.out.println(""+deck.get(0));
        }
		else if (deck.size()==0){
			System.out.println("NO MORE CARDS IN THE DECK");}

        else{
            for (int i = 0; i<deck.size(); i++){
                if (i%5==0){
                    System.out.println();}
                if(i == deck.size()-1){
                    System.out.println(""+deck.get(i));
                }
                else{
                    System.out.print("" + deck.get(i) + ", ");
                }
            }
        }
    }

    /**
     This function is gain access to the deck array.
     */
    public ArrayList<String> getDeck(){
        return deck;
    }

    /**
     This function is gain access to the deck array.

     @param differentDeck A new deck to be assigned for deck
     */
    public void setDeck(ArrayList<String> differentDeck){
        deck = differentDeck;
    }


}
