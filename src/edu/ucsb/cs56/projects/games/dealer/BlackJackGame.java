package edu.ucsb.cs56.projects.games.dealer;

/**
 * This class contains the main component(procedure) of a black jack game.
 * @author Kin Kwan Poon
 * @version UCSB, cs56, F16
 */
public class BlackJackGame {
	protected BlackJackHouse house;
	protected BlackJackPlayer player;
	protected BlackJackDeck d;
	/**
	 * Constructor of BlackJackGame 
	 */
	public BlackJackGame(){
		house=new BlackJackHouse();
		player=new BlackJackPlayer("Player");
		d=new BlackJackDeck();
		d.shuffle();
	}
	/**
	 * 
	 * @param numOfDecks
	 * Constructor of BlackJackGame with number of Decks
	 * @param playerName
	 * Constructor of BlackJackGame with player's name
	 */
	public BlackJackGame(int numOfDecks,String playerName){
		house=new BlackJackHouse();
		player=new BlackJackPlayer(playerName);
		d=new BlackJackDeck(numOfDecks);
		d.shuffle();
	}
	
	/**
	 * The beginning of the game.
	 */
	public void start() {
		for(int i=0;i<2;i++){
			house.addtoHand(1, d);
			if(i==0)
				house.getHand().get(0).hide();
			System.out.println(house.toString());
			sleep(500);
			playerHit();
		}
	}
	
	/**
	 * Draw a card to player 
	 */
	public void playerHit(){
		player.addtoHand(1, d);
		System.out.println(player.toString());
		sleep(500);
		if(player.isBusted())
			player.busted();
	}
	
	/**
	 * Draw a card to dealer.
	 */
	public void houseHit(){
		house.getHand().get(0).showHidden();
		SoundEffect.playSound("deal", 1,4);
		System.out.println(house.toString());
		sleep(500);
		while(house.isHitting()&&!house.isBusted()){
			house.addtoHand(1, d);
			System.out.println(house.toString());
			sleep(500);
		}
		if(house.isBusted())
			house.busted();
	}
	
	/**
	 * Remove all the card on "Table"
	 */
	public void clearHands(){
		house.clearHand();
		player.clearHand();
	}
	
	/**
	 * Getter of player
	 * @return player's hand
	 */
	public BlackJackPlayer getPlayer(){
		return player;
	}

	/**
	 * Getter of dealer
	 * @return dealer's hand
	 */
	public BlackJackHouse getHouse(){
		return house;
	}
	
	/**
	 * 
	 * @return the number of remaining cards
	 */
	public int remainingCard(){
		return d.getDeck().size();
	}

	/**
	 * To determine the player wins or loses or pushes.
	 * @param points
	 holds the points held by player
	 */
	public void result(int points){
		if((house.isBusted()||
				player.getHandValue()>house.getHandValue())
           &&!player.isBusted()){
			player.win();
            player.gain_score(points);
        }
		else if(player.isBusted()||
				!house.isBusted()&&
                player.getHandValue()<house.getHandValue()){
			player.lose();
            player.lose_score(points);
        }
		else
			player.push();
	}
	
	/**
	 *  
	 * @param time
	 * Adds a pause to make the game look real.
	 */
	public void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
