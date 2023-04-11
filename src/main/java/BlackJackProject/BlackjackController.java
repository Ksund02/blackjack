package blackjackproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
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
    private int nextPlayerImageView;
    private int nextDealerImageView;
    private List<ImageView> playerImageViews;
    private List<ImageView> dealerImageViews;
    
    @FXML
    public void initialize() {
        cardGame = new CardGame(200, 6);
        playerImageViews = new ArrayList<>(Arrays.asList(
            playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6
        ));
        dealerImageViews = new ArrayList<>(Arrays.asList(
            dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6
        ));

        if (!FileIO.fileIsEmpty()) {
            loadGame();
        }
    }

    public void loadGame() {
        cardGame.setCardGame(FileIO.readFromFile());
       
        for (Card card : cardGame.getDealer().getCardHand()) {
            setCardSpot(dealerImageViews.get(nextDealerImageView), card.toString());
            nextDealerImageView++;
        }

        for (Card card : cardGame.getPlayer().getCardHand()) {
            setCardSpot(playerImageViews.get(nextPlayerImageView), card.toString());
            nextPlayerImageView++;
        }

        balanceLabel.setText("Balance: " + cardGame.getPlayer().getBalance() + "$");
        betAmountLabel.setText(cardGame.getPlayer().getCurrentBet() + "$");

        if (cardGame.getPlayer().hasEndedRound()) {
            increaseButton.setDisable(true);
            decreaseButton.setDisable(true);
            betButton.setDisable(true);
            nextRoundButton.setVisible(true);
            roundStatusLabel.setText(cardGame.roundOutcome().getDisplayText());
        } else if (cardGame.getPlayer().hasPlacedBet()) {
            increaseButton.setDisable(true);
            decreaseButton.setDisable(true);
            betButton.setDisable(true);
            hitButton.setDisable(false);
            passButton.setDisable(false);
            setCardSpot(dealerCard1, "BacksideCard.png");
        } else {
            //The player is deciding the bet amount
            boolean playerBetEntireBalance = cardGame.getPlayer().getCurrentBet() == cardGame.getPlayer().getBalance();
            boolean playerBetSomething = cardGame.getPlayer().getCurrentBet() != 0;
            if (playerBetEntireBalance) {
                betButton.setDisable(false);
                increaseButton.setDisable(true);
                decreaseButton.setDisable(false);
            } else if (playerBetSomething) {
                betButton.setDisable(false);
                decreaseButton.setDisable(false);
            }
        }
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
        cardGame.getPlayer().setHasPlacedBet(true);
        balanceLabel.setText("Balance: " + cardGame.getPlayer().getBalance() + "$");
        betButton.setDisable(true);
        increaseButton.setDisable(true);
        decreaseButton.setDisable(true);
        startGame();
    }

    private void startGame() {
        sleepGame(200);
        cardGame.setStartingCardHands();
        setCardSpot(dealerCard1, "BacksideCard.png");
        setCardSpot(dealerCard2, cardGame.getDealer().getCardHand().get(1).toString());
        setCardSpot(playerCard1, cardGame.getPlayer().getCardHand().get(0).toString());
        setCardSpot(playerCard2, cardGame.getPlayer().getCardHand().get(1).toString());
        nextPlayerImageView += 2;
        nextDealerImageView += 2;
        
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

        switch(nextPlayerImageView) {
            case 2:
                setCardSpot(playerCard3, newCard.toString());
                break;
            case 3:
                setCardSpot(playerCard4, newCard.toString());
                break;
            case 4:
                setCardSpot(playerCard5, newCard.toString());
                break;
            case 5:
                setCardSpot(playerCard6, newCard.toString());
                nextPlayerImageView--;
                break;
            default:
                throw new IllegalAccessError("nextImageView not accessible!" + nextPlayerImageView);
        }
        nextPlayerImageView++;

        if (cardGame.roundOver()) {
            endRound();
        }
    }

    public void passButtonPressed() {
        endRound();
    }

    private void dealerTurn() {
        cardGame.dealerPlaysHand();
        List<Card> dealerCardsToPlay = cardGame.getDealer().getCardHand().subList(2, cardGame.getDealer().getCardHandSize());

        for (Card dealerCard : dealerCardsToPlay) {
            if (nextDealerImageView < 5) {
                setCardSpot(dealerImageViews.get(nextDealerImageView), dealerCard.toString());
            } else {
                setCardSpot(dealerCard6, dealerCard.toString());  
            }
            nextDealerImageView++;
        }
        setCardSpot(dealerCard1, cardGame.getDealer().getCardHand().get(0).toString());
    }

    public void endRound() {
        cardGame.getPlayer().setHasEndedRound(true);
        hitButton.setDisable(true);
        passButton.setDisable(true);

        if (cardGame.roundOutcome().equals(RoundOutcome.BLACKJACK)) {
            setCardSpot(dealerCard1, cardGame.getDealer().getCardHand().get(0).toString());
        } else if (cardGame.getHandValue(cardGame.getPlayer().getCardHand()) < 22) {
            dealerTurn();
        }
        RoundOutcome roundOutcome = cardGame.roundOutcome();
        roundStatusLabel.setText(roundOutcome.getDisplayText());
        cardGame.distributeMoney(roundOutcome);
        balanceLabel.setText("Balance: " + cardGame.getPlayer().getBalance() + "$");
        betAmountLabel.setText("0$");
        
        if (cardGame.gameLost()) {
            finalTextLabel.setText("Game lost!");
            roundStatusLabel.setText("");
            saveGameButton.setDisable(true);
            FileIO.deleteFileContent();
        } else {
            nextRoundButton.setVisible(true);
        }
    }

    public void nextRoundButtonPressed() {
        cardGame.resetCardGame();
        nextRoundButton.setVisible(false);
        roundStatusLabel.setText("");
        increaseButton.setDisable(false);
        nextPlayerImageView = 0;
        nextDealerImageView = 0;
        
        List<ImageView> allImageViews = new ArrayList<>();
        allImageViews.addAll(dealerImageViews);
        allImageViews.addAll(playerImageViews);
        for (ImageView imageView : allImageViews) {
            setCardSpot(imageView, "EmptyCard.png");
        }
    }

    public void saveGame() {
        FileIO.writeToFile(cardGame.getCurrentCardGame());
    }

    public void switchToStartScreen(ActionEvent event) throws IOException {
        super.switchToStartScreen(event);
    }

}