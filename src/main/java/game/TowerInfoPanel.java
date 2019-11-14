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
    private final Font brushUp = Font.loadFont(getClass().getResourceAsStream("/iCielBrushUp.otf"), 20);
    public TowerInfoPanel() {
        setLayoutX(800);
        setLayoutY(0);
        this.setPrefSize(200, 600);
        this.setPadding(new Insets(10, 12, 10,30));
        this.setSpacing(30);
        this.setStyle("-fx-background-color: #99e2fc");
        addAllTexts();
    }

    public void addAllTexts() {
        Text moneyTxt = new Text(Integer.toString(PlayScreen.money));
        moneyTxt.setFont(brushUp);
        Text damageTxt = new Text("damage");
        damageTxt.setFont(brushUp);
        Text fireRangeTxt = new Text("fire range");
        fireRangeTxt.setFont(brushUp);
        Text fireRateTxt = new Text("fire rate");
        fireRateTxt.setFont(brushUp);

        Button pause = new Button("Pause");
        pause.setPrefSize(50, 50);
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (PlayScreen.isPause) {
                    PlayScreen.isPause = false;
                    PlayScreen.timer.start();
                    pause.setText("Pause");
                }
                else {
                    PlayScreen.isPause = true;
                    PlayScreen.timer.stop();
                    pause.setText("Resume");
                }
            }
        });
        getChildren().addAll(moneyTxt, damageTxt, fireRangeTxt, fireRateTxt, pause);
    }

    public void showTowerInfo() {
        this.getChildren().clear();
    }
}
