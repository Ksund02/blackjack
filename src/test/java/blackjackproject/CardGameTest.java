package blackjackproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardGameTest {
    private CardGame cg1;


    @BeforeEach
    public void setUp() {
        cg1 = new CardGame(200, 1);
    }

    @Test
    @DisplayName("testing CardGame constructor")
    public void testCardGameConstructor() {
        assertEquals(cg1.getPlayer().getBalance(), 200);
        assertEquals(cg1.getPlayer().getCardHandSize(), 2);
        assertEquals(cg1.getDealer().getCardHandSize(), 2);
        assertEquals(cg1.getCardDeck().getDeck().size(),52-4);
    }
}
