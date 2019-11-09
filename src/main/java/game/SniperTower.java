package game;

public class SniperTower extends Tower{
    public SniperTower() {
        this(1 * Settings.TILE_WIDTH, 1 * Settings.TILE_HEIGHT);
    }

    public SniperTower(double x, double y) {
        super(x, y, "assets/towers/towerDefense_tile183.png", "assets/towers/towerDefense_tile249.png");
        this.setDamage(Settings.SNIPER_TOWER_DAMAGE);
        this.setFireRange(Settings.SNIPER_TOWER_FIRE_RANGE);
        type = Settings.SNIPER_TOWER_ITEM;
    }

}
