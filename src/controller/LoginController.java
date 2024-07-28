package controller;

import Saver.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.GamePane;
import view.GameScene;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    private UserManager userManager;
    public TextField getAccount(){return account;}
    @FXML
    private TextField account;

    @FXML
    private PasswordField password;

    @FXML
    private Button ok;
    //    private UserManager userManager;
    @FXML
    private Button cancel;
    @FXML
    private Button register;
    @FXML
    private Label wrongAccount;
    @FXML
    private Label wrongPassword;
    @FXML
    void Back(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OriginController.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Register(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        setWrongAccount0();
        setWrongPassword0();
        setUserManager();
        if(userManager.authenticateUser(account.getText(),password.getText())){
            GameController.setUser(userManager.getUsersMap().get(account.getText()));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Mode2Controller.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if (userManager.ifAccount(account.getText()))
            setWrongPassword1();
        else setWrongAccount1();
    }
    void setUserManager() throws IOException {
        this.userManager = new UserManager();
    }
    public void setWrongAccount1(){
        wrongAccount.setOpacity(1);
    }
    public void setWrongPassword1(){
        wrongPassword.setOpacity(1);
    }
    public void setWrongAccount0(){
        wrongAccount.setOpacity(0);
    }
    public void setWrongPassword0(){
        wrongPassword.setOpacity(0);
    }
}

