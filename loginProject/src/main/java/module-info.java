module com.example.loginproject {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.loginproject to javafx.fxml;
    exports com.example.loginproject;
}