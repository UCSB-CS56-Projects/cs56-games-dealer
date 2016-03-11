cs56-games-dealer
=================

project history
===============
```
 W14 | jcneally 4pm | acantor | Application to shuffle a deck of 52 playing cards and deal them to the use

 W16 | ttstarck kellybielaski 5pm | Added GUI and multiple hand functionality
```

What is this?
=============
It is an application to shuffle a deck of 52 playing cards and deal them to the user using command lines. 


How To Run
==========

In the directory of where the build.xml is located 


    Type `ant run` - runs terminal
    Type 'ant runGUI' - runs GUI


 Other Ant Targets

 
    compile - compiles code
    linenumbers - creates file containing all source files with line numbers (doesn't work)
    clean - deletes javadoc, .jar, download, temp, and .class files
    javadoc - creates javadoc
    jar - creates a jar file
    download - old JWS target
    publish - old JWS target
    
    
How does the application work?
==============================
Command Line:
The user is asked many hands the user wants to be dealt, then if the user wants the deck to be shuffled. The user is asked for a number to be dealt for each hand. The inputted numbers of cards are dealt to the player's hands and removed from the deck. The user is asked if they want to draw again until there are no more cards left in the deck. The user must input an integer from 0 to 52. Otherwise, the user will be dealt a default hand and the application will end. In the case of the deck being empty, the application will prompt the user to either reset with an empty hand and new deck or exit. The shuffle is done by using the shuffle function in the Collections class by using a RandomSecure seed.

GUI:
The user is prompted how many hands they want and if they don't want the deck to be shuffled, only shuffled once, or before each player draws. Then a panel is displayed that contains the textfields where the user can input how many cards they want for each hand. Then click the display cards button which replaces that panel with a new panel containing the textarea that displays the cards dealt for each hand and the remaining cards in the deck. The user can choose to continue drawing or reset the deck.
    
Notes for the next set of Students:
===================================
As of now, the card dealer works but isn't perfect. Some smaller things that should be done don't effect the fact the card dealer is done. These include a code clean-up and adding JUnit tests.
Either more features or implementing a game using the classes implemented would be the next logical step in improving this project.
