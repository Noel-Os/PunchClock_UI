module controller.punchclock_ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens controller.punchclock_ui to javafx.fxml;
    exports controller.punchclock_ui;
}