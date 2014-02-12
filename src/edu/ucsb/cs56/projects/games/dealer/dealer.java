package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
   This class is used to shuffle a standard 52 card deck
   then deal out five cards to the user.

   @author Jeremy White and Andrew Cooney
   @version cs56 W12 3/16/12
*/

public class dealer{

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
       
	for (int i=0; i<xCards; i++){
	    System.out.print("" + cards[i] + " ");
	}
	System.out.println();
    }

    /**
       Main function to interact with the user,
       shuffle the deck, and print cards

       @param args Command line arguments entered by user
    */
    public static void main(String[] args) throws IOException{
	
	Scanner stdin = new Scanner(System.in);
	int numCards;
	boolean keepDealing = true;
	//Create unshuffled deck	
	String[] deck = { "2C", "3C", "4C", "5C", "6C", "7C", "8C", 
		      "9C", "TC", "JC", "QC", "KC", "AC",
		      "2D", "3D", "4D", "5D", "6D", "7D", "8D", 
		      "9D", "TD", "JD", "QD", "KD", "AD",
		      "2H", "3H", "4H", "5H", "6H", "7H", "8H", 
		      "9H", "TH", "JH", "QH", "KH", "AH",
		      "2S", "3S", "4S", "5S", "6S", "7S", "8S", 
		      "9S", "TS", "JS", "QS", "KS", "AS", };


	do{   
	    System.out.println("Do you want the deck shuffled? (y/n)");
	    if (stdin.next().startsWith("y"))
		shuffle(deck);
	    else
		System.out.println("The cards will remain unshuffled");
	   
	    System.out.println("How many cards do you want?");
	    if(stdin.hasNextInt())
		numCards = stdin.nextInt();
	    else{
		System.out.println("No number entered, five cards will be dealt");
		numCards = 5;
		printDeck(deck, numCards);
		break;
	    }
	   
	    printDeck(deck, numCards);
	 
	    System.out.println("Do you want to continue? (y/n)");
	    if(stdin.next().startsWith("y"))
		System.out.println("OK");
	    else 
		break;
	   
	    
	}while(true);
	
	System.out.println("Goodbye");
	    
    }
}
