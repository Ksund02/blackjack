package BlackjackProject;

public class Card {
    
    private static final String validSuits = "SHDC";
    private int face;
    private char suit;

    public Card(int face, char suit) {
        setFace(face);
        setSuit(suit);
    }

    private boolean validSuit(char suit) {
        return (validSuits.contains(""+suit));
    }

    private boolean validFace(int face) {
        return face>0 && face <14;
    }

    private void setFace(int face) {
        if (validFace(face)) {
            this.face=face;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private void setSuit(char suit) {
        if (validSuit(suit)) {
            this.suit=suit;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}