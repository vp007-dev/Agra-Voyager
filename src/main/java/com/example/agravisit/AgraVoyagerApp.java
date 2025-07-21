package com.example.agravisit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class AgraVoyagerApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(AgraVoyagerApp.class.getResource("VoyagerView.fxml"));
        Parent root = fxmlLoader.load();

        // Create a new scene with the loaded layout.
        Scene scene = new Scene(root, 1000, 650);

        // Set the title of the application window.
        stage.setTitle("Agra Voyager");
        stage.setScene(scene);

        // Prevent the window from being resized to maintain the design.
        stage.setResizable(false);

        // Show the application window.
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}