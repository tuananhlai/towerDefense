package game.enemy;

import game.Collider;
import game.Settings;
import javafx.geometry.Rectangle2D;

public class BossEnemy extends AbstractEnemy {
    public BossEnemy() {
        this(0, 3 * Settings.TILE_HEIGHT);
    }
    public BossEnemy(double x, double y) {
        super(x, y, Settings.BOSS_ENEMY_IMAGE);
        this.setHp(Settings.BOSS_HP);
        this.setMaxHP(Settings.BOSS_HP);
        this.setVelocity(Settings.BOSS_VELOCITY, 0);
        this.setDefense(Settings.BOSS_ENEMY_DEFENSE);
        this.setDropReward(Settings.BOSS_ENEMY_DROP);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.position.x + Settings.TILE_WIDTH / 4.5,
                this.position.y + Settings.TILE_HEIGHT / 4.5,
                Settings.TILE_WIDTH - 2 * Settings.TILE_WIDTH / 4.5,
                Settings.TILE_HEIGHT - 2 * Settings.TILE_HEIGHT / 4.5);
    }

    @Override
    public boolean intersects(Collider other) {
        return this.getBoundary().intersects(other.getBoundary());
    }
}
