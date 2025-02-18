package chillguyapp;

import java.util.Objects;

import chillguy.commands.ExitCommand;
import chillguy.main.ChillGuy;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ChillGuy chillGuy;

    private final Image chillGuyImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/ChillGuyImage.png")));

    /**
     * Initializes the GUI by setting up the scroll pane behavior.
     * Binds the vertical scroll value to the height of the dialog container so that
     * new messages are always visible.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());

        //@@author samuelneo-reused
        //Reused from https://github.com/samuelneo/ip/commit/a4318795662647afe657d1e5047e1c433c283896
        this.scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> scrollPane.vvalueProperty().unbind());
        //@@author
    }

    /** Injects the Chill Guy instance */
    public void setChillGuy(ChillGuy c) {
        this.chillGuy = c;
        this.handleOpen();
    }

    /**
     * Handles the initial greeting from ChillGuy when the application starts.
     * This method retrieves ChillGuy's initial response and displays it in the chat window.
     */
    private void handleOpen() {
        String response = this.chillGuy.startWithGUi();
        dialogContainer.getChildren().add(
                DialogBox.getChillGuyDialog(response, chillGuyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = this.chillGuy.getResponseWithGUi(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getChillGuyDialog(response, chillGuyImage)
        );

        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());

        String[] exitCheck = input.split(" ", 2);
        if (exitCheck[0].equalsIgnoreCase(ExitCommand.COMMAND_WORD)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            return;
        }

        userInput.clear();
    }
}
