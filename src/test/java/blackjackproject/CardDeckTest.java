package blackjackproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    private CardDeck cd1;
    private CardDeck cd2;

    @BeforeEach
    public void setUp() {
        cd1 = new CardDeck(1);
        cd2 = new CardDeck(0);
    }

    @Test
    @DisplayName("testing CardDeck constructor with invalid values")
    public void testCardDeckConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CardDeck(-1);
        });
    }

    @Test
    @DisplayName("testing addCard")
    public void testAddCard() {
        Card c1 = new Card('S', 3);
        cd2.addCard(c1);
        assertEquals(true, cd2.getDeck().contains(c1));
    }

    @Test
    @DisplayName("testing removeCard")
    public void testRemoveCard() {
        Card c1 = new Card('S', 3);
        cd2.addCard(c1);
        cd2.removeCard(c1);
        assertEquals(false, cd2.getDeck().contains(c1));
    }

    @Test
    @DisplayName("testing getRandomCard")
    public void testRandomCard() {
        Card random_card = cd1.getRandomCard();
        assertEquals(true, random_card instanceof Card);
        assertEquals(false, cd1.getDeck().contains(random_card));
    }
}
