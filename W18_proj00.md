## Partners: Adil Truong, Ernesto Sandoval

a. Dealer has two programs; one to draw cards from a deck and list out the specific cards, and another to play Blackjack, a game that makes the user draw to compete with the dealer up to 21.

b. As a dealer, I can choose to shuffle a deck of cards, and deal any number of them to up to fifty two players, either through the command line or GUI. As a dealer, you know which cards remain in the deck and what each player drew. 

As a blackjack player, I can play a game of Blackjack with a dealer by betting points, either through the command line or GUI. You can hit or stand every turn. 

As a user, I can choose between different games in the main menu, where when chosen, create a new window of that game in GUI or lines of code in the regular run.

c. The software runs on both GUI and command line. The program displays options to shuffle and disperse cards in Dealer, and gives options to bet, hit and stand in Blackjack.

d. As a game user I can press a "Quit" button or type quit at the command line so that I can quit the current game when done.

As a dealer I can deal different decks, such as a 54 card deck that includes two jokers, so that different games can be made.

As a game user I can press a "Blackjack" button or type 3 in the first query so that I can play a game of blackjack. 

As a Blackjack player, I can make the game multiplayer to play with

e. The README.md clearly describes what the project is intended to do. It explains how to run the application and how the application works.Something that can be added is an explanation of how the blackjack class is implemented using the dealer class. 

f. This project is based on Ant. All of its targets have clear descriptions. 
The README.md says that the console could be run with gradle. When we try this on CSIL we get the following message:

`bash: gradle: command not found...`

`Install package 'gradle' to provide command 'gradle'? [N/y] `

g. There are a total of 1750 points that can be earned by resolving issues. All the issues are clear. 

h. When playing the Dealer game, the Dealer class should be allowed to deal zero cards. This is an issue that can be resolved. 

Deck has a draw method that adds some number of cards from to a hand. Hand has a addToHand method that adds some number of cards from a deck. These two methods appear to do the same thing. They may be redundant.


i. Card is a class that represents a playing card with some rank and suit. Hand and Deck are classes that have arrays of Cards. Deck has a draw method that adds some number of cards from to a hand. Hand has a addToHand method that adds some number of cards from a deck. 

The Dealer class creates an array of Hands representing multiple players and one deck. It uses the methods of the Deck and Hand class to deal. 

BlackjackPlayer is a class with a Hand, name, and value. It has methods to hit, stand, and keep score. BlackJackHouse is a subclass of this class. BlackJackHouse uses these classes to create a game of BlackJack between a BlackJackPlayer and BlackJackHouse. 

The code is organized well. The methods are clear. It is obvious how the classes relate and interact with each other. 

To quickly explain this program to someone, we would explain how the Dealer class works. The Dealer class uses Hand and Deck objects. It uses methods like shuffle() and addToHand(), so they would have an idea of what Hand, Deck, and Dealer is supposed to do. 


j. There are JUnit tests for Card and Hand classes. Some of the Deck classes' methods are tested inside of HandTest.java. It may be better to have a seperate test file for the Deck class. 
