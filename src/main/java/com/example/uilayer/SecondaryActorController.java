package com.example.uilayer;

import com.example.businesslayer.Message;
import com.example.databaselayer.FSDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SecondaryActorController {
    String username;
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

    public void setUsername(String loginUsername) {
        username = loginUsername;
        System.out.println(username);
        updateTable();
    }

    @FXML
    private TableView<Message> tableView;

    @FXML
    private TableColumn<Message, String> dateColumn;
    @FXML
    private TableColumn<Message, String> senderColumn;
    @FXML
    private TableColumn<Message, String> messageColumn;
    @FXML
    private TableColumn<Message, String> statusColumn;

    @FXML
    public void updateTable() {
        FSDatabase db = new FSDatabase();
        Connection conn = db.getConnection();
        ObservableList<Message> messages = FXCollections.observableArrayList();

        try {
            System.out.println(username);
            String query = "SELECT * FROM messages WHERE reciever = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Message message = new Message(
                        rs.getString("date"),
                        rs.getString("sender"),
                        rs.getString("message"),
                        rs.getString("status")
                );
                messages.add(message);
            }

            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
            messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            tableView.setItems(messages);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}