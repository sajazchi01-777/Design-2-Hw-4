package com.example.design2hw4;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable
{

    @FXML private TextField txtName;
    @FXML private ComboBox<String> cmbSubtype;
    @FXML private DatePicker dateRebirth;
    @FXML private ProgressBar progBar;
    @FXML private Label successText;
    @FXML private Button btnFinish;
    private PrimaryController primaryController;

    @FXML
    public void finishButtonPressed()
    {
        String name = txtName.getText();
        String subtype = cmbSubtype.getValue();
        LocalDate dateOfRebirth = dateRebirth.getValue();

        if (name == null || name.isBlank() || subtype == null || dateOfRebirth == null)
        {
            successText.setText("Please fill in all fields!");
            return;
        }

        HorrorCharacter newHorrorCharacter = new HorrorCharacter(name, subtype, dateOfRebirth);


        btnFinish.setDisable(true);


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(progBar.progressProperty(), 1))
        );

        timeline.setOnFinished(e -> {
            primaryController.addHorrorCharacter(newHorrorCharacter);
            successText.setText("Character added successfully!");
            btnFinish.getScene().getWindow().hide();
        });

        timeline.play();
    }


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
