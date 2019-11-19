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

public class MachineGunTower extends Tower{
    public MachineGunTower() {
        this(1 * Settings.TILE_WIDTH, 1 * Settings.TILE_HEIGHT);
    }

    public MachineGunTower(double x, double y) {
        super(x, y, Settings.MACHINE_GUN_BASE, Settings.MACHINE_GUN_GUN);
        this.setFireRate(Settings.MACHINE_GUN_TOWER_FIRE_RATE);
        this.setFireRange(Settings.MACHINE_GUN_TOWER_RANGE);
        this.bullet = new Bullet(Settings.MACHINE_GUN_TOWER_DAMAGE, this.fireRange, Settings.MACHINE_GUN_BULLET_IMAGE, 12, 0);
        this.setMedia(MediaManager.MACHINE_GUN_SHOT_FX);
    }
}
