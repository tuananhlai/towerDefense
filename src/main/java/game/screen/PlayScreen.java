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
    public static int money = 0;
    public static int health = 0;
    public static Group group; // CHÚ Ý: nếu mà tạo Rectangle để click vào tower thì dùng add Rectangle vào đây.
    private Canvas canvas;
    public static AnimationTimer timer;
    public static double fps = 0;
    public PlayScreen() {
        canvas = new Canvas(Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);
        setHandleEventCanvas();

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

    /**
     * Handle event related to canvas, for example:
     * Plant tower event. (mouse drag)
     * ...
     */
    public void setHandleEventCanvas() {
        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != canvas && dragEvent.getDragboard().hasString() && dragEvent.getDragboard().hasImage()) {
                    /* allow for both copying and moving, whatever user chooses */
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                dragEvent.consume();
            }
        });
        canvas.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("Drag dropped at " + dragEvent.getX() + " " + dragEvent.getY());

                Dragboard db = dragEvent.getDragboard();
                boolean isSuccess = false;
                if (db.hasImage() && db.hasString()) {
                    isSuccess = true;
                    plantTower(db.getString(), dragEvent.getX(), dragEvent.getY());
                }
                dragEvent.setDropCompleted(isSuccess);
                dragEvent.consume();
            }
        });
    }

    private void plantTower(String towerCode, double mouseX, double mouseY) {
        int rowIndex = (int) mouseY / Settings.TILE_HEIGHT;
        int colIndex = (int) mouseX / Settings.TILE_WIDTH;
        Vector2D plantPos = new Vector2D(rowIndex, colIndex);
        if (GameField.unusablePositions.contains(plantPos)) {
            return;
        }
        switch (towerCode) {
            case "0": {
                new NormalTower(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
                break;
            }
            case "1": {
                new SniperTower(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
                break;
            }
        }

    }
}
