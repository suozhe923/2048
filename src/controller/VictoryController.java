package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VictoryController {
    public void getVictory() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VictoryController.fxml")));
        Scene victoryScene = new Scene(root);
        Stage victory = new Stage();
        victory.setTitle("Win");
        victory.setScene(victoryScene);
        victory.show();
    }
    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
