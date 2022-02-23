module com.example.swop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.swop to javafx.fxml;
    exports com.example.swop;
}