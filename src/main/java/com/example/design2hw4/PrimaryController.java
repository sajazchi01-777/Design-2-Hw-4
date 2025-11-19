package com.example.design2hw4;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class PrimaryController implements Initializable
{

    @FXML private TableView<HorrorCharacter> tblHorrorChar;
    @FXML private TableColumn<HorrorCharacter, String> clmName;
    @FXML private TableColumn<HorrorCharacter, String> clmSubtype;
    @FXML private TableColumn<HorrorCharacter, LocalDate> clmRebirth;

    @FXML private Button btnDelete;
    @FXML private Button btnCreate;
    @FXML private ProgressIndicator progInd;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<HorrorCharacter> horrorCharList = FXCollections.observableArrayList();

    private void createButtonPressed(ActionEvent event) throws IOException
    {
        openSecondary();
    }

    @FXML public void openSecondary() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        root = loader.load();
        SecondaryController secController = loader.getController();
        secController.setPrimaryController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public ObservableList<HorrorCharacter> getHorrorChar()
    {
        return horrorCharList;
    }

    public void addHorrorCharacter(HorrorCharacter hc)
    {
        horrorCharList.add(hc);
    }

    @FXML
    public void deleteButtonPressed(ActionEvent e)
    {
        ObservableList<HorrorCharacter> allHorrorChars = tblHorrorChar.getItems();
        ObservableList<HorrorCharacter> selectedRows = tblHorrorChar.getSelectionModel().getSelectedItems();

        if (selectedRows.isEmpty()) {
            return;
        }

        progInd.setProgress(0);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(20), event -> {
                    double progress = progInd.getProgress();
                    progInd.setProgress(progress + 0.01);
                })
        );
        timeline.setCycleCount(100);

        timeline.setOnFinished(event -> {
            allHorrorChars.removeAll(selectedRows);

            Timeline pause = new Timeline(new KeyFrame(Duration.seconds(0.5), e2 -> progInd.setProgress(0)));
            pause.play();
        });

        timeline.play();
    }

    private void showEditProgress() {
        progInd.setProgress(0);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(20), event -> {
                    double progress = progInd.getProgress();
                    progInd.setProgress(progress + 0.01);
                })
        );

        timeline.setCycleCount(100);
        timeline.play();
        progInd.setProgress(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tblHorrorChar.setEditable(true);
        horrorCharList = FXCollections.observableArrayList();

        btnDelete.setDisable(true);

        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmSubtype.setCellValueFactory(new PropertyValueFactory<>("subtype"));
        clmRebirth.setCellValueFactory(new PropertyValueFactory<>("dateOfRebirth"));

        tblHorrorChar.setItems(horrorCharList);


        clmName.setCellFactory(TextFieldTableCell.forTableColumn());
        clmSubtype.setCellFactory(TextFieldTableCell.forTableColumn());
        clmRebirth.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));


        clmName.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
            showEditProgress();
        });

        clmSubtype.setOnEditCommit(event -> {
            event.getRowValue().setSubtype(event.getNewValue());
            showEditProgress();
        });

        clmRebirth.setOnEditCommit(event -> {
            event.getRowValue().setDateOfRebirth(event.getNewValue());
            showEditProgress();
        });

        tblHorrorChar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            btnDelete.setDisable(newSelection == null);
        });

        horrorCharList.add(new HorrorCharacter("Dracula", "Vampire", LocalDate.of(1897, 5, 26)));
        horrorCharList.add(new HorrorCharacter("Zombie Bob", "Zombie", LocalDate.of(2001, 10, 31)));
        horrorCharList.add(new HorrorCharacter("Spooky Wraith", "Ghost", LocalDate.of(1999, 8, 15)));
        horrorCharList.add(new HorrorCharacter("Wolfgang", "Werewolf", LocalDate.of(1985, 4, 3)));
        horrorCharList.add(new HorrorCharacter("Azazel", "Demon", LocalDate.of(1700, 12, 25)));
        horrorCharList.add(new HorrorCharacter("Vampira", "Vampire", LocalDate.of(1920, 6, 1)));
        horrorCharList.add(new HorrorCharacter("Rotting Ralph", "Zombie", LocalDate.of(2015, 9, 14)));
        horrorCharList.add(new HorrorCharacter("Ghostly Gwen", "Ghost", LocalDate.of(2005, 3, 22)));
        horrorCharList.add(new HorrorCharacter("Lycan Larry", "Werewolf", LocalDate.of(1995, 11, 11)));
        horrorCharList.add(new HorrorCharacter("Lilith", "Demon", LocalDate.of(1600, 1, 1)));

        tblHorrorChar.setItems(horrorCharList);
    }
}

