package blackjackproject;

import java.util.List;

public interface CardHolder {
    
    /**
     * Gets the card hand from the card holder and returns them.
     * 
     * @return A list of the card holders cards.
     */
    List<Card> getCardHand();

    /**
     * Draws a random card from the card deck and returns it.
     * 
     * @param cardDeck The card deck to draw from
     * @return The drawn card from the card deck
     */
    Card drawCard(CardDeck cardDeck);

    /**
     * draws a specific card from the card deck.
     * 
     * @param card The specific card to draw
     * @param cardDeck The card deck to draw from
     */
    void drawCard(Card card, CardDeck cardDeck);

    /**
     * Removes all cards from the card holders hand
     */
    void removeCards();

}
