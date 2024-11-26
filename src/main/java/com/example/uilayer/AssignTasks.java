package com.example.uilayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AssignTasks {
    @FXML
    private Button backButtonManagerHomepage;
    @FXML
    public void onClickBackButtonManagerHomePage() throws IOException {
        Stage stage = (Stage) backButtonManagerHomepage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerhomepage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Welcome Manager!");
        stage.setScene(scene);
        stage.show();
    }
}
