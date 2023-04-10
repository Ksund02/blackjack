package blackjackproject;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    protected void switchToNewGame(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("NewGame.fxml"));
        Scene scene = new Scene(parent);
        setNewScene(event, scene);
    }

    protected void switchToStartScreen(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        Scene scene = new Scene(parent);
        setNewScene(event, scene);
    }

    private void setNewScene(ActionEvent event, Scene newScene) {
        Node eventSource = (Node) event.getSource();
        Stage stage = (Stage) eventSource.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

}
