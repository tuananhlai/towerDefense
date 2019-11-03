import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //GameField gameField = new GameField();
        //NormalEnemy normalEnemy = new NormalEnemy();
        //final long[] lastTime = {0};
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                if (l - lastTime[0] >= 1000000 / 60) {
//                    gc.clearRect(0, 0, 1000, 800);
//                    gameField.renderAll(gc);
//                    gameField.runAll();
//                    lastTime[0] = l;
//                }
//            }
//        };
//        timer.start();
        //gameField.renderAll(gc);
        Group group = new Group(canvas);
        Scene scene = new Scene(group, 1000, 800);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
