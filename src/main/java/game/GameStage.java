package game;

import game.screen.PlayScreen;
import game.screen.Screen;
import game.screen.WelcomeScreen;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Act as a screen manager to switch between Main Menu, GamePlay, Level, ...
 * Define starting state of play field in a game stage.
 */
public class GameStage {
    public static Stage stage;
    public static Screen currentScreen = null;
    public GameStage(Stage stage) {
        GameStage.stage = stage;
        signNewScreen(new WelcomeScreen());
    }

    /**
     * Change current screen to input screen.
     * @param newScreen input screen
     */
    public static void signNewScreen(Screen newScreen) {
        if (currentScreen != null) {
            currentScreen.clear();
        }
        currentScreen = newScreen;
        stage.setScene(currentScreen.getScene());
    }

}
