package blackjackproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlackjackController extends SceneController {

    @FXML Button increaseButton, decreaseButton, betButton, hitButton, passButton, nextRoundButton, saveGameButton;

    @FXML Label betAmountLabel, balanceLabel, roundStatusLabel, finalTextLabel;

    @FXML ImageView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6;
    @FXML ImageView playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6;
    
    private CardGame cardGame;
    private int nextImageView;
    private List<ImageView> allImageViews;

    @FXML
    public void initialize() {
        cardGame = new CardGame(200, 6);
        nextImageView = 3;

        allImageViews = new ArrayList<>(
            Arrays.asList(dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, 
                playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6)
        );
    }

    public void increaseBet() {
        cardGame.increaseBet();
        betAmountLabel.setText(cardGame.getCurrentBet() + "$");

        if (cardGame.getCurrentBet() == cardGame.getBalance()) {
            increaseButton.setDisable(true);
        } else if (decreaseButton.isDisabled()) {
            decreaseButton.setDisable(false);
            betButton.setDisable(false);
        }
    }

    public void decreaseBet() {
        cardGame.decreaseBet();
        betAmountLabel.setText(cardGame.getCurrentBet() + "$");

        if (cardGame.getCurrentBet() == 0) {
            decreaseButton.setDisable(true);
            betButton.setDisable(true);
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
        sleepGame(200);
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

    public void dealerTurn() {
        cardGame.dealerPlaysHand();
        int index = 0;
        List<ImageView> dealerImageView = new ArrayList<>(
            Arrays.asList(dealerCard3, dealerCard4, dealerCard5)
        );
        List<Card> dealerCardsToPlay = cardGame.getDealerHand().subList(2, cardGame.getDealerHand().size());

        for (Card dealerCard : dealerCardsToPlay) {
            if (index < 3) {
                setCardSpot(dealerImageView.get(index), dealerCard.toString());
            } else {
                setCardSpot(dealerCard6, dealerCard.toString());  
            }
            index++;
        }
        setCardSpot(dealerCard1, cardGame.getDealerHand().get(0).toString());
    }

    public void nextRound(Event event) {
        cardGame.resetCardGame();
        nextRoundButton.setVisible(false);
        roundStatusLabel.setText("");
        increaseButton.setDisable(false);
        nextImageView = 3;
        
        for (ImageView imageView : allImageViews) {
            setCardSpot(imageView, "EmptyCard.png");
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
        dealerTurn();
        String roundOutcome = cardGame.roundOutcome();

        switch(roundOutcome) {
            case "blackjack":
                roundStatusLabel.setText("Blackjack!");
                break;
            case "won":
                roundStatusLabel.setText("You won!");
                break;
            case "tie":
                roundStatusLabel.setText("Tied!");
                break;
            case "lost":
                roundStatusLabel.setText("You lost!");
                break;
        }
        cardGame.distributeMoney(roundOutcome);
        balanceLabel.setText("Balance: " + cardGame.getBalance() + "$");
        betAmountLabel.setText("0$");
        
        if (cardGame.cardGameLost()) {
            finalTextLabel.setText("Game lost!");
            saveGameButton.setDisable(true);
        } else {
            nextRoundButton.setVisible(true);
        }
    }

    public void saveGame() {

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
