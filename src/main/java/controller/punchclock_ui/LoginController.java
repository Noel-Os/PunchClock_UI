package controller.punchclock_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class LoginController {

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    public void Login(ActionEvent actionEvent) {

        JSONObject jObject = new JSONObject();

        jObject.put("id", 0);
        jObject.put("password", password.getText());
        jObject.put("role", null);
        jObject.put("username", username.getText());

    }
}
