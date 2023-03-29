package blackjackproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    private Card c1;
    private Card c2;
    private Card c3;
    private CardDeck cd1;

    @BeforeEach
    public void setUp() {
        c1 = new Card('S', 10);
        c2 = new Card('H', 1);
        c3 = new Card('C', 4);
        cd1 = new CardDeck(1);
    }

    @Test
    @DisplayName("testing Card constructor with invalid values")
    public void testCardConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Card('S', 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Card('L',5);
        });
        assertEquals('S', c1.getSuit());
        assertEquals(10, c1.getFace());
        assertEquals('H', c2.getSuit());
        assertEquals(1, c2.getFace());
    }
}
