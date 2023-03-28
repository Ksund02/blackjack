package blackjackproject;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements CardHolder {

    private List<Card> cardHand;

    public Dealer() {
        cardHand = new ArrayList<>();
    }

    public int getCardHandSize() {
        return cardHand.size();
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
    
}