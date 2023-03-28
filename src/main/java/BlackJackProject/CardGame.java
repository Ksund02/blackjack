package blackjackproject;

import java.util.ArrayList;
import java.util.List;

public class CardGame {
    
    private CardDeck cardDeck;
    private List<Card> dealerHand = new ArrayList<>();
    private List<Card> playerHand = new ArrayList<>();
    private int balance;
    private int currentBet;

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

    protected void setBalance(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance must be positive!");
        }
        this.balance = balance;
    }

    public Card drawPlayerCard() {
        Card newCard = cardDeck.getRandomCard();
        playerHand.add(newCard);
        return newCard;
    }

    public Card drawDealerCard() {
        Card newCard = cardDeck.getRandomCard();
        dealerHand.add(newCard);
        return newCard;
    }

    public int getHandValue(List<Card> cardHand) {
        int totalValue = 0;
        int totalAce = 0;
        for (Card card : cardHand) {
            if (card.getFace() > 1) {
                if (card.getFace() >= 10) {
                    totalValue += 10;
                } else {
                    totalValue += card.getFace();
                }
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


    public void dealerPlaysHand() {
        while (getHandValue(dealerHand) < 17) {
            drawDealerCard();
        }
    }
    
    public void increaseBet() {
        currentBet += 10;
    }

    public void decreaseBet() {
        currentBet -= 10; 
    }

    public void resetCardGame() {
        returnCardsToDeck(playerHand);
        returnCardsToDeck(dealerHand);
        setDealerCards();
        setPlayerCards();
    }

    private void returnCardsToDeck(List<Card> cardHand) {
        for (Card card : cardHand) {
            cardDeck.addCard(card);
        }
        cardHand.clear();
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public List<Card> getDealerHand() {
        return new ArrayList<>(dealerHand);
    }

    public List<Card> getPlayerHand() {
        return new ArrayList<>(playerHand);
    }

    public boolean roundOver() {
        return getHandValue(playerHand) >= 21;
    }

    public boolean cardGameLost() {
        return balance == 0 && roundOutcome() == "lost";
    }

    public void distributeMoney(String outcome) {
        switch (outcome) {
            case "blackjack":
                balance += 3 * currentBet;
                break;
            case "won":
                balance += 2 * currentBet;
                break;
            case "tie":
                balance += currentBet;
                break;
        }
        currentBet = 0;
    }

    public String roundOutcome() {
        int playerHandValue = getHandValue(playerHand);
        int dealerHandValue = getHandValue(dealerHand);

        if (playerHandValue == 21 && playerHand.size() == 2) {
            if (dealerHandValue == 21 && dealerHand.size() == 2) {
                return "tie";
            } else {
                return "blackjack";
            }
        } else if (dealerHandValue == 21 && dealerHand.size() == 2) {
            return "lost";
        } 

        if (playerHandValue > 21) {
            return "lost";
        } else if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
            return "won";
        } else if (dealerHandValue > playerHandValue) {
            return "lost";
        } else {
            return "tie";
        }
    }
}
