cs56-games-dealer
=================

W14 | jcneally | acantor | 

What is this?
=============
It is an application to shuffle a deck of 52 playing cards and deal them to the user using command lines. 


How To Run
==========

In the directory of where the build.xml is located 


    Type `ant run`
    
    
How does the application work?
==============================
It starts be inquiring for a number to be dealed. It deals inputted number to the user's hand and removes the drawn cards in the deck until the user declines to draw cards. The user must input an integer from 0 to 52. Otherwise, the user will be dealt a default hand and the application will end. In the case of the deck being empty, the application will prompt the user to either reset with an empty hand and new deck or exit. The user can shuffle the deck once prompted. The shuffle is done by using the shuffle function in the Collections class by using a RandomSecure seed.
