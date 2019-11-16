package game.screen;
import game.*;
import game.tower.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;

/**
 * Contain UI elements, including Game Field. Responsible for handling user input.
 */
public class PlayScreen extends Screen {
    public static int money = Settings.PLAYER_START_MONEY;
    public static int health = Settings.PLAYER_START_HP;
    public static Group group;
    public static AnimationTimer timer;
    public static boolean isPause = false;
    private static double fps = 0;
    private static Text moneyTxt;
    private static Text healthTxt;

    private final Font brushUp = Font.loadFont(getClass().getResourceAsStream("/iCielBrushUp.otf"), 20);

    public PlayScreen() {
        Canvas canvas = new Canvas(Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);

        group = new Group(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GameField gameField = new GameField();

        new Spawner();

        Text fpsTxt = new Text(Double.toString(fps));
        fpsTxt.setX(10);
        fpsTxt.setY(20);
        fpsTxt.setFill(Color.RED);
        //AI
        StudentRobot.findPositionsAdvantage();
        StudentRobot.readTranningResult("data/trainning_result.txt");
        // Run program
        timer = new AnimationTimer() {
            long lastTime = 0;
            @Override
            public void handle(long l) {
//                long a = System.nanoTime();
                gc.clearRect(0, 0, Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);
                gameField.renderAll(gc);
                gameField.runAll();
//                System.err.println(System.nanoTime() - a);
                fps = 1000000000.0 / (l - lastTime);
                fpsTxt.setText(Integer.toString((int)fps));
                lastTime = l;
                //AI
//                StudentRobot.putTowerGenius(12, money);
            }
        };
        timer.start();
        group.getChildren().add(fpsTxt);
        TowerStore towerStore = new TowerStore();
        towerStore.setBuyEvent(canvas);
        HBox bottomPanel = addBottomPanel(addPlayerInfos(), towerStore.getStore());
        group.getChildren().add(bottomPanel);
        group.getChildren().add(new TowerInfoPanel());
        this.scene = new Scene(group, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }

    @Override
    public void clear() {
        timer.stop();
        GameField.clear();
        group.getChildren().clear();
        PlayScreen.money = Settings.PLAYER_START_MONEY;
        PlayScreen.health = Settings.PLAYER_START_HP;
        scene = null;
        System.gc();
    }

    public static void spendMoney(int price) {
        money -= price;
        if (money < 0) {
            money = 0;
        }
        moneyTxt.setText(Integer.toString(money));
    }

    public static void playerTakeDamage() {
        health--;
        if (health <= 0) {
            GameStage.signNewScreen(new GameOverScreen());
        }
        healthTxt.setText(Integer.toString(health));
    }

    private HBox addBottomPanel(VBox playerInfo, HBox store) {
        HBox bottomPanel = new HBox();
        bottomPanel.setLayoutX(0);
        bottomPanel.setLayoutY(480);
        bottomPanel.setPrefSize(800, 120);
        bottomPanel.getChildren().addAll(playerInfo, store);
        return bottomPanel;
    }

    private VBox addPlayerInfos() {
        VBox playerInfo = new VBox();
        playerInfo.setPrefSize(150, 120);
        playerInfo.setPadding(new Insets(20));
        playerInfo.setStyle("-fx-background-image: url(\"/assets/towers/towerDefense_tile249.png\");");
        // add money display
        HBox moneyDisplay = new HBox();
        moneyTxt = new Text(Integer.toString(PlayScreen.money));
        moneyTxt.setFont(brushUp);
        moneyDisplay.getChildren().add(moneyTxt);
        // add player hp display
        HBox playerHpDisplay = new HBox();
        healthTxt = new Text(Integer.toString(PlayScreen.health));
        healthTxt.setFont(brushUp);
        playerHpDisplay.getChildren().add(healthTxt);

        playerInfo.getChildren().addAll(moneyDisplay, playerHpDisplay);
        return playerInfo;
    }
}
