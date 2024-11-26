package com.example.uilayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OwnerController {
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
    private Button buttonFarmExpenses;

    @FXML
    private Button buttonHealthReport;

    @FXML
    private Button buttonMonitorFarm;

    @FXML
    private Button buttonSalesReport;


    @FXML
    void onClickbuttonFarmExpenses(ActionEvent event) throws IOException {
        Stage stage = (Stage) buttonFarmExpenses.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("farmexpenses.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Cow Catalogue");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onClickbuttonMonitorFarm(ActionEvent event) throws IOException {
        Stage stage = (Stage) buttonMonitorFarm.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("monitorfarm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Cow Catalogue");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onClickbuttonSalesReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) buttonSalesReport.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("salesreport.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Cow Catalogue");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onClickbuttonbuttonHealthReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) buttonHealthReport.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("healthreport.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Cow Catalogue");
        stage.setScene(scene);
        stage.show();
    }
}
