package com.example.uilayer;

import com.example.databaselayer.FSDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    @FXML
    private Button submitButtonLogIn;
    @FXML
    private PasswordField usernamePasswordField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label labelLogin;

    @FXML
    void onClickSubmitButtonLogin(ActionEvent event) throws SQLException, IOException {
        String LoginUsername=usernameTextField.getText();
        String LoginPassword=usernamePasswordField.getText();
        String s="";
        FSDatabase connect = new FSDatabase();
        Connection connectDB = connect.getConnection();
        Statement st = connectDB.createStatement();
        String query = ("select title from users where username='" + LoginUsername + "' AND password='" + LoginPassword + "'");
        ResultSet rs = st.executeQuery(query);
        if(rs.next())
        {
            s=rs.getString("title");
        }
        if (s.equals("OWNER")) {
            Stage stage = (Stage) submitButtonLogIn.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ownerhomepage.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Welcome Owner!");
            stage.setScene(scene);
            stage.show();
        } else if (s.equals("WORKER")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workerhomepage.fxml"));
            Parent root = fxmlLoader.load();
            WorkerController wc = fxmlLoader.getController();
            wc.setUser(LoginUsername);
            Stage stage = (Stage) submitButtonLogIn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Welcome Worker!");
            stage.setScene(scene);
            stage.show();
        } else if (s.equals("MANAGER")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerhomepage.fxml"));
            Parent root = fxmlLoader.load();
            ManagerController mc =fxmlLoader.getController();
            mc.setUser(LoginUsername);
            Stage stage = (Stage) submitButtonLogIn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Welcome Manager!");
            stage.setScene(scene);
            stage.show();
        } else if (s.equals("SECONDARY")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondaryactorhomepage.fxml"));
            Parent root = fxmlLoader.load();
            SecondaryActorController SAC = fxmlLoader.getController();
            SAC.setUsername(LoginUsername);
            Stage stage = (Stage) submitButtonLogIn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Welcome to your Dashboard!");
            stage.setScene(scene);
            stage.show();
        } else if (s.equals("SHOPKEEPER")) {
            Stage stage = (Stage) submitButtonLogIn.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shopkeeperhomepage.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Welcome to Shop!");
            stage.setScene(scene);
            stage.show();
        }else {
            labelLogin.setText("Incorrect Details! Please re-enter!");
            usernamePasswordField.setText("");
            usernamePasswordField.setText("");
        }
    }

}

