package com.example.uilayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
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
    private Button MessageVet;
    @FXML
    private Button buttonAssignTasks;

    @FXML
    private Button buttonProductionReport;

    @FXML
    private Button buttonWasteCollection;


    @FXML
    void onClickMessageVet(ActionEvent event) throws IOException {
        Stage stage = (Stage) MessageVet.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("messagevet.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Send a Message to the Vet here!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onClickbuttonAssignTasks(ActionEvent event) throws IOException {
        Stage stage = (Stage) buttonAssignTasks.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerassignstasks.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Assign Workers Tasks!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onClickbuttonProductionReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) MessageVet.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productionreport.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Milk Production Report!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onClickbuttonWasteCollection(ActionEvent event) throws IOException {
        Stage stage = (Stage) MessageVet.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("messagewastedisposal.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Send a Request for waste collection!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Button buttonIssueReport;
    @FXML
    void onClickbuttonIssueReport() throws IOException {
        Stage stage = (Stage) MessageVet.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reportowner.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Report Issues to Owner here!");
        stage.setScene(scene);
        stage.show();
    }
}
