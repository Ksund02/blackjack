package blackjackproject;

import java.util.ArrayList;
import java.util.List;

public class CardGame {
    
    private CardDeck cardDeck;
    private List<Card> dealerCards = new ArrayList<>();
    private List<Card> playerCards = new ArrayList<>();
    private double balance;

    public CardGame(int balance, int totalDecks) {
        cardDeck = new CardDeck(totalDecks);
        setPlayerCards();
        setDealerCards();
        setBalance(balance);
    }

    private void setPlayerCards() {
        drawPlayerCard();
        drawPlayerCard();
    }

    private void setDealerCards() {
        drawDealerCard();
        drawDealerCard();
    }

    private void setBalance(int balance) {
        if (balance <= 0) {
            throw new IllegalArgumentException("Balance must be greater than zero!");
        }
        this.balance = balance;
    }

    //Kan lage dealer og player klasse? -Arv/Interface?
    public void drawPlayerCard() {
        playerCards.add(cardDeck.getRandomCard());
    }

    public void drawDealerCard() {
        dealerCards.add(cardDeck.getRandomCard());
    }

    public void betMoney(int money) {

    }

    public int getHandValue() {
        int totalValue = 0;
        int totalAce = 0;
        for (Card card : cardDeck.getDeck()) {
            if (card.getFace() > 1) {
                totalValue += card.getFace();
            } else {
                totalAce++;
            }
        }
        for (int i = 0; i < totalAce; i++) {
            if (totalValue + 11 < 22) {
                totalValue += 11;
            } else {
                totalValue++;
            }
        }
        return totalValue;
    }

    public boolean handValueLessThanTwentyOne() {
        return getHandValue() > 21;
    }

}
