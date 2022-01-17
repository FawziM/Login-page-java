package com.example.loginproject;

import com.mysql.cj.protocol.Resultset;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.Connection;

public class LoginController{
    @FXML
    public Button loginButton;
    @FXML
    public ImageView iconImageView;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    public void loginButtonOnAction(ActionEvent event) {

        if(!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void validateLogin() {
        DataBaseConnection connectionNow = new DataBaseConnection();
        Connection connectDB = connectionNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '"+usernameTextField.getText()+"' AND password ='"+enterPasswordField.getText()+"'";

        try{
            Statement statement =connectDB.createStatement();
            ResultSet queryResult =  statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1)==1){
                    loginMessageLabel.setText("congratulations !");
                }else {
                    loginMessageLabel.setText("Invalid Login, please try again");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}