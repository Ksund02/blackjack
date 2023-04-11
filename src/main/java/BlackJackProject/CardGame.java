package blackjackproject;

import java.util.ArrayList;
import java.util.List;

public class CardGame {
    
    private CardDeck cardDeck;
    private Player player;
    private Dealer dealer;

    public CardGame(int balance, int totalDecks) {
        cardDeck = new CardDeck(totalDecks);
        player = new Player(balance);
        dealer = new Dealer();
    }

    /**
     * Makes the player and dealer draw two cards each from the card deck. 
     * This is used at the start of the game.
     */
    public void setStartingCardHands() {
        player.drawCard(cardDeck);
        player.drawCard(cardDeck);
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

    /**
     * Calculates the total value of the specified card hand. 
     * 
     * @param cardHand The card hand to calculate the value of
     * @return The total value of the card hand.
     */
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

    /**
     * Makes the dealer draw cards until he reaches 17 or more.
     */
    public void dealerPlaysHand() {
        while (getHandValue(dealer.getCardHand()) < 17) {
            dealer.drawCard(cardDeck);
        }
    }

    /**
     * Checks if the round is over without having pressed the "pass" button. 
     * This happens when the player has a card hand value of 21 or more.
     * 
     * @return True if the round is over, otherwise false
     */
    public boolean roundOver() {
        return getHandValue(player.getCardHand()) >= 21;
    }

    /**
     * Checks if the game is lost. This happens when the player blanace is zero 
     * and the round outcome is LOSS.
     * 
     * @return True if the game is lost, otherwise false
     */
    public boolean gameLost() {
        return player.getBalance() == 0 && roundOutcome() == RoundOutcome.LOSS;
    }

    /**
     * Finds out what the outcome of the round was. Returns a string containing the information.
     * 
     * @return BLACKJACK, WIN, TIE or LOSS depending on the outcome 
     */
    public RoundOutcome roundOutcome() {
        int playerHandValue = getHandValue(player.getCardHand());
        int dealerHandValue = getHandValue(dealer.getCardHand());

        if (playerHandValue == 21 && player.getCardHandSize() == 2) {
            if (dealerHandValue == 21 && dealer.getCardHandSize() == 2) {
                return RoundOutcome.TIE;
            } else {
                return RoundOutcome.BLACKJACK;
            }
        } else if (dealerHandValue == 21 && dealer.getCardHandSize() == 2) {
            return RoundOutcome.LOSS;
        } 

        if (playerHandValue > 21) {
            return RoundOutcome.LOSS;
        } else if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
            return RoundOutcome.WIN;
        } else if (dealerHandValue > playerHandValue) {
            return RoundOutcome.LOSS;
        } else {
            return RoundOutcome.TIE;
        }
    }

    /**
     * Distributes money to the user from what the round outcome was. It also sets the current bet to zero.
     * BLACKJACK: Triples the bet and adds it to the balance.
     * WIN: Doubles the bet and adds it to the balance.
     * TIE: The bet is added to the balance.
     * LOSS: Nothing is added.
     * 
     * @param outcome The outcome of the current round
     */
    public void distributeMoney(RoundOutcome outcome) {
        player.increaseBalance(switch (outcome) {
            case BLACKJACK -> 3 * player.getCurrentBet();
            case WIN       -> 2 * player.getCurrentBet();
            case TIE       -> player.getCurrentBet();
            case LOSS      -> 0;
        });
        player.setCurrentBet(0);
    }

    /**
     * Returns the player and dealer cards to the deck. 
     * It also sets the players boolean values containing round information to false.
     */
    public void resetCardGame() {
        returnCardsToDeck(player);
        returnCardsToDeck(dealer);
        player.setHasEndedRound(false);
        player.setHasPlacedBet(false);
    }

    private void returnCardsToDeck(CardHolder cardHolder) {
        for (Card card : cardHolder.getCardHand()) {
            cardDeck.addCard(card);
        }
        cardHolder.removeCards();
    }

    /**
     * Loads the previous card game by setting the field in CardGame to corresponding info in the list.
     */
    public void setCardGame(List<String> lines) {
        List<Card> newDealerCards = makeNewCardHand(lines.get(0).split(","));
        List<Card> newPlayerCards = makeNewCardHand(lines.get(1).split(","));
        String[] money = lines.get(2).split(",");
        String[] roundInfo = lines.get(3).split(",");

        for (Card card : newDealerCards) {
            dealer.drawCard(card, cardDeck);
        }
        for (Card card : newPlayerCards) {
            player.drawCard(card, cardDeck);
        }
        player.setBalance(Integer.parseInt(money[0]));
        player.setCurrentBet(Integer.parseInt(money[1]));
        player.setHasPlacedBet(Boolean.parseBoolean(roundInfo[0]));
        player.setHasEndedRound(Boolean.parseBoolean(roundInfo[1]));
    }

    private List<Card> makeNewCardHand(String[] cards) {
        List<Card> cardHand = new ArrayList<>();
        if (!cards[0].equals("N0")) {
            for (String cardString : cards) {
                char suit = cardString.charAt(0);
                int face = Integer.parseInt(cardString.substring(1));
                Card card = new Card(suit, face);
                cardHand.add(card);
            }
        }
        return cardHand;
    }

    /**
     * Saves the current card game by retriving info from cardGame.
     * 
     * --LIST STRUCTURE--
     * "D0,D1,D2,...",
     * "\nP0,P1,P2,...",
     * "\nbalance,currentbet",
     * "\nhasPlacedBet,hasEndedRound"
     * 
     * @return A list of strings containing the information
     */
    public List<String> getCurrentCardGame() {
        List<String> lines = new ArrayList<>();

        if (player.getCardHand().isEmpty()) {
            lines.add("N0\nN0");
        } else {
            lines.add(newStringOfCards(dealer.getCardHand()));
            lines.add("\n" + newStringOfCards(player.getCardHand()));
        }
        lines.add("\n" + player.getBalance() + "," + player.getCurrentBet());
        lines.add("\n" + player.hasPlacedBet() + "," + player.hasEndedRound());

        return lines;
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
        cg.getCurrentCardGame();
        cg.setCardGame(FileIO.readFromFile());
    }
}