package blackjackproject;

public class Card {
    
    public static final String validSuits = "SHDC";
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