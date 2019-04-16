module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.group38 to javafx.fxml;
    exports org.group38;

    opens org.group38.kulturhus.controllers to javafx.fxml;
    exports org.group38.kulturhus.controllers;
}