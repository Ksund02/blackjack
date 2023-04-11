package blackjackproject;

public class Card {
    
    public static final String validSuits = "SHDC";
    private char suit;
    private int face;

    public Card(char suit, int face) {
        setSuit(suit);
        setFace(face);
    }

    private void setFace(int face) {
        if (face < 1 || face > 14) {
            throw new IllegalArgumentException("Illegal face!");
        }
        this.face = face;
    }

    private void setSuit(char suit) {
        if (validSuits.indexOf(suit) == -1) {
            throw new IllegalArgumentException("Illegal suit!");
        }
        this.suit = suit;
    }

    public int getFace() {
        return face;
    }

    public char getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "cards/" + suit + "" + face + ".png";
    }

}