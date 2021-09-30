package controller.punchclock_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public class LoginController {

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    Label wrong;

    public void Login(ActionEvent actionEvent) throws IOException {

        JSONObject jObject = new JSONObject();
        jObject.put("password", password.getText());
        jObject.put("username", username.getText());

        URL url = new URL("http://localhost:8080/auth/login");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        String tokenJSON = "";
        try {
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(jObject.toString().getBytes());
            os.flush();
            os.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                tokenJSON = inputLine;
            }
            in.close();
        } catch (Exception e) {

        }

        if (con.getResponseCode() == 200) {
            Main.getInstance().toAdminHome();
        } else {
            wrong.setVisible(true);
        }
    }
}
