package blackjackproject;

import java.util.ArrayList;
import java.util.List;

public class Player implements CardHolder {

    private List<Card> cardHand;
    private int balance;
    private int currentBet;
    private boolean hasPlacedBet;
    private boolean hasEndedRound;

    public Player(int balance) {
        cardHand = new ArrayList<>();
        setBalance(balance);
    }

    protected void setBalance(int balance) {
        validMoneyAmount(balance);
        this.balance = balance;
    }

    protected void setCurrentBet(int currentBet) {
        validCurrentBet(currentBet);
        this.currentBet = currentBet;
    }

    protected void setHasPlacedBet(boolean b) {
        hasPlacedBet = b;
    }

    protected void setHasEndedRound(boolean b) {
        hasEndedRound = b;
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public int getCardHandSize() {
        return cardHand.size();
    }

    public boolean hasPlacedBet() {
        return hasPlacedBet;
    }

    public boolean hasEndedRound() {
        return hasEndedRound;
    }

    public void increaseBalance(int money) {
        validMoneyAmount(money);
        balance += money;
    }

    public void decreaseBalance(int money) {
        validMoneyAmount(money);
        balance -= money; 
    }

    private void validMoneyAmount(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money amount must be positive!");
        }
    }

    private void validCurrentBet(int currentBet) {
        if (currentBet < 0 || currentBet > balance) {
            throw new IllegalArgumentException("Currentbet cannot be negative or more than balance");
        }
    }

    public void increaseBet() {
        validCurrentBet(currentBet+10);
        currentBet += 10;
    }

    public void decreaseBet() {
        validCurrentBet(currentBet-10);
        currentBet -= 10; 
    }

    @Override
    public List<Card> getCardHand() {
        return new ArrayList<>(cardHand);
    }

    @Override
    public Card drawCard(CardDeck cardDeck) {
        Card newCard = cardDeck.getRandomCard();
        cardHand.add(newCard);
        return newCard;
    }

    @Override
    public void removeCards() {
        cardHand.clear();
    }

    @Override
    public void drawCard(Card card, CardDeck cardDeck) {
        cardHand.add(card);
        cardDeck.removeCard(card);
    }
    
}