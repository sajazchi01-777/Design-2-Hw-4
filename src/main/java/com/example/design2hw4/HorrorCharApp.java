package com.example.design2hw4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main App
 */

public class HorrorCharApp extends Application
{

    /**
     * Scene variable
     */

    private static Scene scene;

    /**
     * Method that starts the window and shows it
     * @param stage
     * @throws IOException
     */

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(loadFXML("primary"), 600, 520);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("HorrorCharacter App");
        stage.show();
    }

    /**
     * Method that sets root
     * @param fxml
     * @throws IOException
     */

    static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Method that loads the fxml file, returns the method that loads it.
     * @param fxml
     * @return
     * @throws IOException
     */

    public static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HorrorCharApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main, launches the application
     * @param args
     */

    public static void main(String[] args)
    {
        launch();
    }
}