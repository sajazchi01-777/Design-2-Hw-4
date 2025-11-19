package com.example.design2hw4;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * SecondaryController class, controls the secondary view
 */

public class SecondaryController implements Initializable
{

    /**
     * Variables for all components of the secondary view
     */

    @FXML private TextField txtName;
    @FXML private ComboBox<String> cmbSubtype;
    @FXML private DatePicker dateRebirth;
    @FXML private ProgressBar progBar;
    @FXML private Label successText;
    @FXML private Button btnFinish;
    private PrimaryController primaryController;

    /**
     * Method for when the finish button is pressed
     */

    @FXML
    public void finishButtonPressed()
    {
        /**
         * Variables for where info is entered to create a new character
         */

        String name = txtName.getText();
        String subtype = cmbSubtype.getValue();
        LocalDate dateOfRebirth = dateRebirth.getValue();

        /**
         * Checking to make sure fields have something in them
         */

        if (name == null || name.isBlank() || subtype == null || dateOfRebirth == null)
        {
            successText.setText("Please fill in all fields!");
            return;
        }

        /**
         * Creation of new horror character
         */

        HorrorCharacter newHorrorCharacter = new HorrorCharacter(name, subtype, dateOfRebirth);

        /**
         * Finish button is set disabled so it cannot be pressed again
         */


        btnFinish.setDisable(true);

        /**
         * Code to have the progress bar go through an animation when a new character is made
         */


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(progBar.progressProperty(), 1))
        );

        /**
         * Code that handles what happens after the animation plays
         */

        timeline.setOnFinished(e -> {
            primaryController.addHorrorCharacter(newHorrorCharacter);
            successText.setText("Character added successfully!");
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(actionEvent -> btnFinish.getScene().getWindow().hide());
            pause.play();
        });

        timeline.play();
    }

    /**
     * Initialize method for secondary view, sets the different subtypes of monsters into the combo box(I took the liberty to add a few)
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        cmbSubtype.getItems().addAll("Vampire", "Werewolf", "Zombie", "Ghost", "Demon");
    }

    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    public PrimaryController getPrimaryController() {
        return primaryController;
    }


}
