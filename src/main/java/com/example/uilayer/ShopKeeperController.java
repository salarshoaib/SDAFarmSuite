package com.example.uilayer;

import com.example.databaselayer.FSDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShopKeeperController {
    double milkLeft, cheeseLeft, yogurtLeft;
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
    private ComboBox<String> productComboBox;

    @FXML
    public void initialize() {
        // Add options to the ComboBox
        productComboBox.getItems().addAll("Milk", "Yogurt", "Cheese");

        // Set a default value (optional)
        productComboBox.setValue("Milk");

        // Handle selection events (optional)
        productComboBox.setOnAction(event -> {
            String selectedProduct = productComboBox.getValue();
            System.out.println("Selected product: " + selectedProduct);
        });
        updateStockFromDatabase();
    }

    private void updateStockFromDatabase() {
        try {
            // Get today's date in the format YYYY-MM-DD
            java.time.LocalDate today = java.time.LocalDate.now();
            String todayDate = today.toString();  // Format: YYYY-MM-DD

            // Establish connection using FSDatabase
            FSDatabase connect = new FSDatabase();
            Connection connectDB = connect.getConnection();
            Statement st = connectDB.createStatement();

            // Query to fetch milk, yogurt, and cheese values for today
            String query = "SELECT milk, yogurt, cheese FROM production WHERE date = '" + todayDate + "'";
            ResultSet rs = st.executeQuery(query);

            // Check if data is available for today
            if (rs.next()) {
                // Get milk, yogurt, and cheese values for today
                double milkAmount = rs.getDouble("milk");
                double yogurtAmount = rs.getDouble("yogurt");
                double cheeseAmount = rs.getDouble("cheese");

                // Update the labels with the fetched values
                availableMilk.setText(String.valueOf(milkAmount));
                availableYogurt.setText(String.valueOf(yogurtAmount));
                availableCheese.setText(String.valueOf(cheeseAmount));

            } else {
                // If no data is available for today, clear the labels or show a default message
                availableMilk.setText("0");
                availableYogurt.setText("0");
                availableCheese.setText("0");
            }
            milkLeft= Double.parseDouble(availableMilk.getText());
            yogurtLeft= Double.parseDouble(availableYogurt.getText());
            cheeseLeft= Double.parseDouble(availableCheese.getText());
            totalLabel.setText("0");
            rs.close();
            st.close();
            connectDB.close();

        } catch (Exception e) {
            e.printStackTrace();  // Print error if database connection or query fails
        }
    }
    @FXML
    private Button addToCartButton;

    @FXML
    private TextArea cart;

    @FXML
    private TextField quantity;
    @FXML
    private Label availableCheese;

    @FXML
    private Label availableMilk;

    @FXML
    private Label availableYogurt;


    @FXML
    private Button checkOutButton;

    @FXML
    private ImageView imageCheese;

    @FXML
    private ImageView imageMilk;

    @FXML
    private ImageView imageYogurt;
    @FXML
    void onClickCheckOutButton(ActionEvent event) throws IOException {

        if (Integer.parseInt(totalLabel.getText())==0) {
            errorMessage.setText("You have no items in Cart!");
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkoutpage.fxml"));
            Parent root = fxmlLoader.load();
            CheckOut checkoutPageController = fxmlLoader.getController();
            checkoutPageController.setTotalBill(totalLabel.getText(), Double.parseDouble(availableMilk.getText())-milkLeft,Double.parseDouble(availableCheese.getText())-cheeseLeft, Double.parseDouble(availableYogurt.getText())-yogurtLeft );
            Stage stage = (Stage) checkOutButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Check-Out");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private Label errorMessage;
    @FXML
    void onClickaddToCartButton(ActionEvent event) {
        errorMessage.setText("");
        String quantityText = quantity.getText();
        String selectedProduct = productComboBox.getValue();
        if (quantityText.equals("")){
            errorMessage.setText("Quantity is empty!");
        }
        else {
            int quantityValue = Integer.parseInt(quantityText);
            if (quantityValue == 0) {
                errorMessage.setText("Quantity cannot be 0!");
            } else {
                int price = 0;
                switch (selectedProduct) {
                    case "Milk":
                        if (quantityValue <= milkLeft) {
                            price = 250;
                            milkLeft = milkLeft - quantityValue;
                        } else {
                            errorMessage.setText("No more left!");
                        }
                        break;
                    case "Yogurt":
                        if (quantityValue <= yogurtLeft) {
                            price = 300;
                            yogurtLeft = yogurtLeft - quantityValue;
                        } else {
                            errorMessage.setText("No more left!");
                        }
                        break;
                    case "Cheese":
                        if (quantityValue <= cheeseLeft) {
                            price = 1000;
                            cheeseLeft = cheeseLeft - quantityValue;
                        } else {
                            errorMessage.setText("No more left!");
                        }
                        break;
                }
                if (price != 0) {
                    int itemTotal = quantityValue * price;
                    String line = quantityText + " x " + selectedProduct + " = " + itemTotal + "\n";
                    cart.appendText(line);

                    int currentTotal = Integer.parseInt(totalLabel.getText());
                    currentTotal += itemTotal;
                    totalLabel.setText(String.valueOf(currentTotal));
                }
            }
        }
    }

    @FXML
    private Button clearButton;
    @FXML
    void onClickClearButton(){
        cart.clear();
        totalLabel.setText("0");
        milkLeft= Double.parseDouble(availableMilk.getText());
        yogurtLeft= Double.parseDouble(availableYogurt.getText());
        cheeseLeft= Double.parseDouble(availableCheese.getText());
    }
    @FXML
    private Label totalLabel;

}
