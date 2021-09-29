module controller.punchclock_ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens controller.punchclock_ui to javafx.fxml;
    exports controller.punchclock_ui;
}