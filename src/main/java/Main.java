import game.GameStage;
import game.screen.PlayScreen;
import game.screen.WelcomeScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// FIXME: If the application return Invocation... error, please go to Run > Edit Configuration > VM Options. Input --module-path javafx-sdk-13.0.1\lib --add-modules javafx.controls,javafx.fxml,javafx.media
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameStage gameStage = new GameStage(primaryStage);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
