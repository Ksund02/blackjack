package blackjackproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDeck {
    
    public static final char[] validSuits = {'S', 'H', 'D', 'C'};
    public static final Random randomGenerator = new Random();
    private List<Card> deck = new ArrayList<>();

    public CardDeck(int totalDecks) {
        validDeckAmount(totalDecks);
        for (int i = 0; i < totalDecks; i++) {
            addNewCardDeck();
        }
    }

    private void validDeckAmount(int totalDecks) {
        if (totalDecks < 0) {
            throw new IllegalArgumentException("Cannot have negative amount of carddecks!");
        }
    }
    
    private void addNewCardDeck() {
        for (char suit : validSuits) {
            for (int face = 1; face < 14; face++) {
                addCard(new Card(suit, face));
            }
        }
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void removeCard(Card card) {
        deck.remove(card);
    }

    public Card getRandomCard() {
        int randomIndex = randomGenerator.nextInt(deck.size());
        Card randomCard = deck.get(randomIndex); // Gets a random card (index 0, to upperbound is what rand.nextint does)
        removeCard(randomCard);
        return randomCard;
    }

    //The code below is for testing:

    public List<Card> getDeck() {
        return new ArrayList<>(this.deck);
    }

    @Override
    public String toString() {
        return "" + deck;
    }

    public static void main(String[] args) {
        CardDeck blDeck = new CardDeck(2);
        System.out.println(blDeck.getRandomCard());
        List<Card> cd1 = new ArrayList<>();
        cd1.add(new Card('S', 5));
        cd1.add(new Card('S', 2));
        cd1.add(new Card('S', 3));
        cd1.add(new Card('S', 1));
        System.out.println(blDeck);
    }

}