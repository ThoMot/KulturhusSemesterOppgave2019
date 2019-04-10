module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.group38 to javafx.fxml;
    exports org.group38;

    opens org.group38.model.Controllers to javafx.fxml;
    exports org.group38.model.Controllers;
}