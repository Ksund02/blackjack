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
    @DisplayName("Test constructor")
    public void testPlayerConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Player(-10);
        });

        assertEquals(300, p1.getBalance());
        assertEquals(0, p1.getCurrentBet());
        assertEquals(0, p1.getCardHandSize());
        assertEquals(false, p1.hasEndedRound());
        assertEquals(false, p1.hasPlacedBet());
        assertEquals(200, p2.getBalance());
    }

    @Test
    @DisplayName("Test setting current bet")
    public void testSetCurrentBet() {
        p1.setCurrentBet(200);
        assertEquals(200, p1.getCurrentBet());

        assertThrows(IllegalArgumentException.class, () -> {
            p1.setCurrentBet(500);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            p1.setCurrentBet(-100);
        });
    }

    @Test
    @DisplayName("Test drawing card")
    public void testDrawCard() {
        Card drawnCard = p1.drawCard(cd0);
        assertEquals(true, drawnCard instanceof Card);
        assertEquals(true, p1.getCardHand().contains(drawnCard));
    }

    @Test
    @DisplayName("Test removing card")
    public void testRemoveCards() {
        p1.drawCard(cd0);
        p1.removeCards();
        assertEquals(0, p1.getCardHandSize());
    }

    @Test
    @DisplayName("Test increasing bet")
    public void testIncreaseBet() {
        p3.increaseBet();
        assertEquals(p3.getCurrentBet(), 10);

        assertThrows(IllegalArgumentException.class, () -> {
            p3.increaseBet();
        });
    }

    @Test
    @DisplayName("Test decreasing bet")
    public void testDecreaseBet() {
        p3.increaseBet();
        p3.decreaseBet();
        assertEquals(0, p3.getCurrentBet());

        assertThrows(IllegalArgumentException.class, () -> {
            p3.decreaseBet();
        });
    }

}