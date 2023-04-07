package blackjackproject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController {
    
    @FXML Button loadGameButton;

    private SceneController sceneController = new SceneController();
    protected CardGame cardGame = new CardGame(200, 6);

    @FXML
    public void initialize() {
        //cardGame = new CardGame(200, 6);
        
        if (cardGame.getFileIO().fileEmpty()) {
            loadGameButton.setDisable(true);
        } else {
            loadGameButton.setDisable(false);
        }
    }

    public void switchToNewGame(ActionEvent event) throws IOException {
        if (!cardGame.getFileIO().fileEmpty()) {
            cardGame.getFileIO().deleteFileContent();
        }
        sceneController.switchToNewGame(event);
    }

    public void switchToLoadedGame(ActionEvent event) throws IOException {
        sceneController.switchToNewGame(event);
    }

    public SceneController getSceneController() {
        return sceneController;
    }

}
