package controller.punchclock_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateCategoryController {

    @FXML
    TextField catName;

    public void create(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Main.getInstance().toAdminHome();
    }
}
