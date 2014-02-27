package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
   This class is used to deal cards to the user..

   @author Jeremy White and Andrew Cooney
   @author Antonio Cantor
   @version cs56 W14 2/26/14
*/

public class dealer{

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
    //Create empty hand
    Hand hand=new Hand();

	do{
	    System.out.println("Do you want the deck shuffled? (y/n)");
	    if (stdin.next().startsWith("y")){
		    deck.shuffle();
        }
	    else{
		    System.out.println("The cards will remain unshuffled");
        }

        System.out.println("How many cards do you want?");
	    if(stdin.hasNextInt()){
		    numCards = stdin.nextInt();
            if (numCards<0||numCards>53){
                System.out.println("An integer from 0 to 52 was not entered");
                System.out.println("Five cards will be dealt");
                numCards = 5;
                hand.addtoHand(numCards, deck);
                hand.printHand();
                break;
            }
            hand.addtoHand(numCards, deck);
        }
        else{
            System.out.println("No number entered, five cards will be dealt");
            numCards = 5;
            hand.addtoHand(numCards, deck);
            hand.printHand();
		    break;
	    }
	    hand.printHand();

	    System.out.println("Do you want to continue? (y/n)");
	    if(stdin.next().startsWith("y"))
		    System.out.println("OK");
	    else
		    break;


	}while(true);

        deck.printDeck();

    System.out.println();
	System.out.println("Goodbye");

    }
}

