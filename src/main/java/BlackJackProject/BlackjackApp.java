package blackjackproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BlackjackApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        Scene startScreen = new Scene(parent);
        stage.setScene(startScreen);
        stage.show();
    }

    public static void main(String[] args) {
        launch(BlackjackApp.class, args);
    }

}