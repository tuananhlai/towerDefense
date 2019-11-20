package game.enemy;

import game.Collider;
import game.Settings;
import javafx.geometry.Rectangle2D;

public class SmallEnemy extends AbstractEnemy {
    public SmallEnemy() {
        this(0, 3 * Settings.TILE_HEIGHT);
    }

    public SmallEnemy(double x, double y) {
        super(x, y, Settings.SMALL_ENEMY_IMAGE);
        this.setHp(Settings.SMALL_HP);
        this.setMaxHP(Settings.SMALL_HP);
        this.setVelocity(Settings.SMALL_VELOCITY, 0);
        this.setDefense(Settings.SMALL_ENEMY_DEFENSE);
        this.setDropReward(Settings.SMALL_ENEMY_DROP);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.position.x + Settings.TILE_WIDTH / 3.5,
                this.position.y + Settings.TILE_HEIGHT / 3.5,
                Settings.TILE_WIDTH - 2 * Settings.TILE_WIDTH / 3.5,
                Settings.TILE_HEIGHT - 2 * Settings.TILE_HEIGHT / 3.5);
    }

    @Override
    public boolean intersects(Collider other) {
        return this.getBoundary().intersects(other.getBoundary());
    }
}
