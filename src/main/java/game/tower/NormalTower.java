package game.tower;

import game.Bullet;
import game.Settings;

public class NormalTower extends Tower{
    public NormalTower() {
        this(1 * Settings.TILE_WIDTH, 1 * Settings.TILE_HEIGHT);
    }

    public NormalTower(double x, double y) {
        super(x, y, "assets/towers/towerDefense_tile180.png", "assets/towers/towerDefense_tile250.png");
        this.setFireRange(200);
        this.bullet = new Bullet(Settings.NORMAL_TOWER_DAMAGE, this.fireRange, "assets/towers/Asset4.png", 5, 0);
    }
}
