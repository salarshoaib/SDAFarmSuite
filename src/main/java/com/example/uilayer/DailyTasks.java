package com.example.uilayer;

import com.example.databaselayer.FSDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DailyTasks {
    String user;
    @FXML
    void setUser(String s){
        user=s;
        start();
    }
    @FXML
    public void start() {
        System.out.println("here"+user);
        loadTaskData(user);
    }
    @FXML
    private Label statusLabel;
    private void loadTaskData(String username) {
        try (Connection connectDB = new FSDatabase().getConnection()) {
            // Query to fetch sender, task, message, and reply for the given user
            String query = "SELECT sender, task, message, reply, status FROM dailytasks WHERE receiver = ?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                // Set the values from the database into the UI components
                String sender = rs.getString("sender");
                String task = rs.getString("task");
                String message = rs.getString("message");
                String reply = rs.getString("reply");
                String status = rs.getString("status");
                fromLabel.setText("- " +sender);
                todaysTaskLabel.setText(task);
                senderTextArea.setText(message);
                replyTextArea.setText("");
                statusLabel.setText(status);
            } else {
                System.out.println("No tasks found for user: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button backButtonWorkerHomePage;
    @FXML
    void onClickbackButtonWorkerHomePage() throws IOException {
        Stage stage = (Stage) backButtonWorkerHomePage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workerhomepage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Welcome Worker!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Button buttonDone;

    @FXML
    private Button buttonSubmit;

    @FXML
    private TextArea replyTextArea;

    @FXML
    private Label todaysTaskLabel;
    @FXML
    private TextArea senderTextArea;
    @FXML
    void onClickbuttonDone(ActionEvent event) {
        String sender =user;

        try (Connection connectDB = new FSDatabase().getConnection()) {
            String query = "UPDATE dailytasks SET status = ? WHERE receiver = ?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, "DONE");
            preparedStatement.setString(2, sender);
            statusLabel.setText("DONE");
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Status updated successfully.");
            } else {
                System.out.println("No task found to submit a reply.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Label fromLabel;
    @FXML
    void onClickbuttonSubmit(ActionEvent event) {
        String reply = replyTextArea.getText(); // Get the reply from the text area
        String sender =user;

        try (Connection connectDB = new FSDatabase().getConnection()) {
            String query = "UPDATE dailytasks SET reply = ? WHERE receiver = ?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, reply);
            preparedStatement.setString(2, sender);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reply submitted successfully.");
            } else {
                System.out.println("No task found to submit a reply.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
