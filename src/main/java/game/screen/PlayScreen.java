package game.screen;

import game.GameField;
import game.NormalEnemy;
import game.NormalTower;
import game.Settings;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PlayScreen extends Screen {
    public PlayScreen() {
        Canvas canvas = new Canvas(Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GameField gameField = new GameField();
        NormalEnemy normalEnemy = new NormalEnemy();
        new NormalTower();
        new NormalTower(4 * Settings.TILE_WIDTH, 5 * Settings.TILE_HEIGHT);
        new NormalTower(7 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT);
        new AnimationTimer() {
            long lastTime = 0;
            @Override
            public void handle(long l) {
                if (l - lastTime > 2000000000) {
                    new NormalEnemy();
                    lastTime = l;
                }
            }
        }.start();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, 1000, 800);
                gameField.renderAll(gc);
                gameField.runAll();
            }
        };
        timer.start();
        Group group = new Group(canvas);
        scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }
    @Override
    public void clear() {

    }
}