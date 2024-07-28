package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;

public class LoseController {
    public void getLose() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoseController.fxml")));
        Scene loseScene = new Scene(root);
        Stage victory = new Stage();
        victory.setTitle("Lose");
        victory.setScene(loseScene);
        victory.show();
    }
    @FXML
    void close(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
