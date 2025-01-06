module org.example.r5a12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.charts;
    requires java.desktop;

    exports org.example.r5a12;
    opens org.example.r5a12 to javafx.fxml;
    exports org.example.r5a12.view;
}