package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
   This class is used to deal cards to the user..

   @author Jeremy White and Andrew Cooney
   @author Antonio Cantor
   @version cs56 W14 2/23/14
*/

public class dealer extends Deck{

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
    Deck deck=new Deck();


	do{   
	    System.out.println("Do you want the deck shuffled? (y/n)");
	    if (stdin.next().startsWith("y"))
		shuffle(deck.getDeck());
	    else
		System.out.println("The cards will remain unshuffled");
	   
	    System.out.println("How many cards do you want?");
	    if(stdin.hasNextInt())
		numCards = stdin.nextInt();
	    else{
		System.out.println("No number entered, five cards will be dealt");
		numCards = 5;
		printDeck(deck.getDeck(), numCards);
		break;
	    }
	   
	    printDeck(deck.getDeck(), numCards);
	 
	    System.out.println("Do you want to continue? (y/n)");
	    if(stdin.next().startsWith("y"))
		System.out.println("OK");
	    else 
		break;

	    
	}while(true);

	System.out.println("Goodbye");
	    
    }
}
