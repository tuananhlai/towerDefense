package game.tower;

import game.Bullet;
import game.GameStage;
import game.Settings;
import game.screen.PlayScreen;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MachineGunTower extends Tower{
    public MachineGunTower() {
        this(1 * Settings.TILE_WIDTH, 1 * Settings.TILE_HEIGHT);
    }

    public MachineGunTower(double x, double y) {
        super(x, y, "assets/towers/towerDefense_tile180.png", "assets/towers/towerDefense_tile250.png");
        this.setFireRate(6);
        this.setFireRange(Settings.MACHINE_GUN_TOWER_RANGE);
        this.bullet = new Bullet(Settings.MACHINE_GUN_TOWER_DAMAGE, this.fireRange, Settings.MACHINE_GUN_BULLET_IMAGE, 10, 0);
    }

    public void showFireRange() {
        Circle circle = new Circle();
        circle.setCenterX(this.getCenterX());
        circle.setCenterY(this.getCenterY());
        circle.setStroke(Color.RED);
        circle.setRadius(this.fireRange);
        circle.setFill(Color.TRANSPARENT);
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (circle.isVisible()) {
                    circle.setVisible(false);
                }
                else {
                    circle.setVisible(true);
                }
            }
        });
        PlayScreen.group.getChildren().add(circle);
    }
}
