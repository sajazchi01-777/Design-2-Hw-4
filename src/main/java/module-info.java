module com.example.design2hw4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.design2hw4 to javafx.fxml;
    exports com.example.design2hw4;
}