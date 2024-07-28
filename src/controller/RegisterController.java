package controller;

import Saver.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    private UserManager userManager;
    @FXML
    private TextField newAccount;

    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirm;
    @FXML
    private Label account;

    @FXML
    private Label password;
    @FXML
    private Button OK;

    @FXML
    void doRegister(ActionEvent event) throws IOException {
        setUserManager();
        if(userManager.registerUser(newAccount.getText(),newPassword.getText(),confirm.getText())){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            password.setOpacity(1);
            account.setOpacity(0);
        }
        else if (newPassword.getText().equals(confirm.getText())){
            password.setOpacity(0);
            account.setOpacity(1);
        }
        else {account.setOpacity(0);
            password.setOpacity(1);}


    }
    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    void setUserManager() throws IOException {
        this.userManager = new UserManager();
    }

}

