module org.example.r5a12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports org.example.r5a12;
    opens org.example.r5a12 to javafx.fxml;
    exports org.example.r5a12.view;
}