package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.lang.*;

/**
 This class is to provide functions to a standard 52 cards deck such as shuffling.

 @author Jeremy White and Andrew Cooney
 @author Antonio Cantor
 @version cs56 W14 2/23/14
 */

public class Deck {

    private String[] deck;

    /**
    Deck constructor to make deck array contained an unshuffle deck.
     */

    public Deck(){
        deck = new String[]{
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
                "Jack of Spaces", "Queen of Spades", "King of Spades", "Ace of Spades", };
    }

    /**
     This function is used to shuffle the deck
     A random number is used to swap values at
     array indices repeeatedly.

     @param cards The deck of cards to be shuffled
     */
    public static void shuffle(String[] cards){
        Random rand = new Random();
        for (int i=0; i<500; i++){
            int x = Math.abs(rand.nextInt())%52;
            int y = Math.abs(rand.nextInt())%52;
            String hold = cards[x];
            cards[x] = cards[y];
            cards[y] = hold;
        }
    }

    /**
     This function is used to print the deck
     based off of the desired number of cards the user inputs

     @param cards The deck of cards to be printed
     @param xCards The number of cards to be printed
     */
    public static void printDeck(String[] cards, int xCards){
        System.out.println("Your hand:");

        if(xCards==1){
            System.out.println("" + cards[0]);
        }
        else{
            for (int i=0; i<xCards; i++){
                if(i==xCards-1){
                    System.out.println(""+cards[i]);
                }
                else{
                    System.out.print("" + cards[i] + ", ");
                }
            }
        }
        System.out.println();
    }

    /**
     This function is gain access to the deck array.
     */
    public String[] getDeck(){
        return deck;
    }

}
