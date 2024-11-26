module com.example.projectfarmsuite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.businesslayer to javafx.base;
    opens com.example.uilayer to javafx.fxml;
    exports com.example.uilayer;
    exports com.example.databaselayer;
    opens com.example.databaselayer to javafx.fxml;
}