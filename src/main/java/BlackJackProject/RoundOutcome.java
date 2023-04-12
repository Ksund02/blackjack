package blackjackproject;

public enum RoundOutcome {
    
    BLACKJACK("Blackjack!"),
    WIN("You win!"),
    TIE("Tied!"),
    LOSS("You lost!");

    private final String displayText;

    private RoundOutcome(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }

}
