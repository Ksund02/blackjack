package blackjackproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Dealer d1;
    private CardDeck cd0;

    @BeforeEach 
    public void setUp() {
        d1 = new Dealer();
        cd0 = new CardDeck(1);
    }

    @Test
    @DisplayName("Test constructor")
    public void testDealerConstructor() {
        assertEquals(0, d1.getCardHandSize());
    }

    @Test
    @DisplayName("Test drawing card")
    public void testDrawCard() {
        Card drawnCard = d1.drawCard(cd0);
        assertEquals(true, drawnCard instanceof Card);
        assertEquals(true, d1.getCardHand().contains(drawnCard));
    }

    @Test
    @DisplayName("Test removing card")
    public void testRemoveCards() {
        d1.drawCard(cd0);
        d1.removeCards();
        assertEquals(d1.getCardHandSize(), 0);
    }
}
