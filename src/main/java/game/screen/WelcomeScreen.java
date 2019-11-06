package game.screen;

import game.GameStage;
import game.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class WelcomeScreen extends Screen {
    Button startButton;
    // TODO: Preload assets when in menu
    public WelcomeScreen() {
        startButton = new Button("Start Game");
        startButton.setMaxSize(100, 40);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameStage.signNewScreen(new PlayScreen());
            }
        });
        Group group = new Group(startButton);
        group.setLayoutX(300);
        group.setLayoutY(300);
        scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        scene.setFill(Color.RED);
    }

    @Override
    public void clear() {

    }
}
