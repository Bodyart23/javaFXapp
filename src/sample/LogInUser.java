package sample;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LogInUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label userName;

    @FXML
    private Label timeInscription;

    @FXML
    private Label currentTime;

    @FXML
    private Button exit;

    public String username;

    @FXML
    void initialize() {
        time();
    }

    @FXML
    private void exit() {
        openNewScene("/sample/sample.fxml");
    }

    public void setUsername(String username) {
        userName.setText(this.username = username);
    }

    public void method(){
        time();
      openNewScene("/sample/sample.fxml");
    }

    private void time() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Date date = new Date();
                        SimpleDateFormat formatForTimeNow = new SimpleDateFormat("E hh:mm:ss");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                currentTime.setText(formatForTimeNow.format(date));
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void openNewScene(String window) {
        exit.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

