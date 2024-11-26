package com.example.uilayer;

import com.example.databaselayer.FSDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Reports {
    @FXML
    private Button backButtonManagerHomepage;
    @FXML
    private BarChart<String, Number> productionBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

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

    @FXML
    public void initialize() {
        xAxis.setLabel("Product Type");
        yAxis.setLabel("Quantity Produced");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Production Data");
        try {
            FSDatabase connect = new FSDatabase();
            Connection connectDB = connect.getConnection();
            Statement statement = connectDB.createStatement();
            String query = "SELECT SUM(milk) AS total_milk, SUM(yogurt) AS total_yogurt, SUM(cheese) AS total_cheese FROM farmsuitedatabase.production";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                double totalMilk = resultSet.getDouble("total_milk");
                double totalYogurt = resultSet.getDouble("total_yogurt");
                double totalCheese = resultSet.getDouble("total_cheese");
                series.getData().add(new XYChart.Data<>("Milk", totalMilk));
                series.getData().add(new XYChart.Data<>("Yogurt", totalYogurt));
                series.getData().add(new XYChart.Data<>("Cheese", totalCheese));
            }
            resultSet.close();
            statement.close();
            connectDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        productionBarChart.getData().add(series);
    }
}