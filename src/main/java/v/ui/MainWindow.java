package v.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import v.V;

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

    private V v;
    private BackgroundManager backgroundManager;
    @FXML
    private AnchorPane rootPane; // fx:id bound in FXML

    private Image userImage = AvatarManager.getRandomUserAvatar();
    private final Image dukeImage = AvatarManager.getVAvatar();

    /**
     * Initializes the GUI components and sets up the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Initialize background manager on the real root pane (not the controller instance)
        AnchorPane target = (rootPane != null) ? rootPane : (AnchorPane) this;
        backgroundManager = new BackgroundManager(target);

        // Focus the text field on load so keyboard works immediately
        javafx.application.Platform.runLater(() -> userInput.requestFocus());

        // Add V's dramatic welcome message with delays - using original CLI text
        addWelcomeMessageWithDelay("Voilà! In view, a voice of the vox populi.", 0);
        addWelcomeMessageWithDelay("Behold—the visage behind the letter.", 1500);
        addWelcomeMessageWithDelay("A humble vessel of verbosity and vigilance.", 3000);
        addWelcomeMessageWithDelay("I am V. Voice for the voiceless. What do you require?", 4500);
    }

    /**
     * Injects the V instance.
     *
     * @param v The V instance to inject.
     */
    public void setV(V v) {
        this.v = v;
    }

    /**
     * Adds a welcome message with a delay for dramatic effect.
     *
     * @param message The message to display.
     * @param delayMs The delay in milliseconds.
     */
    private void addWelcomeMessageWithDelay(String message, int delayMs) {
        javafx.animation.Timeline timeline = new javafx.animation.Timeline();
        timeline.getKeyFrames().add(
            new javafx.animation.KeyFrame(javafx.util.Duration.millis(delayMs), e -> {
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
            })
        );
        timeline.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing V's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = v.getResponse(input);
        String commandType = v.getCommandType();
        if (input == null) {
            return;
        }
        // Get a new random user avatar for each message
        userImage = AvatarManager.getRandomUserAvatar();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, commandType)
        );
        userInput.clear();
        // Check if this is an exit command
        if (commandType != null && commandType.equals("ExitCommand")) {
            // Disable input to prevent further commands
            userInput.setDisable(true);
            sendButton.setDisable(true);
            
            // Use Timeline to delay exit without blocking the UI thread
            javafx.animation.Timeline exitTimeline = new javafx.animation.Timeline();
            exitTimeline.getKeyFrames().add(
                new javafx.animation.KeyFrame(javafx.util.Duration.seconds(4), e -> {
                    javafx.application.Platform.exit();
                })
            );
            exitTimeline.play();
        }
    }
}
