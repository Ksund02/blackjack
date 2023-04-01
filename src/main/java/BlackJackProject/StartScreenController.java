package blackjackproject;

import java.io.IOException;

import javafx.event.ActionEvent;

public class StartScreenController {
    
    private SceneController sceneController;

    public StartScreenController() {
        sceneController = new SceneController();
    }

    public void switchToNewGame(ActionEvent event) throws IOException {
        sceneController.switchToNewGame(event);
    }

}
