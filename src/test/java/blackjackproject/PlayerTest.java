package blackjackproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player p1;
    private Player p2;
    private Player p3;
    private CardDeck cd0;

    @BeforeEach
    public void setUp() {
        p1 = new Player(300);
        p2 = new Player(200);
        p3 = new Player(10);
        cd0 = new CardDeck(1);
    }

    @Test
    @DisplayName("testing player constructor")
    public void testPlayerConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Player(-4);
        });
        assertEquals(p1.getBalance(), 300);
        assertEquals(p2.getBalance(), 200);
    }

    @Test
    @DisplayName("testing setCurrentBet")
    public void testSetCurrentBet() {
        p1.setCurrentBet(200);
        assertEquals(p1.getCurrentBet(), 200);
        assertThrows(IllegalArgumentException.class, () -> {
            p1.setCurrentBet(500);
        });
    }

    @Test
    @DisplayName("testing drawCard")
    public void testDrawCard() {
        Card drawnCard = p1.drawCard(cd0);
        assertEquals(drawnCard instanceof Card, true);
        assertEquals(p1.getCardHand().contains(drawnCard), true);
    }

    @Test
    @DisplayName("testing removeCards")
    public void testRemoveCards() {
        p1.drawCard(cd0);
        p1.removeCards();
        assertEquals(p1.getCardHand().size(), 0);
    }

    @Test
    @DisplayName("testing increasebet")
    public void testIncreaseBet() {
        p3.increaseBet();
        assertEquals(p3.getCurrentBet(), 10);
        assertThrows(IllegalArgumentException.class, () -> {
            p3.increaseBet();
        });
        
    }

    @Test
    @DisplayName("testing decreaseBet")
    public void testDecreaseBet() {
        p3.increaseBet();
        p3.decreaseBet();
        assertEquals(p3.getCurrentBet(), 0);
        assertThrows(IllegalArgumentException.class, () -> {
            p3.decreaseBet();
        });
    }
}
