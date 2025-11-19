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
     * Scene variable. A scene is the container within the window (stage).
     */

    private static Scene scene;

    /**
     * Method that starts the window and shows it
     * @param stage (window)
     * @throws IOException (will throw IOException into the void)
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
     * @param fxml (file that contains all the things that make up the view (made in SceneBuilder))
     * @throws IOException (will also throw IOException into the void)
     */

    static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Method that loads the fxml file, returns the method that loads it.
     * @param fxml (takes the name of the fxml file, finds it, then loads it.)
     * @return fxmlLoader.load(); (method that loads the fxml)
     * @throws IOException (will also throw IOException into the void)
     */

    public static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HorrorCharApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main, launches the application
     */

    public static void main(String[] args)
    {
        launch();
    }
}