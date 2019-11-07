package game;

import java.util.ArrayList;
import java.util.List;

public class NormalTower extends Tower{
    public NormalTower() {
        this(1 * Settings.TILE_WIDTH, 1 * Settings.TILE_HEIGHT);
    }

    public NormalTower(double x, double y) {
        super(x, y, "assets/towers/towerDefense_tile180.png", "assets/towers/towerDefense_tile250.png");
        this.setDamage(Settings.NORMAL_TOWER_DAMAGE);
        this.setFireRange(200);
    }

}
