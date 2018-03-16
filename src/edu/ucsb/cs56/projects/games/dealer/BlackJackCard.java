package edu.ucsb.cs56.projects.games.dealer;

/**
 * This class contains the Card subclass BlackJackCard.
 * @author Ernesto Sandoval, Adil Truong
 * @version UCSB, cs56, W18
 */


public class BlackJackCard extends Card {
        
        
    /*
     * Construct a new Card with specific rank and suit.
     * 
     * @param rank
     * holds Rank value of the rank
     * @param suit
     * holds Suit value of the suit
     */
    
    public BlackJackCard(Rank rank, Suit suit) {
        super(rank, suit);    
    }

    public Integer rankValue() {
        if(hidden) {
            return 0;
        } else {
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
}
