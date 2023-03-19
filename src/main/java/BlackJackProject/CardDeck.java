package BlackjackProject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardDeck implements CardInterface {
    private List<Card> Deck;
    private final static Random rand = new Random();

    public CardDeck() {
        this.Deck = new ArrayList<>();
    }

    public CardDeck(int decks) {
        if (negativeError(decks)) {
            throw new IllegalArgumentException("Cannot have negative amount of carddecks");
        }
        this.Deck = new ArrayList<>();
        for (int i=0;i<decks;i++) {
            for (int face=1;face<14;face++) {
                for (int suit=0;suit<4;suit++) {
                    this.addCard(new Card(face, validSuits.charAt(suit)));
                }
            }
        }
    }

    private boolean negativeError(int i) {
        return i < 0;
    }

    public void addCard(Card card) {
        this.Deck.add(card);
    }

    public void removeCard(Card card) {
        this.Deck.remove(card);
    }

    public Card getRandomCard() {
        Card randomCard = this.Deck.get(rand.nextInt(this.Deck.size())); // Gets a random card (index 0, to upperbound is what rand.nextint does)
        this.Deck.remove(randomCard);
        return randomCard;
    }

    @Override
    public String toString() {
        return ""+this.Deck;
    }

    public static void main(String[] args) {
        CardDeck blDeck = new CardDeck(2);
        System.out.println(blDeck.getRandomCard());
        List<Card> cd1 = new ArrayList<>();
        cd1.add(new Card(5, 'S'));
        cd1.add(new Card(2, 'S'));
        cd1.add(new Card(3, 'S'));
        cd1.add(new Card(1, 'S'));
        Collections.sort(blDeck.Deck);
        System.out.println(blDeck);

    }
}
