package blackjackproject;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    private Parent parent;
    private Scene scene;
    private Stage stage;

    protected void switchToNewGame(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("NewGame.fxml"));
        scene = new Scene(parent);
        setNewScene(event, scene);
    }

    protected void switchToSavedGame(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("SavedGame.fxml")); // Kan komme endringer, skal hente tilstand fra fil
        scene = new Scene(parent);
        setNewScene(event, scene);
    }

    protected void switchToStartScreen(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        scene = new Scene(parent);
        setNewScene(event, scene);
    }

    private void setNewScene(ActionEvent event, Scene newScene) {
        Node eventSource = (Node) event.getSource();
        stage = (Stage) eventSource.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
