package game.tower;

import game.Bullet;
import game.GameStage;
import game.MediaManager;
import game.Settings;
import game.screen.PlayScreen;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NormalTower extends Tower{
    public NormalTower() {
        this(1 * Settings.TILE_WIDTH, 1 * Settings.TILE_HEIGHT);
    }

    public NormalTower(double x, double y) {
        super(x, y, Settings.NORMAL_BASE, Settings.NORMAL_GUN);
        this.setFireRate(Settings.NORMAL_TOWER_FIRE_RATE);
        this.setFireRange(Settings.NORMAL_TOWER_RANGE);
        this.bullet = new Bullet(Settings.NORMAL_TOWER_DAMAGE, this.fireRange, Settings.NORMAL_BULLET_IMAGE, 10, 0);
        this.setMedia(MediaManager.NORMAL_GUN_SHOT_FX);
    }
}
