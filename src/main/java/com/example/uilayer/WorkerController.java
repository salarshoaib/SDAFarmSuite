package com.example.uilayer;

import com.example.databaselayer.FSDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class WorkerController {

    String user;
    @FXML
    public void setUser(String s){
        user=s;
    }

    @FXML
    private Button backButtonLogIn;

    @FXML
    void onClickBackButtonLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButtonLogIn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Welcome to FarmSuite!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Button dailyTaskButton;
    @FXML
    private Button milkCowsButton;
    @FXML
    void onClickdailyTaskButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dailytasks.fxml"));
        Parent root = fxmlLoader.load();
        System.out.println("worker"+user);
        DailyTasks dt = fxmlLoader.getController();
        dt.setUser(user);
        Stage stage = (Stage) dailyTaskButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("These are your Daily Tasks!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void onClickmilkCowsButton() throws IOException {
        Stage stage = (Stage) milkCowsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("milkcows.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Cow Catalogue");
        stage.setScene(scene);
        stage.show();
    }

}
