package game.enemy;

import game.Collider;
import game.Settings;
import javafx.geometry.Rectangle2D;

/**
 * Normal Enemy with basic attributes.
 */
public class TankerEnemy extends AbstractEnemy {
    public TankerEnemy() {
        this(0, 3 * Settings.TILE_HEIGHT);
    }
    public TankerEnemy(double x, double y) {
        super(x, y, Settings.TANKER_ENEMY_IMAGE); // TODO: use Image object instead of url
        this.setHp(Settings.TANKER_HP);
        this.setMaxHP(Settings.TANKER_HP);
        this.setVelocity(Settings.TANKER_VELOCITY, 0);
        this.setDefense(Settings.TANKER_ENEMY_DEFENSE);
        this.setDropReward(Settings.TANKER_ENEMY_DROP);
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

