package controller.punchclock_ui;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminHomeController {

    public void toCreateUser(ActionEvent actionEvent) throws IOException {
        Main.getInstance().toCreateUser();
    }

    public void toCreateRole(ActionEvent actionEvent) throws IOException {
        Main.getInstance().toCreateRole();
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Main.getInstance().toLogin();
    }
}
