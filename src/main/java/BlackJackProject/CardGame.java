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
        currentBet = 10;
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

    //Kan lage dealer og player klasse? -Arv/Interface?
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


    public void dealersTurn() {
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
    
    /*public String checkWon() {
        //Checks if player or dealer got blackjack
        if (getHandValue(playerHand) == 21 && playerHand.size() == 2) {
            if (getHandValue(dealerHand)==21 && dealerHand.size() == 2) {
                return "tie";
            }
            return "blackjack";
        }
        if (getHandValue(dealerHand) == 21 && dealerHand.size() ==2) {
            return "lost";
        }

        // Checks if player busted
        if (!handValueLessThanTwentyTwo(playerHand)) {
            return "lost";
        }

        // Checks if dealer busted or if playerHand is closer to 21 than dealerHand
        if (!handValueLessThanTwentyTwo(dealerHand) || getHandValue(playerHand) > getHandValue(dealerHand)) {
            return "won";
        }

        // Checks if dealerhand is closer to 21
        if (getHandValue(playerHand) < getHandValue(dealerHand)) {
            return "lost";
        }

        // Last case scenario where they have the same value
        return "tie";
    }
    */

    public boolean roundOver() {
        return getHandValue(playerHand) >= 21;
        //int playerHandValue = getHandValue(playerHand);
        //int dealerHandValue = getHandValue(dealerHand);
/*
        // if (spiller har trykt på pass) -> checkwinconditions


        // Checks if player got blackjack
        if (playerHandValue == 21 && playerHand.size() == 2) {
            if (dealerHandValue != playerHandValue) {
                return "blackjack";
            } else {
                return "tie";
            }
        }

        //Checks if player has 21
        if (playerHandValue == 21) {
            return "done";
            //dealersTurn();
            //checkWinConditions();
        }

        // You can still hit or pass here
        return "ongoing";
*/
    }

    public String checkRoundOutcome() {
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

/*
        //Checks if dealer got blackjack
        if (dealerHandValue == 21 && dealerHand.size() == 2) {
            return "lost";
        }

        // Checks if dealer busted or if playerHand is closer to 21 than dealerHand
        if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
            return "won";
        }

        // Checks if dealerhand is closer to 21
        if (playerHandValue < dealerHandValue) {
            return "lost";
        }

        // Last case scenario where they have the same value
        return "tie";
*/
    }

}
