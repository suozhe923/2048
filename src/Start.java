import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.GamePane;
import view.GameScene;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

public class Start extends Application {
    public void start(Stage primaryStage) throws IOException {
        Parent origin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("controller/OriginController.fxml")));
        Scene scene = new Scene(origin);
        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();
//        Play0 play0 = new Play0("music4.mp3");
//        play0.start();
    }
}
