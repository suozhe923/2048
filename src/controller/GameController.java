package controller;
import Saver.User;
import Saver.UserManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GridNumber;
import view.GamePane;

import java.io.IOException;
import java.util.Objects;

public class GameController {
    private GamePane gamePane;
    private GridNumber model;
    private static User user;
    private final UserManager userManager = new UserManager();
    private static int size;
    private final Stage stage;

    public static void setSize(int s){
        size = s;
    }
    public static int getSize(){
        return size;
    }

    public GameController(GamePane gamePane, GridNumber model,Stage stage) throws IOException {
        this.gamePane = gamePane;
        this.model = model;
        this.stage = stage;
    }
    public static void setUser(User u){
        user = u;
    }
    public static User getUser(){
        return user;
    }
    public void restartGame() {
        model.initialNumbers();
        if(GamePane.getBarrier())
            model.setBarrier();
        gamePane.updateGame();
        gamePane.requestFocus();
        gamePane.reStep();
    }
    public void loadGame() throws IOException {
        model.setNumbers(user.getNumbers());
        gamePane.setPlants(GameController.getUser().getPlants());
        gamePane.updateGame();
        gamePane.requestFocus();
        gamePane.setSteps(user.getStep());
        gamePane.setTimeLimit(user.getTimeLimit());
    }
    public void saveGame(User u){
        user = u;
        user.setNumbers(model.getNumbers());
        user.setStep(gamePane.getSteps());
        user.setTimeLimit(gamePane.getTimeLimit());
        user.setPlants(gamePane.getPlants());
        user.setHasLJ(gamePane.getLJ());
        userManager.getUsersMap().put(user.getUsername(),user);
        userManager.saveUserData();
    }
    public void moveUp(){gamePane.doMoveUp();}
    public void moveDown(){
        gamePane.doMoveDown();
    }
    public void moveRight(){
        gamePane.doMoveRight();
    }
    public void moveLeft(){
        gamePane.doMoveLeft();
    }
    public void toMode2() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Mode2Controller.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toMode() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModeController.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setBarrier(){
        model.setBarrier();
    }
    public void burn(int row){
        model.burn(row);
    }
}