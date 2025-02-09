package chillguyapp;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an {@code ImageView} to represent the speaker's face
 * and a {@code Label} containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a {@code DialogBox} with the specified text and image.
     * Loads the associated FXML layout and initializes the UI components.
     *
     * @param text The message to be displayed in the dialog.
     * @param img  The image representing the speaker; can be {@code null}.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(chillguyapp.MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        if (img != null) {
            displayPicture.setImage(img);
        } else {
            this.getChildren().remove(displayPicture);
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a {@code DialogBox} representing the user's message.
     * Since the user does not have an associated image, this dialog box will not display an image.
     *
     * @param text The user's message to be displayed.
     * @return A {@code DialogBox} instance containing the user's text.
     */
    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, null);
    }

    /**
     * Creates a {@code DialogBox} representing ChillGuy's (the chatbot's) response.
     * The image provided will be used as the chatbot's avatar.
     *
     * @param text The chatbot's response message.
     * @param img  The image representing ChillGuy (the chatbot).
     * @return A {@code DialogBox} instance containing ChillGuy's response.
     */
    public static DialogBox getChillGuyDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
