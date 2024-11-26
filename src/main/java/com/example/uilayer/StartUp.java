package com.example.uilayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StartUp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartUp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 570, 370);
        stage.setTitle("Welcome to FarmSuite!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}