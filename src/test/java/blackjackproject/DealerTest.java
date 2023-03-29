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
    @DisplayName("testing dealer constructor")
    public void testDealerConstructor() {
        assertEquals(d1.getCardHandSize(), 0);
    }

    @Test
    @DisplayName("testing drawCard")
    public void testDrawCard() {
        Card drawnCard = d1.drawCard(cd0);
        assertEquals(drawnCard instanceof Card, true);
        assertEquals(d1.getCardHand().contains(drawnCard), true);
    }

    @Test
    @DisplayName("testing removeCards")
    public void testRemoveCards() {
        d1.drawCard(cd0);
        d1.removeCards();
        assertEquals(d1.getCardHand().size(), 0);
    }
}
