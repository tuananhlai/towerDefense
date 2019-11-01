import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JavaFXTest extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HBox Experiment 1");

        Stage stage = new Stage();
        stage.setScene(new Scene(new Button("hello")));
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}