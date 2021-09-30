package controller.punchclock_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CreateUserController {

    @FXML
    TextField username;

    @FXML
    TextField pw;

    @FXML
    ComboBox<String> role;

    @FXML
    public void initialize() throws IOException {

        try {

            URL url = new URL("http://localhost:8080/roles");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();

                JSONParser parse = new JSONParser();
                JSONArray data_obj = (JSONArray) parse.parse(inline);

                for (int i = 0; i < data_obj.size(); i++) {

                    JSONObject new_obj = (JSONObject) data_obj.get(i);

                    role.getItems().add(new_obj.get("roleName").toString());

                    break;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void create(ActionEvent actionEvent) throws IOException {


    }

    public void back(ActionEvent actionEvent) throws IOException {
        Main.getInstance().toAdminHome();
    }
}
