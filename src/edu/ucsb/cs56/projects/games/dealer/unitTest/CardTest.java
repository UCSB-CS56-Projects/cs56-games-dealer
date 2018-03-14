package edu.ucsb.cs56.projects.games.dealer.unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ucsb.cs56.projects.games.dealer.Card;

/**
 * The test class CardTest, to test the Card class
 * @author Kin Kwan Poon
 * @version UCSB, cs56, F16
 */
public class CardTest {
    @Test 
    public void testAce_SpadesEqualsAce_Hearts(){
	Card AS = new Card("Ace","Spades");
	Card AH = new Card("Ace","Hearts");
	assertFalse("The Hashcodes of two cards should not be the same",
		    AS.hashCode()==AH.hashCode());
    }

    @Test
    public void test2_ClubsEquals2_Clubs(){
	Card c1 = new Card("2","Clubs");
	Card c2 = new Card("2","Clubs");
	assertTrue("The Hashcodes of two cards should be the same",
		   c1.hashCode()==c2.hashCode());
	assertTrue("They should be equal",c1.equals(c2));
	assertEquals(0,c1.compareTo(c2));
    }

    @Test
    public void test2_ClubsEqualsNull(){
        Card c1 = new Card("2","Clubs");
        assertFalse("They should not be equal",c1.equals(null));
    }

    @Test
    public void test2_ClubsEqualsInteger(){
        Card c1 = new Card("2","Clubs");
        assertFalse("They should not be equal",c1.equals(5));
    }

    @Test
    public void testSpadesAceEqualsHeartsAce(){
        Card c1 = new Card("Ace","Spades");
        Card c2 = new Card("Ace","Hearts");
        assertFalse("They should not be equal",c1.equals(c2));
    }

    @Test
    public void testSpadesAceGreaterThanHeartsAce(){
    	Card c1 = new Card("Ace","Spades");
    	Card c2 = new Card("Ace","Hearts");
    	assertEquals(1,c1.compareTo(c2));
    }
    
    @Test
    public void testSpades4LessThanClubs5(){
    	Card c1 = new Card("4","Spades");
    	Card c2 = new Card("5","Clubs");
    	assertEquals(-1,c1.compareTo(c2));
    }
    
    @Test
    public void testHeartsAceGreaterThanHeartsKing(){
    	Card c1 = new Card("Ace","Hearts");
    	Card c2 = new Card("King","Hearts");
    	assertEquals(1,c1.compareTo(c2));
    }
   
    @Test
    public void testHeartsAceGreaterThanSpadeAce(){
        Card c1 = new Card("Ace","Hearts");
        Card c2 = new Card("Ace","Spades");
        assertEquals(-1,c1.compareTo(c2));
    }
 
    @Test 
    public void testAceValue(){
    	Card c1 = new Card("Ace","Clubs");
    	assertEquals(1,c1.rankValue(true).intValue());
    }
    
    @Test
    public void testJackValue(){
    	Card c1 = new Card("Jack","Clubs");
    	assertEquals(10,c1.rankValue(true).intValue());
    }
    @Test
    public void testQueenValue(){
    	Card c1 = new Card("Queen","Clubs");
    	assertEquals(10,c1.rankValue(true).intValue());
    }
    @Test
    public void testKingValue(){
    	Card c1 = new Card("King","Clubs");
    	assertEquals(10,c1.rankValue(true).intValue());
    }
    
    @Test
    public void testThreeValue(){
    	Card c1 = new Card("3","Clubs");
    	assertEquals(3,c1.rankValue(true).intValue());
    }
    
    @Test
    public void testThreeValueFalse(){
        Card c1 = new Card("3","Clubs");
        assertEquals(3,c1.rankValue(false).intValue());
    }

    @Test
    public void testThreeValueHidden(){
        Card c1 = new Card("3","Clubs");
        c1.hide();
        assertEquals(0,c1.rankValue(true).intValue());
    }
   
    @Test
    public void testThreeValueShowHidden(){
        Card c1 = new Card("3","Clubs");
        c1.hide();
        c1.showHidden();
        assertEquals(3,c1.rankValue(true).intValue());
    }

    @Test
    public void testJackValueNoBool(){
        Card c1 = new Card("Jack","Clubs");
        assertEquals(11,c1.rankValue().intValue());
    }

    @Test
    public void testQueenValueNoBool(){
        Card c1 = new Card("Queen","Clubs");
        assertEquals(12,c1.rankValue().intValue());
    }

    @Test
    public void testDiamondValueNoBool(){
        Card c1 = new Card("Jack","Diamonds");
        assertEquals(1,c1.suitVaule().intValue());
    }

    @Test
    public void testFooValueNoBool(){
        Card c1 = new Card("Queen","Foo");
        assertEquals(0,c1.suitVaule().intValue());
    }


    @Test
    public void testToStringHiddenCard(){
        Card c1 = new Card("Ace","Spades");
        c1.hide();
        assertEquals(c1.toString(), "X of X");
    }

    @Test
    public void testGetRankOfAceSpades(){
        Card c1 = new Card("Ace","Spades");
        assertEquals(c1.getRank(), Card.Rank.Ace);
    }

    @Test
    public void testGetRankOfHidden(){
        Card c1 = new Card("Ace","Spades");
        c1.hide();
        assertEquals(c1.getRank(), Card.Rank.X);
    }

    @Test
    public void testGetSuitOfAceSpades(){
        Card c1 = new Card("Ace","Spades");
        assertEquals(c1.getSuit(), Card.Suit.Spades);
    }

    @Test
    public void testGetSuitOfHidden(){
        Card c1 = new Card("Ace","Spades");
        c1.hide();
        assertEquals(c1.getSuit(), Card.Suit.X);
    }

}
