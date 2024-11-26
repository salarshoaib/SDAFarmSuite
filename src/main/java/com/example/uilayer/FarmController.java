package com.example.uilayer;

import com.example.businesslayer.Cows;
import com.example.businesslayer.Fodder;
import com.example.databaselayer.FSDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Random;

public class FarmController {
    Fodder availableFodder= new Fodder();
    Cows cowGroup= new Cows();
    int tempNum;
    java.util.Date currentDate;
    private static final Random rand = new Random();

    @FXML
    void initialize() {
        availableFodder.randomizeFodder();
        fodderGraphicSetUp();
        checkAndUpdateMilkProduction();
    }
    @FXML
    private ImageView fodderBarImage;
    @FXML
    private Label fodderLabel;
    void fodderGraphicSetUp(){
        int available=availableFodder.getAvailable();
        fodderLabel.setText(String.valueOf(available)+"%");
        if (available >= 20 && available <= 50) {
            fodderBarImage.setImage(new Image("file:///C:/Users/Public/Documents/ProjectFarmSuite/src/main/resources/data/bar4.png"));
        } else if (available > 50 && available <= 70) {
            fodderBarImage.setImage(new Image("file:///C:/Users/Public/Documents/ProjectFarmSuite/src/main/resources/data/bar3.png"));
        } else if (available > 70 && available < 100) {
            fodderBarImage.setImage(new Image("file:///C:/Users/Public/Documents/ProjectFarmSuite/src/main/resources/data/bar2.png"));
        }
        else if (available==100){
            fodderBarImage.setImage(new Image("file:///C:/Users/Public/Documents/ProjectFarmSuite/src/main/resources/data/bar1.png"));
        }
    }
    @FXML
    private Button orderFodderButton;
    @FXML
    public void onClickorderFodderButton(){
            if (availableFodder.getAvailable()<50){
                availableFodder.setAvailable(100);
                fodderGraphicSetUp();
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
    private Label cow1Label;

    @FXML
    private Label cow2Label;

    @FXML
    private Label cow3Label;

    @FXML
    private Label cow4Label;

    @FXML
    private Label cow5Label;

    @FXML
    private Label cow6Label;
    @FXML
    private Button buttonCollect0;

    @FXML
    private Button buttonCollect1;

    @FXML
    private Button buttonCollect2;

    @FXML
    private Button buttonCollect3;

    @FXML
    private Button buttonCollect4;

    @FXML
    private Button buttonCollect5;
    @FXML
    void onClickbuttonCollect0(ActionEvent event) {
        if (cow1Label.getText().matches("0.00")){
            //do nothing
        }
        else {
            System.out.println(availableFodder.getAvailable());
            availableFodder.setAvailable(availableFodder.getAvailable()-10);
            System.out.println(availableFodder.getAvailable());
            fodderGraphicSetUp();
            double milkAmount = Double.parseDouble(cow1Label.getText());
            cowGroup.setCow1(milkAmount);
            cowGroup.setCow2((double) 0);
            cowGroup.setCow3((double) 0);
            cowGroup.setCow4((double) 0);
            cowGroup.setCow5((double) 0);
            cowGroup.setCow6((double) 0);
            tempNum = 1;
            updateProductionAndResetMilk();
        }
    }

    @FXML
    void onClickbuttonCollect1(ActionEvent event) {
        if (cow2Label.getText().matches("0.00")){
            //do nothing
        }
        else {
            availableFodder.setAvailable(availableFodder.getAvailable() - 10);
            fodderGraphicSetUp();
            double milkAmount = Double.parseDouble(cow2Label.getText());
            cowGroup.setCow1((double) 0);
            cowGroup.setCow2(milkAmount);
            cowGroup.setCow3((double) 0);
            cowGroup.setCow4((double) 0);
            cowGroup.setCow5((double) 0);
            cowGroup.setCow6((double) 0);
            tempNum = 2;
            updateProductionAndResetMilk();
        }
    }

    @FXML
    void onClickbuttonCollect2(ActionEvent event) {
        if (cow3Label.getText().matches("0.00")){
            //do nothing
        }
        else {
            availableFodder.setAvailable(availableFodder.getAvailable() - 10);
            fodderGraphicSetUp();
            double milkAmount = Double.parseDouble(cow3Label.getText());
            cowGroup.setCow1((double) 0);
            cowGroup.setCow2((double) 0);
            cowGroup.setCow3(milkAmount);
            cowGroup.setCow4((double) 0);
            cowGroup.setCow5((double) 0);
            cowGroup.setCow6((double) 0);
            tempNum = 3;
            updateProductionAndResetMilk();
        }
    }

    @FXML
    void onClickbuttonCollect3(ActionEvent event) {
        if (cow4Label.getText().matches("0.00")){
            //do nothing
        }
        else {
            availableFodder.setAvailable(availableFodder.getAvailable() - 10);
            fodderGraphicSetUp();
            double milkAmount = Double.parseDouble(cow4Label.getText());
            cowGroup.setCow1((double) 0);
            cowGroup.setCow2((double) 0);
            cowGroup.setCow3((double) 0);
            cowGroup.setCow4(milkAmount);
            cowGroup.setCow5((double) 0);
            cowGroup.setCow6((double) 0);
            tempNum = 4;
            updateProductionAndResetMilk();
        }
    }

    @FXML
    void onClickbuttonCollect4(ActionEvent event) {
        if (cow5Label.getText().matches("0.00")){
            //do nothing
        }
        else {
            availableFodder.setAvailable(availableFodder.getAvailable() - 10);
            fodderGraphicSetUp();
            double milkAmount = Double.parseDouble(cow5Label.getText());
            cowGroup.setCow1((double) 0);
            cowGroup.setCow2((double) 0);
            cowGroup.setCow3((double) 0);
            cowGroup.setCow4((double) 0);
            cowGroup.setCow5(milkAmount);
            cowGroup.setCow6((double) 0);
            tempNum = 5;
            updateProductionAndResetMilk();
        }
    }

    @FXML
    void onClickbuttonCollect5(ActionEvent event) {
        if (cow6Label.getText().matches("0.00")){
            //do nothing
        }
        else {
            availableFodder.setAvailable(availableFodder.getAvailable() - 10);
            fodderGraphicSetUp();
            double milkAmount = Double.parseDouble(cow6Label.getText());
            cowGroup.setCow1((double) 0);
            cowGroup.setCow2((double) 0);
            cowGroup.setCow3((double) 0);
            cowGroup.setCow4((double) 0);
            cowGroup.setCow5((double) 0);
            cowGroup.setCow6(milkAmount);
            tempNum = 6;
            updateProductionAndResetMilk();
        }
    }

    private void checkAndUpdateMilkProduction() {
        Connection connectDB = null;
        Statement st = null;
        ResultSet rs = null;
        String query;
        try {
            FSDatabase connect = new FSDatabase();
            connectDB = connect.getConnection();
            st = connectDB.createStatement();

            // Get current date and month
            java.sql.Date currentSqlDate = new java.sql.Date(System.currentTimeMillis());
            Calendar calendar = Calendar.getInstance();
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.ENGLISH);

            query = "SELECT * FROM Cows WHERE date = '" + currentSqlDate + "'";
            rs = st.executeQuery(query);

            if (rs.next()) {
                // Entry for today's date exists, fetch values
                cow1Label.setText(rs.getString("cow1"));
                cow2Label.setText(rs.getString("cow2"));
                cow3Label.setText(rs.getString("cow3"));
                cow4Label.setText(rs.getString("cow4"));
                cow5Label.setText(rs.getString("cow5"));
                cow6Label.setText(rs.getString("cow6"));
            } else {
                // No entry for today's date, generate new random values
                double cow1 = rand.nextInt(4) + 1;
                double cow2 = rand.nextInt(4) + 1;
                double cow3 = rand.nextInt(4) + 1;
                double cow4 = rand.nextInt(4) + 1;
                double cow5 = rand.nextInt(4) + 1;
                double cow6 = rand.nextInt(4) + 1;

                double totalProduct = cow1 + cow2 + cow3 + cow4 + cow5 + cow6;

                // Insert new values into the database
                String insertQuery = "INSERT INTO Cows (date, month, cow1, cow2, cow3, cow4, cow5, cow6, totalProduct) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = connectDB.prepareStatement(insertQuery);
                pst.setDate(1, currentSqlDate);
                pst.setString(2, month);
                pst.setDouble(3, cow1);
                pst.setDouble(4, cow2);
                pst.setDouble(5, cow3);
                pst.setDouble(6, cow4);
                pst.setDouble(7, cow5);
                pst.setDouble(8, cow6);
                pst.setDouble(9, totalProduct);
                pst.executeUpdate();

                // Update labels with generated values
                cow1Label.setText(String.valueOf(cow1));
                cow2Label.setText(String.valueOf(cow2));
                cow3Label.setText(String.valueOf(cow3));
                cow4Label.setText(String.valueOf(cow4));
                cow5Label.setText(String.valueOf(cow5));
                cow6Label.setText(String.valueOf(cow6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connectDB != null) connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateProductionAndResetMilk() {
        Connection connectDB = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            FSDatabase connect = new FSDatabase();
            connectDB = connect.getConnection();
            st = connectDB.createStatement();

            // Get current date and month
            java.sql.Date currentSqlDate = new java.sql.Date(System.currentTimeMillis());
            Calendar calendar = Calendar.getInstance();
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.ENGLISH);

            // Calculate total milk from all cows

            double totalMilk = cowGroup.getCow1() + cowGroup.getCow2() + cowGroup.getCow3() + cowGroup.getCow4() + cowGroup.getCow5() + cowGroup.getCow6();

// Calculate milk, yogurt, and cheese values
            double milk = totalMilk * 0.70;
            double yogurt = totalMilk * 0.20;
            double cheese = totalMilk * 0.10;

// Check if the record for the current date and month already exists in the production table
            String query = "SELECT milk, yogurt, cheese FROM production WHERE date = ? AND month = ?";
            pst = connectDB.prepareStatement(query);
            pst.setDate(1, currentSqlDate);
            pst.setString(2, month);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Record exists, fetch existing values and add the new ones
                double existingMilk = rs.getDouble("milk");
                double existingYogurt = rs.getDouble("yogurt");
                double existingCheese = rs.getDouble("cheese");

                // Add the new values to the existing ones
                milk += existingMilk;
                yogurt += existingYogurt;
                cheese += existingCheese;

                // Update the row with the new values
                String updateQuery = "UPDATE production SET milk = ?, yogurt = ?, cheese = ? WHERE date = ? AND month = ?";
                pst = connectDB.prepareStatement(updateQuery);
                pst.setDouble(1, milk);
                pst.setDouble(2, yogurt);
                pst.setDouble(3, cheese);
                pst.setDate(4, currentSqlDate);
                pst.setString(5, month);
                pst.executeUpdate();
            }
            else{
                // No record, insert a new row
                String insertQuery = "INSERT INTO production (date, month, milk, yogurt, cheese) VALUES (?, ?, ?, ?, ?)";
                pst = connectDB.prepareStatement(insertQuery);
                pst.setDate(1, currentSqlDate);
                pst.setString(2, month);
                pst.setDouble(3, milk);
                pst.setDouble(4, yogurt);
                pst.setDouble(5, cheese);
                pst.executeUpdate();
            }

            // Now, reset the milk values for the cows that have been milked (set to zero)
            String updateCowsQuery = "UPDATE Cows SET cow1 = ?, cow2 = ?, cow3 = ?, cow4 = ?, cow5 = ?, cow6 = ? WHERE date = ?";

            if (tempNum == 1) {
                cowGroup.setCow1((double) 0);
                cow1Label.setText("0.00");
                cowGroup.setCow2(Double.valueOf(cow2Label.getText()));
                cowGroup.setCow3(Double.valueOf(cow3Label.getText()));
                cowGroup.setCow4(Double.valueOf(cow4Label.getText()));
                cowGroup.setCow5(Double.valueOf(cow5Label.getText()));
                cowGroup.setCow6(Double.valueOf(cow6Label.getText()));
            } else if (tempNum == 2) {
                cowGroup.setCow1(Double.valueOf(cow1Label.getText()));
                cowGroup.setCow2((double) 0);
                cow2Label.setText("0.00");
                cowGroup.setCow3(Double.valueOf(cow3Label.getText()));
                cowGroup.setCow4(Double.valueOf(cow4Label.getText()));
                cowGroup.setCow5(Double.valueOf(cow5Label.getText()));
                cowGroup.setCow6(Double.valueOf(cow6Label.getText()));
            } else if (tempNum == 3) {
                cowGroup.setCow1(Double.valueOf(cow1Label.getText()));
                cowGroup.setCow2(Double.valueOf(cow2Label.getText()));
                cowGroup.setCow3((double) 0);
                cow3Label.setText("0.00");
                cowGroup.setCow4(Double.valueOf(cow4Label.getText()));
                cowGroup.setCow5(Double.valueOf(cow5Label.getText()));
                cowGroup.setCow6(Double.valueOf(cow6Label.getText()));
            } else if (tempNum == 4) {
                cowGroup.setCow1(Double.valueOf(cow1Label.getText()));
                cowGroup.setCow2(Double.valueOf(cow2Label.getText()));
                cowGroup.setCow3(Double.valueOf(cow3Label.getText()));
                cowGroup.setCow4((double) 0);
                cow4Label.setText("0.00");
                cowGroup.setCow5(Double.valueOf(cow5Label.getText()));
                cowGroup.setCow6(Double.valueOf(cow6Label.getText()));
            } else if (tempNum == 5) {
                cowGroup.setCow1(Double.valueOf(cow1Label.getText()));
                cowGroup.setCow2(Double.valueOf(cow2Label.getText()));
                cowGroup.setCow3(Double.valueOf(cow3Label.getText()));
                cowGroup.setCow4(Double.valueOf(cow4Label.getText()));
                cowGroup.setCow5((double) 0);
                cow5Label.setText("0.00");
                cowGroup.setCow6(Double.valueOf(cow6Label.getText()));
            } else if (tempNum == 6) {
                cowGroup.setCow1(Double.valueOf(cow1Label.getText()));
                cowGroup.setCow2(Double.valueOf(cow2Label.getText()));
                cowGroup.setCow3(Double.valueOf(cow3Label.getText()));
                cowGroup.setCow4(Double.valueOf(cow4Label.getText()));
                cowGroup.setCow5(Double.valueOf(cow5Label.getText()));
                cowGroup.setCow6((double) 0);
                cow6Label.setText("0.00");
            }
            pst = connectDB.prepareStatement(updateCowsQuery);
            pst.setDouble(1, cowGroup.getCow1() == 0 ? 0 : cowGroup.getCow1());
            pst.setDouble(2, cowGroup.getCow2() == 0 ? 0 : cowGroup.getCow2());
            pst.setDouble(3, cowGroup.getCow3() == 0 ? 0 : cowGroup.getCow3());
            pst.setDouble(4, cowGroup.getCow4() == 0 ? 0 : cowGroup.getCow4());
            pst.setDouble(5, cowGroup.getCow5() == 0 ? 0 : cowGroup.getCow5());
            pst.setDouble(6, cowGroup.getCow6() == 0 ? 0 : cowGroup.getCow6());
            pst.setDate(7, currentSqlDate);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (st != null) st.close();
                if (connectDB != null) connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
