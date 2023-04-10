package blackjackproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @DisplayName("Test constructor")
    public void testCardGameConstructor() {
        assertEquals(200, cg1.getPlayer().getBalance());
        assertEquals(0, cg1.getPlayer().getCardHandSize());
        assertEquals(0, cg1.getDealer().getCardHandSize());
        assertEquals(52, cg1.getCardDeck().getDeck().size());
    }

    @Test
    @DisplayName("Test setting the starting card hands")
    public void testSetPlayerStartingHand() {
        cg1.setStartingCardHands();
        assertEquals(2, cg1.getPlayer().getCardHandSize());
        assertEquals(2, cg1.getDealer().getCardHandSize());
    }

    @Test
    @DisplayName("Test calculating card hand value")
    public void testGetHandValue() {
        List<Card> l1 = new ArrayList<>(Arrays.asList(
            new Card('S', 1)
            ));

        assertEquals(11, cg1.getHandValue(l1));
        l1.add(new Card('H', 1));
        assertEquals(12, cg1.getHandValue(l1));
        l1.add(new Card('H', 6));
        assertEquals(18, cg1.getHandValue(l1));
        l1.add(new Card('H', 3));
        assertEquals(21, cg1.getHandValue(l1));
        l1.add(new Card('S', 13));
        assertEquals(21, cg1.getHandValue(l1));
        l1.add(new Card('D', 11));
        assertEquals(31, cg1.getHandValue(l1));
    }

    @Test
    @DisplayName("Test dealer plays their turn")
    public void testDealerPlaysHand() {
        cg1.dealerPlaysHand();
        assertTrue(cg1.getHandValue(cg1.getDealer().getCardHand()) >= 17);
    }

    @Test
    @DisplayName("Test getting the round outcome")
    public void testRoundOutcome() {
        cg1.getPlayer().drawCard(new Card('S', 13), cg1.getCardDeck());
        cg1.getPlayer().drawCard(new Card('S', 1), cg1.getCardDeck());
        cg1.getDealer().drawCard(new Card('H', 7), cg1.getCardDeck());
        cg1.getDealer().drawCard(new Card('H', 13), cg1.getCardDeck());
        assertEquals(RoundOutcome.BLACKJACK, cg1.roundOutcome());

        cg1.getPlayer().removeCards();
        cg1.getPlayer().drawCard(new Card('D', 12), cg1.getCardDeck());
        assertEquals(RoundOutcome.LOSS, cg1.roundOutcome());

        cg1.getPlayer().drawCard(new Card('D', 7), cg1.getCardDeck());
        assertEquals(RoundOutcome.TIE, cg1.roundOutcome());

        cg1.getPlayer().drawCard(new Card('S', 5), cg1.getCardDeck());
        assertEquals(RoundOutcome.LOSS, cg1.roundOutcome());
    }

    @Test
    @DisplayName("Test distributing money")
    public void testDistributeMoney() {
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().decreaseBalance(20);
        cg1.distributeMoney(RoundOutcome.WIN);
        assertEquals(220, cg1.getPlayer().getBalance());

        cg1.getPlayer().increaseBet();
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().decreaseBalance(20);
        cg1.distributeMoney(RoundOutcome.TIE);
        assertEquals(220, cg1.getPlayer().getBalance());

        cg1.getPlayer().increaseBet();
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().decreaseBalance(20);
        cg1.distributeMoney(RoundOutcome.LOSS);
        assertEquals(200, cg1.getPlayer().getBalance());

        cg1.getPlayer().increaseBet();
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().decreaseBalance(20);
        cg1.distributeMoney(RoundOutcome.BLACKJACK);
        assertEquals(240, cg1.getPlayer().getBalance());
    }
    
}