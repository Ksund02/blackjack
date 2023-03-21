package blackjackproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDeck implements CardInterface {
    
    public static final Random randomGenerator = new Random();
    private List<Card> deck = new ArrayList<>();

    public CardDeck(int totalDecks) {
        validDeckAmount(totalDecks);
        
        for (int i = 0; i < totalDecks; i++) {
            addNewCardDeck();
        }
    }

    private void validDeckAmount(int decks) {
        if (decks < 0) {
            throw new IllegalArgumentException("Cannot have negative amount of carddecks!");
        }
    }

    private void addNewCardDeck() {
        for (int face = 1; face < 14; face++) {
            for (int suit = 0; suit < 4; suit++) {
                addCard(new Card(face, validSuits.charAt(suit)));
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

    @Override
    public String toString() {
        return "" + deck;
    }

    public static void main(String[] args) {
        CardDeck blDeck = new CardDeck(2);
        System.out.println(blDeck.getRandomCard());
        List<Card> cd1 = new ArrayList<>();
        cd1.add(new Card(5, 'S'));
        cd1.add(new Card(2, 'S'));
        cd1.add(new Card(3, 'S'));
        cd1.add(new Card(1, 'S'));
        System.out.println(blDeck);
    }
}
