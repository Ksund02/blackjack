package blackjackproject;

public class Card implements CardInterface, Comparable<Card> {
    private int face;
    private char suit;

    public Card(int face, char suit) {
        setFace(face);
        setSuit(suit);
    }

    private void setFace(int face) {
        if (face < 0 || face > 14) {
            throw new IllegalArgumentException("Illegal face!");
        }
        this.face = face;
    }

    private void setSuit(char suit) {
        if (!validSuits.contains(""+suit)) {
            throw new IllegalArgumentException("Illegal suit!");
        }
        this.suit=suit;
    }

    public int getFace() {
        return face;
    }

    public char getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return suit + face + ".png";
    }

    //Trenger vi å compare kort? Kanskje senere...
    @Override
    public int compareTo(Card c2) {
        return this.getFace() - c2.getFace();
    }

}
