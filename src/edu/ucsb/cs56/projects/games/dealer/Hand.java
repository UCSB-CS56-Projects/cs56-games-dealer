package edu.ucsb.cs56.projects.games.dealer;
import java.util.*;
import java.lang.*;

public class Hand{
    private ArrayList hand;
    private int handSize=0;

    /**
     Hand constructor to make hand arraylist be an empty hand
     and the handSize be 0.
     */

    Hand(){
        hand = new ArrayList();
        handSize=0;
    }

    /**
     This function adds cards to the hand based on the
     user input. After the cards to the hand, it removes
     the cards from the deck that were added to the hand

     @param xCards the number of cards user inputted
     @param D .the Deck deck used in the dealer class
     */

    public void addtoHand(int xCards, Deck D){
        ArrayList copy = new ArrayList(D.getDeck());

        for (int i=0;i<xCards;i++){
            hand.add(copy.get(i));
            handSize++;
        }

        for(Iterator<String> iterator = copy.iterator(); iterator.hasNext();) {
            String card = iterator.next();
            if(hand.contains(card)){
                iterator.remove();
            }
        }

        D.setDeck(copy);
    }


    /**
     This function is used to print the cards in the hand
     */
    public void printHand(){
        System.out.println("Your hand:");

        if(handSize == 1){
            System.out.println("" + hand.get(0));
        }
        else{
            for (int i = 0; i<handSize; i++){
				if (i%5==0){
					System.out.println();}
                if(i == handSize-1){
                    System.out.println(""+hand.get(i));
                }
                else{
                    System.out.print("" + hand.get(i) + ", ");
                }
            }
        }
    }



}
