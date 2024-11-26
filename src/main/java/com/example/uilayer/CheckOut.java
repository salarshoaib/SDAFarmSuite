package com.example.uilayer;

import com.example.databaselayer.FSDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckOut {
    double totalBill;
    double milkSold;
    double cheeseSold;
    double yogurtSold;
    boolean checker;
    @FXML
    private Button backButtonShop;
    @FXML
    void onClickBackButtonShop(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButtonShop.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shopkeeperhomepage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Welcome to Shop!");
        stage.setScene(scene);
        stage.show();
    }

    public void setTotalBill(String text, double m, double c, double y) {
        System.out.println(text);
        totalBill= Double.parseDouble(text);
        milkSold=m;
        cheeseSold=c;
        yogurtSold=y;
        setBills();
    }
    @FXML
    private Label milkLabel;
    @FXML
    private Label yogurtLabel;
    @FXML
    private Label cheeseLabel;
    @FXML
    public void setBills(){

        total.setText("BILL: PKR " + String.valueOf(totalBill));
        tax.setText("16% GST: PKR " + String.valueOf(totalBill*0.16));
        grandTotal.setText("PKR "+ String.valueOf(totalBill*0.16+totalBill));
        yogurtLabel.setText("YOGURT: " + yogurtSold + " X 300 = " + yogurtSold*300);
        milkLabel.setText("MILK: " + milkSold + " X 250 = " + milkSold*250);
        cheeseLabel.setText("CHEESE: " + cheeseSold + " X 1000 = " + cheeseSold*1000);
        checker=false;
    }

    @FXML
    private TextField CVC;

    @FXML
    private TextField cardNumber;
    @FXML
    private TextField name;

    @FXML
    private TextField date;

    @FXML
    private Label grandTotal;

    @FXML
    private Button submitButton;

    @FXML
    private Label tax;

    @FXML
    private Label total;

    @FXML
    private Label errorMessage;
    @FXML
    void onClickSubmitButton(ActionEvent event) throws SQLException {
        if (checker==false) {
            String name1 = name.getText();
            String cardNum1 = cardNumber.getText();
            String date1 = date.getText();
            String cvc1 = CVC.getText();
            String datePattern = "\\d{4}-\\d{2}-\\d{2}";
            if (name1.isEmpty() || cardNum1.isEmpty() || date1.isEmpty() || cvc1.isEmpty() || !date1.matches(datePattern)) {
                errorMessage.setText("Please fill in all fields correctly.");
            }
            else {
                FSDatabase connect = new FSDatabase();
                Connection connectDB = connect.getConnection();
                Statement st = connectDB.createStatement();
                String query = "SELECT * FROM farmsuitedatabase.card WHERE name = '" + name1 + "' AND cardNum = '" + cardNum1 + "' AND date = '" + date1 + "' AND cvc = '" + cvc1 + "'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    String todayDate = java.time.LocalDate.now().toString();
                    String productionQuery = "SELECT milk, yogurt, cheese, sold_milk, sold_cheese, sold_yogurt FROM production WHERE date = '" + todayDate + "'";
                    ResultSet rsProduction = st.executeQuery(productionQuery);

                    if (rsProduction.next()) {
                        double milkAmount = rsProduction.getDouble("milk");
                        double yogurtAmount = rsProduction.getDouble("yogurt");
                        double cheeseAmount = rsProduction.getDouble("cheese");
                        double milkAmountSold = rsProduction.getDouble("sold_milk");
                        double yogurtAmountSold = rsProduction.getDouble("sold_yogurt");
                        double cheeseAmountSold = rsProduction.getDouble("sold_cheese");

                        if (milkSold <= milkAmount && yogurtSold <= yogurtAmount && cheeseSold <= cheeseAmount) {
                            double updatedMilk = milkAmount - milkSold;
                            double updatedYogurt = yogurtAmount - yogurtSold;
                            double updatedCheese = cheeseAmount - cheeseSold;
                            double updatedMilkSold = milkAmountSold + milkSold;
                            double updatedYogurtSold = yogurtAmountSold + yogurtSold;
                            double updatedCheeseSold = cheeseAmountSold + cheeseSold;

                            String updateQuery = "UPDATE production SET milk = '" + updatedMilk + "', yogurt = '" + updatedYogurt + "', cheese = '" + updatedCheese + "', sold_milk = " + updatedMilkSold + ", sold_yogurt = " + updatedYogurtSold + ", sold_cheese = " + updatedCheeseSold + " WHERE date = '" + todayDate + "'";
                            st.executeUpdate(updateQuery);
                            double result = totalBill * 0.16 + totalBill;
                            String formattedResult = String.format("%.2f", result);
                            String saleQuery = "INSERT INTO sales (date, month, milk, yogurt, cheese, totalbill) VALUES ('" + todayDate + "', '" + java.time.LocalDate.now().getMonth().toString() + "', '" + milkSold + "', '" + yogurtSold + "', '" + cheeseSold + "', '" + Double.parseDouble(formattedResult) + "')";
                            st.executeUpdate(saleQuery);
                            errorMessage.setText("Card verified successfully! Transaction Successful!");
                            checker = true;
                        }
                    }
                    rsProduction.close();
                } else {
                    errorMessage.setText("Card Data is not correct!");
                }
                rs.close();
                st.close();
                connectDB.close();
            }
        }
        else{
            errorMessage.setText("Your transaction has already been completed!");
            total.setText("");
            tax.setText("");
            grandTotal.setText("");
            CVC.setText("");
            name.setText("");
            cardNumber.setText("");
            date.setText("");
            milkLabel.setText("");
            yogurtLabel.setText("");
            cheeseLabel.setText("");
        }
    }
}
