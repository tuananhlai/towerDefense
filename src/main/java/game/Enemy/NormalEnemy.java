package game.Enemy;

import game.Collider;
import game.Enemy.AbstractEnemy;
import game.Settings;
import javafx.geometry.Rectangle2D;

/**
 * Normal Enemy with basic attributes.
 */
public class NormalEnemy extends AbstractEnemy {
    public NormalEnemy() {
        this(0, 3 * Settings.TILE_HEIGHT);
    }

    public NormalEnemy(double x, double y) {
        super(x, y, Settings.NORMAL_ENEMY_IMAGE);
        this.setHp(Settings.NORMAL_HP);
        this.setMaxHP(Settings.NORMAL_HP);
        this.setVelocity(Settings.NORMAL_VELOCITY, 0);
        this.setDefense(Settings.NORMAL_ENEMY_DEFENSE);
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
