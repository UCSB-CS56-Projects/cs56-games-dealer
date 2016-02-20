package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
   This class is used to help the Panel create the string of random cards that it displays.

   @author Kelly Bielaski and Tristan Starck
   @version cs56 W16 2/15/16
*/

public class DealerPanelHelper{

    /**
       Main function to interact with the gui,
       shuffle the deck, and print cards

       @param args Command line arguments entered by user
    */

    private int numHands;
    private int[] playerInputArrayInts;
    private String shuffle;
    private Deck deck;
    private Hand[] hands;
    public DealerPanelHelper(int numHands, int[] playerInputArrayInts, String shuffle, Deck deck, Hand[] hands){
	this.numHands=numHands;
	this.playerInputArrayInts=playerInputArrayInts;
	this.shuffle=shuffle;
	this.deck=deck;
	this.hands=hands;
    }
    
    public String[] playerCardString(){
	
	if(playerInputArrayInts==null){
	    return "player inputs are null";
	}
	if(shuffle==null){
	    return "shuffle is null";
	}
	if(deck==null){
	    return "deck is null";
	}
	//make the size of the array based on how many times we shuffle	   
	if(shuffle=="shuffle after every set of cards is dealt"){
	    String[] displayedCards=new String[playerInputArrayInts.size()*2+2];
	}
	if(shuffle=="shuffle once before dealing"){
	    String[] displayedCards=new String[playerInputArrayInts.size()+3];
	}
	else{
	    String[] displayedCards=new String[playerInputArrayInts.size()+2];
	}
	//for every element in displayedCards, initialize it to NULL
	for(int i=0; i<displayedCards.size(); i++){
	    displayedCards[i]="";
	}
	//place holder to where to insert the statement
	int nextSpot=0;
	if (shuffle=="shuffle once before dealing" || shuffle == "shuffle after every set of cards is dealt"){
	    deck.shuffle();
	    displayedCards[nextSpot]="deck shuffled\n";
	    nextSpot++;
	}
	if(hands==null)
	    hands=new Hand[numHands];
	
	for(int i=0; i<numHands;i++){
	    if (playerInputArrayInts[i]<0||playerInputArrayInts[i]>53)
		playerInputArrayInts[i]=5;
	    if(hands[i]==null){
		hands[i]= new Hand();
	    }

	    hands[i].addtoHand(playerInputArrayInts[i], deck);
	    displayedCards[nextSpot]="\n" +"Player "+ (i+1)+ "/" +numHands+ ": "+ hands[i]+"\n";
	    nextSpot++;
	    if(shuffle=="shuffle after every set of cards is dealt"){
		deck.shuffle();
		displayedCards[nextSpot]="\ndeck shuffled\n";
		nextSpot++;
	    }
	}
	displayedCards[nextSpot]="\n\n"+deck;
	if(deck.getDeck().size()<=0){
	    displayedCards[nextSpot]="\nNo more cards to distribute. Click Submit/Reset button to reset the deck and play again.";
	}
	    
	return displayedCards;	
    }

    public Deck getDeck(){
	return deck;
    }

    public Hand[] getHands(){
	return hands;
    }
    
}

