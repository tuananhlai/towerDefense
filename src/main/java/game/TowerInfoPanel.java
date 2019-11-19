package game;
/**
 * Show information about selecting tower on the right of game's window.
 */

import game.screen.PlayScreen;
import game.screen.WelcomeScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TowerInfoPanel extends VBox{
    private static Text towerPrice;
    private static AttributeBar damageBar, fireRangeBar, fireRateBar;

    private final Font brushUp = Font.loadFont(getClass().getResourceAsStream("/iCielBrushUp.otf"), 20);

    public TowerInfoPanel() {
        setLayoutX(800);
        setLayoutY(0);
        this.setPrefSize(200, 600);
        this.setPadding(new Insets(10, 12, 10,30));
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #545954");
        addAllElements();
    }

    private void addAllElements() {
        addAllTexts();
        addPauseBtn();
        addMenuButton();
        addSaveButton();
    }

    private void addAllTexts() {
        towerPrice = new Text("0");
        towerPrice.setFont(brushUp);
        towerPrice.setFill(Color.WHITE);
        Text damageTxt = new Text("damage");
        damageTxt.setFont(brushUp);
        damageTxt.setFill(Color.WHITE);
        Text fireRangeTxt = new Text("fire range");
        fireRangeTxt.setFont(brushUp);
        fireRangeTxt.setFill(Color.WHITE);
        Text fireRateTxt = new Text("fire rate");
        fireRateTxt.setFont(brushUp);
        fireRateTxt.setFill(Color.WHITE);

        damageBar = new AttributeBar(0, 10);
        fireRangeBar = new AttributeBar(0, 300);
        fireRateBar = new AttributeBar(0, 10);

        getChildren().addAll(towerPrice, damageTxt, damageBar, fireRangeTxt, fireRangeBar, fireRateTxt, fireRateBar);
    }

    private void addPauseBtn() {
        Button pauseBtn = new Button("Pause");
        pauseBtn.setPrefSize(50, 50);
        pauseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (PlayScreen.isPause) {
                    PlayScreen.isPause = false;
                    PlayScreen.timer.start();
                    pauseBtn.setText("Pause");
                }
                else {
                    PlayScreen.isPause = true;
                    PlayScreen.timer.stop();
                    pauseBtn.setText("Resume");
                    new GameManager().saveData();
                    GameField.spawner.saveCurrentWaveTurn();
                }
            }
        });
        this.getChildren().add(pauseBtn);
    }

    private void addMenuButton() {
        Button menuBtn = new Button("Menu");
        menuBtn.setPrefSize(50, 50);
        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameStage.signNewScreen(new WelcomeScreen());
            }
        });
        this.getChildren().add(menuBtn);
    }

    private void addSaveButton(){
        Button saveButton = new Button("Save");
        saveButton.setPrefSize(50, 50);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new GameManager().saveData();
                GameField.spawner.saveCurrentWaveTurn();
            }
        });
        this.getChildren().add(saveButton);
    }

    public static void showTowerInfo(int price, double damage, int range, double fireRate) {
        TowerInfoPanel.towerPrice.setText(Integer.toString(price));
        TowerInfoPanel.damageBar.setCurrent(damage);
        TowerInfoPanel.fireRangeBar.setCurrent(range);
        TowerInfoPanel.fireRateBar.setCurrent(fireRate);
    }

    /**
     * Show info as bar (like health bar you know).
     */
    class AttributeBar extends StackPane {
        private Rectangle currentDisplay;
        private double max;
        public AttributeBar(double current, double max) {
            this.setPrefSize(100, 20);
            this.setAlignment(Pos.CENTER_LEFT);
            this.max = max;
            Rectangle maxDisplay = new Rectangle();
            maxDisplay.setFill(Color.BLACK);
            maxDisplay.setWidth(100);
            maxDisplay.setHeight(10);
            currentDisplay = new Rectangle();
            currentDisplay.setFill(Color.LIMEGREEN);
            double percent = current / this.max;
            currentDisplay.setWidth(100 * percent);
            currentDisplay.setHeight(10);
            this.getChildren().addAll(maxDisplay, currentDisplay);
        }
        public void setCurrent(double current) {
            double percent = current / this.max;
            currentDisplay.setWidth(100 * percent);
        }
    }
}
