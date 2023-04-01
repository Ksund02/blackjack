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

public class BlackjackController {

    @FXML Button increaseButton, decreaseButton, betButton, hitButton, passButton, nextRoundButton, saveGameButton;

    @FXML Label betAmountLabel, balanceLabel, roundStatusLabel, finalTextLabel;

    @FXML ImageView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6;
    @FXML ImageView playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6;
    
    private CardGame cardGame;
    private SceneController sceneController;
    private int nextImageView;
    private List<ImageView> allImageViews;

    @FXML
    public void initialize() {
        cardGame = new CardGame(200, 6);
        sceneController = new SceneController();
        nextImageView = 3;

        allImageViews = new ArrayList<>(
            Arrays.asList(dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, 
                playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6)
        );
    }

    public void increaseBet() {
        cardGame.getPlayer().increaseBet();
        betAmountLabel.setText(cardGame.getPlayer().getCurrentBet() + "$");

        if (cardGame.getPlayer().getCurrentBet() == cardGame.getPlayer().getBalance()) {
            increaseButton.setDisable(true);
        } else if (decreaseButton.isDisabled()) {
            decreaseButton.setDisable(false);
            betButton.setDisable(false);
        }
    }

    public void decreaseBet() {
        cardGame.getPlayer().decreaseBet();
        betAmountLabel.setText(cardGame.getPlayer().getCurrentBet() + "$");

        if (cardGame.getPlayer().getCurrentBet() == 0) {
            decreaseButton.setDisable(true);
            betButton.setDisable(true);
        } else if (increaseButton.isDisabled()) {
            increaseButton.setDisable(false);
        }
    }

    public void betButtonPressed() {
        cardGame.getPlayer().decreaseBalance(cardGame.getPlayer().getCurrentBet());
        balanceLabel.setText("Balance: " + cardGame.getPlayer().getBalance() + "$");
        betButton.setDisable(true);
        increaseButton.setDisable(true);
        decreaseButton.setDisable(true);
        startGame();
    }

    private void startGame() {
        sleepGame(200);
        cardGame.setPlayerStartingHand();
        cardGame.setDealerStartingHand();
        setCardSpot(dealerCard1, "BacksideCard.png");
        setCardSpot(dealerCard2, cardGame.getDealer().getCardHand().get(1).toString());
        setCardSpot(playerCard1, cardGame.getPlayer().getCardHand().get(0).toString());
        setCardSpot(playerCard2, cardGame.getPlayer().getCardHand().get(1).toString());
        
        if (cardGame.roundOver()) {
            endRound();
        } else {
            hitButton.setDisable(false);
            passButton.setDisable(false);
        }
    }

    private void setCardSpot(ImageView spot, String cardName) {
        Image cardImage = new Image(getClass().getResourceAsStream(cardName));
        spot.setImage(cardImage);
    }

    private void sleepGame(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("Exited program!");
        }
    }

    public void hitButtonPressed() {
        Card newCard = cardGame.getPlayer().drawCard(cardGame.getCardDeck());
        sleepGame(100);

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

    public void dealerTurn() {
        cardGame.dealerPlaysHand();
        int index = 0;
        List<ImageView> dealerImageView = allImageViews.subList(2, 5);
/*        
        new ArrayList<>(
            Arrays.asList(dealerCard3, dealerCard4, dealerCard5)
        );
*/
        List<Card> dealerCardsToPlay = cardGame.getDealer().getCardHand().subList(2, cardGame.getDealer().getCardHandSize());

        for (Card dealerCard : dealerCardsToPlay) {
            if (index < 3) {
                setCardSpot(dealerImageView.get(index), dealerCard.toString());
            } else {
                setCardSpot(dealerCard6, dealerCard.toString());  
            }
            index++;
        }
        setCardSpot(dealerCard1, cardGame.getDealer().getCardHand().get(0).toString());
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
        balanceLabel.setText("Balance: " + cardGame.getPlayer().getBalance() + "$");
        betAmountLabel.setText("0$");
        
        if (cardGame.gameLost()) {
            finalTextLabel.setText("Game lost!");
            roundStatusLabel.setText("");
            saveGameButton.setDisable(true);
        } else {
            nextRoundButton.setVisible(true);
        }
    }

    public void nextRoundButtonPressed(Event event) {
        cardGame.resetCardGame();
        nextRoundButton.setVisible(false);
        roundStatusLabel.setText("");
        increaseButton.setDisable(false);
        nextImageView = 3;
        
        for (ImageView imageView : allImageViews) {
            setCardSpot(imageView, "EmptyCard.png");
        }
    }

    public void saveGame() {
        cardGame.writeStateToFile();
    }

    public void switchToStartScreen(ActionEvent event) throws IOException {
        sceneController.switchToStartScreen(event);
    }

}
