package blackjackproject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BlackjackController extends SceneController {

    @FXML Button increaseButton;
    @FXML Button decreaseButton;
    @FXML Label betAmount;

    @FXML
    public void increaseBet() {
        
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
