package unitTest;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import edu.ucsb.cs56.projects.games.dealer.Deck;
import edu.ucsb.cs56.projects.games.dealer.Hand;
import edu.ucsb.cs56.projects.games.dealer.Card;

/**
 * The test class HandTest, to test the Hand class
 *
 * @author Kin Kwan Poon
 * @version CS56,F16
 * @see Deck,Hand,Card
 */
public class HandTest{

    @Test
    public void testUnshuffleDeck(){
	Deck d = new Deck();
	Hand h = new Hand();
	h.addtoHand(1,d);
	ArrayList<Card> expected=new ArrayList<Card>();
	expected.add(new Card("Ace","Spades"));
	assertEquals(expected,h.getHand());
    }




}
