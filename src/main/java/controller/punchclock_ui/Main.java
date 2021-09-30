package controller.punchclock_ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

        @Override
        public void start(Stage stage) throws IOException {

            Main.instance = this;
            this.primaryStage = stage;

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650, 350);
            this.primaryStage.setTitle("Login");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        }

        public void toAdminHome() throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("admin-home-view.fxml"));
            this.primaryStage.setScene(new Scene(root, 650, 350));
            this.primaryStage.setTitle("Home");
            this.primaryStage.show();
        }

    public static void main(String[] args) {
        launch();
    }
}