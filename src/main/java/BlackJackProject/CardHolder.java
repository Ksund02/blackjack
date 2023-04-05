package blackjackproject;

import java.util.List;

public interface CardHolder {
    
    List<Card> getCardHand();
    Card drawCard(CardDeck cardDeck);
    void drawCard(Card card, CardDeck cardDeck);
    void removeCards();

}
