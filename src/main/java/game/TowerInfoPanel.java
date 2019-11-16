package game;
/**
 * Show information about selecting tower on the right of game's window.
 */

import game.screen.PlayScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TowerInfoPanel extends VBox{
    public static Text towerPrice, damage, fireRange, fireRate;

    private final Font brushUp = Font.loadFont(getClass().getResourceAsStream("/iCielBrushUp.otf"), 20);

    public TowerInfoPanel() {
        setLayoutX(800);
        setLayoutY(0);
        this.setPrefSize(200, 600);
        this.setPadding(new Insets(10, 12, 10,30));
        this.setSpacing(30);
        this.setStyle("-fx-background-color: #99e2fc");
        addAllElements();
    }

    private void addAllElements() {
        addAllTexts();
        addPauseBtn();
    }

    private void addAllTexts() {
        towerPrice = new Text("0");
        towerPrice.setFont(brushUp);
        Text damageTxt = new Text("damage");
        damageTxt.setFont(brushUp);
        Text fireRangeTxt = new Text("fire range");
        fireRangeTxt.setFont(brushUp);
        Text fireRateTxt = new Text("fire rate");
        fireRateTxt.setFont(brushUp);

        damage = new Text("0");
        fireRange = new Text("0");
        fireRate = new Text("0");



        getChildren().addAll(towerPrice, damageTxt, damage, fireRangeTxt, fireRange, fireRateTxt, fireRate);
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
                }
            }
        });
        this.getChildren().add(pauseBtn);
    }
    public static void showTowerInfo(int price, int damage, int range, double fireRate) {
        TowerInfoPanel.towerPrice.setText(Integer.toString(price));
        TowerInfoPanel.damage.setText(Integer.toString(damage));
        TowerInfoPanel.fireRange.setText(Integer.toString(range));
        TowerInfoPanel.fireRate.setText(Double.toString(fireRate));
    }
}
