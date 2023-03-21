package blackjackproject;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BlackjackApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        Scene startScreen = new Scene(parent);
        Image logo = new Image(getClass().getResourceAsStream("Blackjack.png"));
        
        stage.getIcons().add(logo);
        stage.setTitle("Blackjack");
        stage.setScene(startScreen);
        stage.show();
    }

    public static void main(String[] args) {
        launch(BlackjackApp.class, args);
    }

}