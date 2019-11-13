package game.screen;

import game.GameField;
import javafx.scene.Scene;

public abstract class Screen {
    protected Scene scene;
    public abstract void clear();
    public Scene getScene() {
        return scene;
    }
}
