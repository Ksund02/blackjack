package blackjackproject;

import java.util.ArrayList;
import java.util.List;

public class CardGame {
    
    private CardDeck cardDeck;
    private List<Card> dealerHand = new ArrayList<>();
    private List<Card> playerHand = new ArrayList<>();
    private double balance;
    private double currentBet;

    public CardGame(int balance, int totalDecks) {
        cardDeck = new CardDeck(totalDecks);
        setPlayerCards();
        setDealerCards();
        setBalance(balance);
    }

    private void setPlayerCards() {
        drawCard(playerHand);
        drawCard(playerHand);
    }

    private void setDealerCards() {
        drawCard(dealerHand);
        drawCard(dealerHand);
    }

    private void setBalance(int balance) {
        if (balance <= 0) {
            throw new IllegalArgumentException("Balance must be greater than zero!");
        }
        this.balance = balance;
    }

    //Kan lage dealer og player klasse? -Arv/Interface?
    public void drawCard(List<Card> cardHand) {
        cardHand.add(cardDeck.getRandomCard());
    }

    public int getHandValue(List<Card> cardHand) {
        int totalValue = 0;
        int totalAce = 0;
        for (Card card : cardHand) {
            if (card.getFace() > 1) {
                totalValue += card.getFace();
            } else {
                totalAce++;
            }
        }
        for (int i = 0; i < totalAce; i++) {
            if (totalValue + 11 < 22 && totalAce - i > 0) {
                totalValue += 11;
            } else {
                totalValue++;
            }
        }
        return totalValue;
    }

    public boolean handValueLessThanTwentyTwo(List<Card> cardHand) {
        return getHandValue(cardHand) < 22;
    }

    public void dealersTurn() {
        while (getHandValue(dealerHand) < 17) {
            drawCard(dealerHand);
        }
    }
    
    public void betMoney(int money) {
        balance -= money;
        currentBet = money;
    }

    public void newRound() {
        returnCards(playerHand);
        returnCards(dealerHand);
        setDealerCards();
        setPlayerCards();
        
        currentBet = 0;

    }

    private void returnCards(List<Card> cardHand) {
        for (Card card : cardHand) {
            cardDeck.addCard(card);
            cardHand.remove(card);
        }
    }

    public void checkRoundStatus() {
        
    }

    public boolean roundWon() {

        return balance == 0;
    }

    public double getBalance() {
        return balance;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public static void main(String[] args) {
        


    }

}
