package BlackjackProject;
import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private List<Card> Deck;

    public CardDeck() {
        this.Deck = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.Deck.add(card);
    }

    public void removeCard(Card card) {
        this.Deck.remove(card);
    }

    public static void main(String[] args) {
        CardDeck blDeck = new CardDeck();
        for (int face=1;face<14;face++) {
            for (int suit=0;suit<4;suit++) {
                
                blDeck.addCard(new Card(face, validSu));
            }
        }
    }
}
