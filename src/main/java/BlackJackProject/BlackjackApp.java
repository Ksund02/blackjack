package blackjackproject;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class BlackjackApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        Scene startScreen = new Scene(parent);
        Image logo = new Image(getClass().getResourceAsStream("Blackjack.png"));
        
        playMusic("JazzOOP.mp3");
        stage.getIcons().add(logo);
        stage.setTitle("Blackjack");
        stage.setScene(startScreen);
        stage.show();
    }

    MediaPlayer mediaPlayer;
    public void playMusic(String fileName) throws FileNotFoundException {
        URL filePath = getClass().getResource(fileName);
        mediaPlayer = new MediaPlayer(new Media(filePath.toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch(BlackjackApp.class, args);
    }

}