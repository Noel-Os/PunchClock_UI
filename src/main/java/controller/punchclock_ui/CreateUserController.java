package controller.punchclock_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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

    public void create(ActionEvent actionEvent) throws IOException, ParseException {

        long id = 0;

        for (int i = 0; i <= role.getItems().size() - 1; i++){
            if (role.getValue() == role.getItems().get(i)){
                id = i;
            }
        }

        JSONObject jObject = new JSONObject();
        jObject.put("password", pw.getText());
        jObject.put("role", getRole(id));
        jObject.put("username", username.getText());

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
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream()));

            in.close();
        } catch (Exception e) {

        }
        System.out.println(jObject.toString());
        System.out.println(con.getResponseCode());
    }

    public JSONObject getRole(long id) throws IOException, ParseException {
        URL url = new URL("http://localhost:8080/roles/" + id);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responsecode = conn.getResponseCode();

        if (responsecode > 204) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            scanner.close();

            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            return data_obj;
        }

    }

    public void back(ActionEvent actionEvent) throws IOException {
        Main.getInstance().toAdminHome();
    }
}
