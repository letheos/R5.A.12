module org.example.r5a12 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    requires javafx.swing;

    exports org.example.r5a12;
    opens org.example.r5a12 to javafx.fxml;
    exports org.example.r5a12.view;
}