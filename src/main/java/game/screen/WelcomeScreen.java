package game.screen;

import game.GameStage;
import game.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WelcomeScreen extends Screen {
    // TODO: Preload assets when in menu
    private Group group;
    public WelcomeScreen() {
        Button startButton = new Button("Start Game");
        startButton.setMaxSize(100, 40);
        startButton.setPrefSize(400, 400);
        startButton.setLayoutX(300);
        startButton.setLayoutY(300);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameStage.signNewScreen(new PlayScreen());
            }
        });
        Image backgroundImg = null;
        try {
            backgroundImg = new Image(new FileInputStream("assets/welcome_background_1.png"));
        } catch (FileNotFoundException e) {

        }
        ImageView background = new ImageView(backgroundImg);
        group = new Group(background);
        group.getChildren().add(startButton);
        scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }

    @Override
    public void clear() {
        group.getChildren().clear();
        scene = null;
    }
}
