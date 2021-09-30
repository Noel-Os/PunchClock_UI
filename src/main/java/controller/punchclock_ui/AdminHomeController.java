package controller.punchclock_ui;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminHomeController {

    public void toCreateUser(ActionEvent actionEvent) throws IOException {
        Main.getInstance().toCreateUser();
    }
}
