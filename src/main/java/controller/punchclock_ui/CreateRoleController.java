package controller.punchclock_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateRoleController {

    @FXML
    TextField roleName;


    public void create(ActionEvent actionEvent) throws IOException {
        JSONObject jObject = new JSONObject();
        jObject.put("roleName", roleName.getText());

        URL url = new URL("http://localhost:8080/roles");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        try {
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(jObject.toString().getBytes());
            os.flush();
            os.close();

            System.out.println(con.getResponseCode());

        } catch (Exception e) {

        }
    }
}
