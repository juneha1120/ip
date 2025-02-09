package chillguyapp;

import chillguy.main.ChillGuy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private final Image chillGuyImage = new Image(this.getClass().getResourceAsStream("/images/ChillGuyImage.png"));

    /**
     * Initializes the GUI by setting up the scroll pane behavior.
     * Binds the vertical scroll value to the height of the dialog container so that
     * new messages are always visible.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
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
        userInput.clear();
    }
}


