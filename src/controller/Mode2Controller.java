package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.GamePane;
import view.GameScene;

import java.io.IOException;
import java.util.Objects;

public class Mode2Controller {
    private boolean plants = false;
    private int timeLimit;
    @FXML
    void loadGame(ActionEvent event) throws IOException {
        if (GameController.getUser().getNumbers() != null) {
            setSize(GameController.getUser().getNumbers().length);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            GamePane gamePane = new GamePane();
            gamePane.setPlants(GameController.getUser().getPlants());
            plants = GameController.getUser().getPlants();
            gamePane.initialGame();
            gamePane.updateGame();
            gamePane.setLayoutX(0);
            gamePane.setLayoutY(0);
            GameController controller = new GameController(gamePane, gamePane.getModel(), stage);
            controller.loadGame();
            gamePane.setTimeLimitOrigin(GameController.getUser().getTimeLimit());
            gamePane.startTimeLine();
            gamePane.setTimeLabel();
            gamePane.setStepLabel();
            Group group = new Group();
            Pane root = new Pane(gamePane, group);
            GameScene gameScene;
            if (!plants) {
                gameScene = new GameScene(gamePane, controller, group, root, GameController.getUser());
            } else {
                gameScene = new GameScene(gamePane, controller, group, root, GameController.getUser(), GameController.getUser().getLJ());
                System.out.println(GameController.getUser().getLJ());
            }
            stage.setScene(gameScene);
            stage.show();
        }
    }

    @FXML
    void newGame(ActionEvent event) throws IOException {
        setSize(GameController.getUser().getNumbers().length);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GamePane gamePane = new GamePane();
        gamePane.setLayoutX(0);
        gamePane.setLayoutY(0);
        gamePane.setTimeLimitOrigin(timeLimit);
        gamePane.startTimeLine();
        gamePane.setPlants(plants);
        gamePane.initialGame();
        gamePane.updateGame();
        GameController controller = new GameController(gamePane,gamePane.getModel(),stage);
        Group group = new Group();
        Pane root = new Pane(gamePane,group);
        GameScene gameScene;
        if (!plants){
            gameScene = new GameScene(gamePane,controller,group,root,GameController.getUser());}
        else gameScene = new GameScene(gamePane,controller,group,root,GameController.getUser(),true);
        stage.setScene(gameScene);
        stage.show();
    }
    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void setSize4(ActionEvent event) {
        GameController.setSize(4);
        GamePane.setSize(4);
    }
    @FXML
    void setSize6(ActionEvent event) {
        GameController.setSize(6);
        GamePane.setSize(6);
    }
    @FXML
    void setSize8(ActionEvent event) {
        GameController.setSize(8);
        GamePane.setSize(8);
    }
    @FXML
    void setSize10(ActionEvent event) {
        GameController.setSize(10);
        GamePane.setSize(10);
    }
    private void setSize(int n){
        GameController.setSize(n);
        GamePane.setSize(n);
    }
    @FXML
    void setTimeLimit1(ActionEvent event) {
        timeLimit = 60;
    }
    @FXML
    void setTimeLimit2(ActionEvent event) {
        timeLimit = 120;
    }
    @FXML
    void setTimeLimit3(ActionEvent event) {
        timeLimit = 600;
    }
    @FXML
    void noTimeLimit(ActionEvent event) {
        timeLimit = -1;
    }
    @FXML
    void setGoal1(ActionEvent event) {
        GamePane.setGoal(2048);
    }
    @FXML
    void setGoal2(ActionEvent event) {
        GamePane.setGoal(4096);
    }
    @FXML
    void setGoal3(ActionEvent event) {
        GamePane.setGoal(-1);
    }
    @FXML
    void setBarrier(ActionEvent event) {
        GamePane.setBarrier(true);
    }
    @FXML
    void setPlants(ActionEvent event){
        plants = !plants;
    }
    @FXML
    void setClassic(ActionEvent event) {
        GamePane.setBarrier(false);
        plants = false;
    }
}
