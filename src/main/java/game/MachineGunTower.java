package game;

public class MachineGunTower extends Tower{
    public MachineGunTower() {
        this(1 * Settings.TILE_WIDTH, 1 * Settings.TILE_HEIGHT);
    }

    public MachineGunTower(double x, double y) {
        super(x, y, "assets/towers/towerDefense_tile226.png", "assets/towers/towerDefense_tile298.png");
        this.setDamage(Settings.MACHINE_GUN_TOWER_DAMAGE);
        this.setFireRange(Settings.MACHINE_GUN_TOWER_FIRE_RANGE);
        type = Settings.MACHINE_GUN_TOWER_ITEM;
    }

}
