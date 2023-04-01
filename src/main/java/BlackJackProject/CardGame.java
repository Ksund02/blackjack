package blackjackproject;

import java.util.ArrayList;
import java.util.List;

public class CardGame {
    
    private CardDeck cardDeck;
    private Player player;
    private Dealer dealer;
    private FileIO fileIO;

    public CardGame(int balance, int totalDecks) {
        cardDeck = new CardDeck(totalDecks);
        player = new Player(balance);
        dealer = new Dealer();
        fileIO = new FileIO(System.getProperty("user.dir") + "/src/main/resources/blackjackproject/SavedGame.txt"); //Adderer det som ikke er felles for hver datamaskin
    }

    public void setPlayerStartingHand() {
        player.drawCard(cardDeck);
        player.drawCard(cardDeck);
    }

    public void setDealerStartingHand() {
        dealer.drawCard(cardDeck);
        dealer.drawCard(cardDeck);
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
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
        for (int i = 1; i <= totalAce; i++) {
            if (totalValue + 11 < 22 && i == totalAce) {
                totalValue += 11;
            } else {
                totalValue++;
            }
        }
        return totalValue;
    }

    public void dealerPlaysHand() {
        while (getHandValue(dealer.getCardHand()) < 17) {
            dealer.drawCard(cardDeck);
        }
    }

    public boolean roundOver() {
        return getHandValue(player.getCardHand()) >= 21;
    }

    public boolean gameLost() {
        return player.getBalance() == 0 && roundOutcome() == "lost";
    }

    public String roundOutcome() {
        int playerHandValue = getHandValue(player.getCardHand());
        int dealerHandValue = getHandValue(dealer.getCardHand());

        if (playerHandValue == 21 && player.getCardHandSize() == 2) {
            if (dealerHandValue == 21 && dealer.getCardHandSize() == 2) {
                return "tie";
            } else {
                return "blackjack";
            }
        } else if (dealerHandValue == 21 && dealer.getCardHandSize() == 2) {
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

    public void distributeMoney(String outcome) {
        switch (outcome) {
            case "blackjack":
                player.increaseBalance(3 * player.getCurrentBet());
                break;
            case "won":
                player.increaseBalance(2 * player.getCurrentBet());
                break;
            case "tie":
                player.increaseBalance(player.getCurrentBet());
                break;
        }
        player.setCurrentBet(0);
    }

    public void resetCardGame() {
        returnCardsToDeck(player);
        returnCardsToDeck(dealer);
    }

    private void returnCardsToDeck(CardHolder cardHolder) {
        for (Card card : cardHolder.getCardHand()) {
            cardDeck.addCard(card);
        }
        cardHolder.removeCards();
    }

    public void readStateFromFile() {
        List<String> lines = fileIO.readFromFile();

    }

    public void writeStateToFile() {
        List<String> lines = new ArrayList<>();

        if (player.getCardHand().isEmpty()) {
            lines.add("N0\nN0");
        } else {
            lines.add(newStringOfCards(player.getCardHand()));
            lines.add("\n" + newStringOfCards(dealer.getCardHand()));
        }
        lines.add("\n" + player.getBalance() + "," + player.getCurrentBet());

        fileIO.writeToFile(lines);
    }

    private String newStringOfCards(List<Card> cards) {
        StringBuilder builder = new StringBuilder();
        for (Card card : cards) {
            builder.append(",").append(card.getSuit()).append(card.getFace());
        }
        return builder.substring(1);
    }

    public static void main(String[] args) {
        CardGame cg = new CardGame(300, 1);
        cg.getPlayer().drawCard(cg.getCardDeck());
        cg.dealerPlaysHand();
        cg.writeStateToFile();
    }

}