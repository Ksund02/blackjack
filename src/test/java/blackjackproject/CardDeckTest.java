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
    @DisplayName("Test constructor")
    public void testCardDeckConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CardDeck(-1);
        });

        assertEquals(52, cd1.getDeck().size());
        assertEquals(0, cd2.getDeck().size());
    }

    @Test
    @DisplayName("Test adding card")
    public void testAddCard() {
        Card c1 = new Card('S', 3);
        cd2.addCard(c1);
        assertEquals(true, cd2.getDeck().contains(c1));
    }

    @Test
    @DisplayName("Test removing card")
    public void testRemoveCard() {
        Card c1 = new Card('S', 3);
        cd1.removeCard(c1);
        assertEquals(false, cd1.getDeck().contains(c1));
    }

    @Test
    @DisplayName("Test drawing a random card")
    public void testRandomCard() {
        Card random_card;

        for (int i = 0; i < 20; i++) {
            random_card = cd1.getRandomCard();
            assertEquals(true, random_card instanceof Card);
            assertEquals(false, cd1.getDeck().contains(random_card));
        }
    }

}