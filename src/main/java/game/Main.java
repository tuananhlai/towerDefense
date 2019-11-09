package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(800, 420);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GameField gameField = new GameField();
        Score score = new Score(710, 20);


        gameField.addStoreItems();
        NormalEnemy normalEnemy = new NormalEnemy();
        canvas.setOnMouseMoved(event -> {
            GameField.mouseEvent = event;
            gameField.hoverTowers(event);
            gameField.hoverItems(event);
        });
        canvas.setOnMousePressed(gameField::clickItems);
        canvas.setOnMouseClicked(gameField::clickTowers);
//        new NormalTower();
//        new NormalTower(4 * Settings.TILE_WIDTH, 5 * Settings.TILE_HEIGHT);
//        new NormalTower(7 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT);
        new AnimationTimer() {
            long lastTime = 0;
            @Override
            public void handle(long l) {
                if (((l - lastTime))/2 - 1000000000 > 0) {
                    long time = l;
                    new TankerEnemy();
                    lastTime = l;
                }
            }
        }.start();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) { // FIXME: fix stuttering issue
                gc.clearRect(0, 0, 800, 600);
//                gameField.deleteUnActiveObj();
                score.render(gc);
                gameField.renderAll(gc);
                gameField.runAll();
            }
        };
        timer.start();
        StackPane group = new StackPane(canvas);
        Scene scene = new Scene(group, 800, 420);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
