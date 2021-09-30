package controller.punchclock_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    public void Login(ActionEvent actionEvent) throws IOException {

        JSONObject jObject = new JSONObject();

        jObject.put("id", 0);
        jObject.put("password", password.getText());
        jObject.put("role", new JSONArray());
        jObject.put("username", username.getText());

        URL url = new URL("http://localhost:8080/auth/login/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = jObject.toString();
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        System.out.println(jsonInputString);

        System.out.println(con.getResponseCode());

        Main.getInstance().toAdminHome();

    }
}
