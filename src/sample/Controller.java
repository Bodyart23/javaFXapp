package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField password_field;

    @FXML
    private Button clear_button;

    @FXML
    private Button backspace_button;

    @FXML
    private Button button_1;

    @FXML
    private Button button_2;

    @FXML
    private Button button_3;

    @FXML
    private Button button_4;

    @FXML
    private Button button_5;

    @FXML
    private Button button_6;

    @FXML
    private Button button_7;

    @FXML
    private Button button_8;

    @FXML
    private Button button_9;

    @FXML
    private Button button_0;

    @FXML
    private Button login_button;

    @FXML
    private Button clock_button;

    @FXML
    private Label timeInscription;

    @FXML
    private Label currentTime;
    @FXML
    void initialize() throws SQLException {
        time();
        login_button.setOnAction(event -> {
            String passText = password_field.getText().trim();

            if (!passText.equals("")){
                loginUser(passText);

            }
            else {
                System.out.println("Password is empty");
            }
        });


        Button button1 = new Button("1");
        button_1.setOnAction(click -> {
            if(password_field.getText().length()<4){
                String textFieldNewString = password_field.getText() + button1.getText();
                password_field.setText(textFieldNewString);
            }
        });
        Button button2 = new Button("2");
        button_2.setOnAction(click -> {
            if(password_field.getText().length()<4){
            String textFieldNewString = password_field.getText() + button2.getText();
            password_field.setText(textFieldNewString);
            }
        });
        Button button3 = new Button("3");
        button_3.setOnAction(click -> {
            if(password_field.getText().length()<4) {
                String textFieldNewString = password_field.getText() + button3.getText();
                password_field.setText(textFieldNewString);
            }
        });
        Button button4 = new Button("4");
        button_4.setOnAction(click -> {
            if(password_field.getText().length()<4) {
                String textFieldNewString = password_field.getText() + button4.getText();
                password_field.setText(textFieldNewString);
            }
        });
        Button button5 = new Button("5");
        button_5.setOnAction(click -> {
            if(password_field.getText().length()<4) {
                String textFieldNewString = password_field.getText() + button5.getText();
                password_field.setText(textFieldNewString);
            }
        });
        Button button6 = new Button("6");
        button_6.setOnAction(click -> {
            if(password_field.getText().length()<4) {
                String textFieldNewString = password_field.getText() + button6.getText();
                password_field.setText(textFieldNewString);
            }
        });
        Button button7 = new Button("7");
        button_7.setOnAction(click -> {
            if(password_field.getText().length()<4) {
                String textFieldNewString = password_field.getText() + button7.getText();
                password_field.setText(textFieldNewString);
            }
        });
        Button button8 = new Button("8");
        button_8.setOnAction(click -> {
            if(password_field.getText().length()<4) {
                String textFieldNewString = password_field.getText() + button8.getText();
                password_field.setText(textFieldNewString);
            }
        });
        Button button9 = new Button("9");
        button_9.setOnAction(click -> {
            if(password_field.getText().length()<4){
            String textFieldNewString = password_field.getText() + button9.getText();
            password_field.setText(textFieldNewString);
            }
        });
        Button button0 = new Button("0");
        button_0.setOnAction(click -> {
            if(password_field.getText().length()<4) {
                String textFieldNewString = password_field.getText() + button0.getText();
                password_field.setText(textFieldNewString);
            }
        });
        clear_button.setOnAction(click ->{
            password_field.clear();
        });
        backspace_button.setOnAction(click->{
            String t = password_field.getText();
            password_field.setText(t.substring(0, t.length() - 1));
        });
    }
//    public String showUser(){
//        return userName;
//    }

    public String getUser(String passText){
        DatabaseHandler db = new DatabaseHandler();
        LogInUser logInUser = new LogInUser();
        ObservableList<User> list = null;
        String name = null;
        try {
            list = db.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(User user:list){
            if (user.getPussword().equals(passText)){
                name = user.getUserName();
            }
        }
        return name ;
    }

    private void loginUser(String passText){
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setPussword(passText);
        ResultSet resultSet = dbHandler.getUser(user);
        LogInUser logInUser = new LogInUser();
        int counter = 0;
        try {
            while (resultSet.next()){
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter>=1){
            logInUser.setUserName(getUser(passText));
            openNewScene("/sample/user.fxml");
        }
    }
//    public void setPass(String passText){
//        LogInUser logInUser = new LogInUser();
//        logInUser.getUser(passText);
//    }


    public void openNewScene(String window){
        login_button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
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
}

