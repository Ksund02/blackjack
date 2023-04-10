package blackjackproject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController extends SceneController {
    
    @FXML Button loadGameButton;

    @FXML
    public void initialize() {
        if (FileIO.fileIsEmpty()) {
            loadGameButton.setDisable(true);
        } else {
            loadGameButton.setDisable(false);
        }
    }

    public void switchToNewGame(ActionEvent event) throws IOException {
        if (!FileIO.fileIsEmpty()) {
            FileIO.deleteFileContent();
        }
        super.switchToNewGame(event);
    }

    public void switchToLoadedGame(ActionEvent event) throws IOException {
        super.switchToNewGame(event);
    }

}
