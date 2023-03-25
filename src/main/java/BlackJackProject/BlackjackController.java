package blackjackproject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlackjackController extends SceneController {

    @FXML Button increaseButton, decreaseButton, betButton;

    @FXML Label betAmountLabel, balanceLabel;

    @FXML ImageView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6;
    @FXML ImageView playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6;
    

    private CardGame cardGame = new CardGame(200, 6);

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

    public void startGame() {
        sleepProgram(500);
        Image cardBackside = new Image(getClass().getResourceAsStream("BacksideCard.png"));
        dealerCard1.setImage(cardBackside);
    }

    private void sleepProgram(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("Exited program!");
        }
    }

    private void setCardSpot(ImageView spot) {
        Image cardImage = new Image(getClass().getResourceAsStream("BacksideCard.png"));
        spot.setImage(cardImage);
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
