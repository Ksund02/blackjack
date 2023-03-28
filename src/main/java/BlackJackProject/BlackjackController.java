package blackjackproject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlackjackController extends SceneController {

    @FXML Button increaseButton, decreaseButton, betButton, hitButton, passButton;

    @FXML Label betAmountLabel, balanceLabel;

    @FXML ImageView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6;
    @FXML ImageView playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6;
    
    private CardGame cardGame;
    private int nextImageView;

    @FXML
    public void initialize() {
        cardGame = new CardGame(200, 6);
        nextImageView = 3;
    }

    public void increaseBet() {
        cardGame.increaseBet();
        betAmountLabel.setText(cardGame.getCurrentBet() + "$");

        if (cardGame.getCurrentBet() == cardGame.getBalance()) {
            increaseButton.setDisable(true);
        } else if (decreaseButton.isDisabled()) {
            decreaseButton.setDisable(false);
        }
    }

    public void decreaseBet() {
        cardGame.decreaseBet();
        betAmountLabel.setText(cardGame.getCurrentBet() + "$");

        if (cardGame.getCurrentBet() == 10) {
            decreaseButton.setDisable(true);
        } else if (increaseButton.isDisabled()) {
            increaseButton.setDisable(false);
        }
    }

    public void betButtonPressed() {
        cardGame.setBalance(cardGame.getBalance() - cardGame.getCurrentBet());
        balanceLabel.setText("Balance: " + cardGame.getBalance() + "$");
        betButton.setDisable(true);
        increaseButton.setDisable(true);
        decreaseButton.setDisable(true);
        startGame();
    }

    public void hitButtonPressed() {
        Card newCard = cardGame.drawPlayerCard();

        switch(nextImageView) {
            case 3:
                setCardSpot(playerCard3, newCard.toString());
                break;
            case 4:
                setCardSpot(playerCard4, newCard.toString());
                break;
            case 5:
                setCardSpot(playerCard5, newCard.toString());
                break;
            case 6:
                setCardSpot(playerCard6, newCard.toString());
                nextImageView--;
                break;
            default:
                throw new IllegalAccessError("nextImageView not accessible!");
        }
        nextImageView++;

        if (cardGame.roundOver()) {
            endRound();
        }
    }

    public void passButtonPressed() {
        endRound();
    }

    private void startGame() {
        sleepGame(300);
        setCardSpot(dealerCard1, "BacksideCard.png");
        setCardSpot(dealerCard2, cardGame.getDealerHand().get(1).toString());
        setCardSpot(playerCard1, cardGame.getPlayerHand().get(0).toString());
        setCardSpot(playerCard2, cardGame.getPlayerHand().get(1).toString());
        
        if (cardGame.roundOver()) {
            endRound();
        } else {
            hitButton.setDisable(false);
            passButton.setDisable(false);
        }
    }

    private void sleepGame(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("Exited program!");
        }
    }

    private void setCardSpot(ImageView spot, String cardName) {
        Image cardImage = new Image(getClass().getResourceAsStream(cardName));
        spot.setImage(cardImage);
    }

    public void endRound() {
        hitButton.setDisable(true);
        passButton.setDisable(true);
        switch(cardGame.checkRoundOutcome()) {
            case "blackjack":
                break;
            case "won":
                break;
            case "tie":
                break;
            case "lost":
                break;
        }
    }

    @Override
    public void switchToNewGame(ActionEvent event) throws IOException {
        super.switchToNewGame(event);
    }

    @Override
    public void switchToStartScreen(ActionEvent event) throws IOException {
        super.switchToStartScreen(event);
    }

}
