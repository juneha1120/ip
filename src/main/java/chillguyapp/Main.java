package chillguyapp;

import java.io.IOException;

import chillguy.main.ChillGuy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final ChillGuy chillGuy = new ChillGuy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(chillguyapp.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("ChillGuy - Your Chill Chatbot");
            stage.setScene(scene);
            stage.setMinHeight(420);
            stage.setMinWidth(420);
            fxmlLoader.<MainWindow>getController().setChillGuy(chillGuy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
