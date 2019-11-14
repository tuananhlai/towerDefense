package game.screen;
import game.*;
import game.tower.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Contain UI elements, including Game Field. Responsible for handling user input.
 */
public class PlayScreen extends Screen {
    public static int money = 100;
    public static int health = 0;
    public static Group group; // CHÚ Ý: nếu mà tạo Rectangle để click vào tower thì dùng add Rectangle vào đây.
    private Canvas canvas;
    public static AnimationTimer timer;
    public static boolean isPause = false;
    public static double fps = 0;
    public PlayScreen() {
        canvas = new Canvas(Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);

        group = new Group(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GameField gameField = new GameField();

        new Spawner();

        Text fpsTxt = new Text(Double.toString(fps));
        fpsTxt.setX(10);
        fpsTxt.setY(20);
        fpsTxt.setFill(Color.RED);
        // Run program
        timer = new AnimationTimer() {
            long lastTime = 0;
            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);
                gameField.renderAll(gc);
                gameField.runAll();
                fps = 1000000000.0 / (l - lastTime);
                fpsTxt.setText(Integer.toString((int)fps));
                lastTime = l;
            }
        };
        timer.start();
        group.getChildren().add(fpsTxt);
//        Button quit = new Button("quit");
//        quit.setLayoutX(0);
//        quit.setLayoutY(480);
//        quit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                GameStage.signNewScreen(new WelcomeScreen());
//            }
//        });
//        group.getChildren().add(quit);
        TowerStore towerStore = new TowerStore();
        towerStore.setBuyEvent(canvas);
        group.getChildren().add(towerStore.getStore());
        group.getChildren().add(new TowerInfoPanel());
        this.scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }

    @Override
    public void clear() {
        GameField.clear();
        group.getChildren().clear();
        timer.stop();
    }

    public static void spendMoney(int price) {
        money -= price;
        if (money < 0) {
            money = 0;
        }
    }

    public static void playerTakeDamage() {
        health--;
        if (health <= 0) {
            GameStage.signNewScreen(new WelcomeScreen());
        }
    }
}
