package edu.ucsb.cs56.projects.games.dealer.unitTest;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ucsb.cs56.projects.games.dealer.BlackJackPlayer;
import edu.ucsb.cs56.projects.games.dealer.Deck;

/**
 * This class is used to test the BlackJackPlayer class.
 * @author Kin Kwan Poon
 * @version UCSB, cs56, F16
 */
public class BlackJackPlayerTest {
    private Deck d;
    @Before
    public void setUp(){
    	d=new Deck();
	}

    @Test
    public void testGetName(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        assertEquals("test", h1.getName());
    }

    @Test
    public void testGetScore(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        assertEquals(10, h1.get_score());
    }

    @Test
    public void testLoseScore(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        h1.lose_score(1);
        assertEquals(9, h1.get_score());
    }

    @Test
    public void testGainScore(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        h1.gain_score(1);
        assertEquals(11, h1.get_score());
    }
	
    @Test
    public void testHandVaule1(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	h1.addtoHand(2,d);
    	assertEquals(13,h1.getHandValue());
    }
    
    @Test
    public void testHandVaule2(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	h1.addtoHand(5,d);
    	assertEquals(15,h1.getHandValue());
    }
    
    @Test
    public void testHandVaule3(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	BlackJackPlayer h2=new BlackJackPlayer("test");
    	h1.addtoHand(1,d);// Ace
    	h2.addtoHand(8, d); // 2 to 9
    	h1.addtoHand(1, d);// 10
    	assertEquals(21,h1.getHandValue());
    }
    @Test
    public void testHandVaule4(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	BlackJackPlayer h2=new BlackJackPlayer("test");
    	h1.addtoHand(1,d);// Ace
    	h2.addtoHand(9, d); // 2 to 10
    	h1.addtoHand(1, d);// Jack
    	assertEquals(21,h1.getHandValue());
    }
    @Test
    public void testHandVaule5(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	BlackJackPlayer h2=new BlackJackPlayer("test");
    	h1.addtoHand(1,d);// Ace
    	h2.addtoHand(10, d); // 2 to Jack
    	h1.addtoHand(1, d);// Queen
    	assertEquals(21,h1.getHandValue());
    }
    @Test
    public void testHandVaule6(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	BlackJackPlayer h2=new BlackJackPlayer("test");
    	h1.addtoHand(1,d);// Ace
    	h2.addtoHand(11, d); // 2 to 10
    	h1.addtoHand(1, d);// King
    	assertEquals(21,h1.getHandValue());
    }
    @Test
    public void testHandVaule7(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	BlackJackPlayer h2=new BlackJackPlayer("test");
    	h1.addtoHand(1,d);// Ace
    	h2.addtoHand(12, d); // 2 to King Spades
    	h1.addtoHand(1, d);// Hearts Ace
    	assertEquals(12,h1.getHandValue());
    }
    
    @Test
    public void testBusted1(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	BlackJackPlayer h2=new BlackJackPlayer("test");
    	h1.addtoHand(10, d);
    	h2.addtoHand(3, d);// Jack to King
    	assertTrue(h2.isBusted());
    }   

    @Test
    public void testBusted2(){
    	BlackJackPlayer h1=new BlackJackPlayer("test");
    	BlackJackPlayer h2=new BlackJackPlayer("test");
    	h1.addtoHand(1, d);
    	h2.addtoHand(1, d);// 2
    	h1.addtoHand(9, d);
    	h2.addtoHand(2, d);// Queen and King
    	assertTrue(h2.isBusted());
    }
   
    @Test
    public void testBusted3(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        assertTrue(!h1.isBusted());
    }

    @Test
    public void testToString1(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        h1.addtoHand(1, d);
        assertEquals("test: \nAce of Spades (11)\n", h1.toString());
    }

    @Test
    public void testToString2(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        h1.addtoHand(2, d);
        assertEquals("test: \nAce of Spades, 2 of Spades (13)\n", h1.toString());
    }

    @Test
    public void testClearHand(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        h1.addtoHand(10, d);
        h1.clearHand();
        assertEquals("test: \n (0)\n", h1.toString());
    }

    @Test
    public void testBusted(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        assertEquals("test busted.", h1.busted());
    }

    @Test
    public void testWin(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        assertEquals("test wins.", h1.win());
    }

    @Test
    public void testLose(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        assertEquals("test loses.", h1.lose());
    }

    @Test
    public void testPush(){
        BlackJackPlayer h1=new BlackJackPlayer("test");
        assertEquals("test pushes.", h1.push());
    }



}
