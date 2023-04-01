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
    @DisplayName("testing CardGame constructor")
    public void testCardGameConstructor() {
        assertEquals(cg1.getPlayer().getBalance(), 200);
        assertEquals(cg1.getPlayer().getCardHandSize(), 0);
        assertEquals(cg1.getDealer().getCardHandSize(), 0);
        assertEquals(cg1.getCardDeck().getDeck().size(),52);
    }

    @Test
    @DisplayName("testing setPlayerStartingHand()")
    public void testSetPlayerStartingHand() {
        cg1.setPlayerStartingHand();
        assertEquals(cg1.getPlayer().getCardHandSize(), 2);
    }

    @Test
    @DisplayName("testing setDealerStartingHand()")
    public void testSetDealerStartingHand() {
        cg1.setDealerStartingHand();
        assertEquals(cg1.getDealer().getCardHandSize(), 2);
    }

    @Test
    @DisplayName("testing getHandValue()")
    public void testGetHandValue() {
        List<Card> l1 = new ArrayList<>(Arrays.asList(new Card('S', 1)));
        assertEquals(cg1.getHandValue(l1), 11);
        l1.add(new Card('S', 13));
        assertEquals(cg1.getHandValue(l1), 21);
        l1.add(new Card('S', 1));
        assertEquals(cg1.getHandValue(l1), 12);
        l1.add(new Card('S', 1));
        assertEquals(cg1.getHandValue(l1), 13);
        l1.add(new Card('S', 7));
        assertEquals(cg1.getHandValue(l1), 20);
    }

    @Test
    @DisplayName("testing ")
    public void testDealerPlaysHand() {
        cg1.dealerPlaysHand();
        assertTrue(cg1.getHandValue(cg1.getDealer().getCardHand())>=17);
    }

    @Test
    @DisplayName("testing ")
    public void testRoundOutcome() {
        // OBS! MANGLER HER
        // Lurer på hvordan vi skal fikse dette
        // med tanke på at player.getCardHand()
        // metoden er innkapslet
    }

    @Test
    @DisplayName("testing ")
    public void testDistributeMoney() {
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().decreaseBalance(20);
        cg1.distributeMoney("won");
        assertEquals(cg1.getPlayer().getBalance(), 220);

        cg1.getPlayer().increaseBet();
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().decreaseBalance(20);
        cg1.distributeMoney("tie");
        assertEquals(cg1.getPlayer().getBalance(), 220);

        cg1.getPlayer().increaseBet();
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().decreaseBalance(20);
        cg1.distributeMoney("lost");
        assertEquals(cg1.getPlayer().getBalance(), 200);

        cg1.getPlayer().increaseBet();
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().decreaseBalance(20);
        cg1.distributeMoney("blackjack");
        assertEquals(cg1.getPlayer().getBalance(), 240);
    }
}
