package game.tower;

import game.Bullet;
import game.Settings;
import game.screen.PlayScreen;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SniperTower extends Tower{
    public SniperTower() {
        this(1 * Settings.TILE_WIDTH, 1 * Settings.TILE_HEIGHT);
    }

    public SniperTower(double x, double y) {
        super(x, y, "assets/towers/towerDefense_tile183.png", "assets/towers/towerDefense_tile249.png");
        this.setFireRate(Settings.SNIPER_TOWER_FIRE_RATE);
        this.setFireRange(Settings.SNIPER_TOWER_RANGE);
        this.bullet = new Bullet(Settings.SNIPER_TOWER_DAMAGE, this.fireRange, Settings.SNIPER_BULLET_IMAGE, 25, 0);
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