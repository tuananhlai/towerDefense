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
        super(x, y, "assets/enemies/towerDefense_tile269.png"); // TODO: use Image object instead of url
        this.setHp(Settings.TANKER_HP);
        this.setMaxHP(Settings.TANKER_HP);
        this.setVelocity(Settings.TANKER_VELOCITY, 0);
        this.setDefense(Settings.TANKER_ENEMY_DEFENSE);
        this.setDropReward(Settings.TANKER_ENEMY_DROP);
    }

    public TankerEnemy(double x, double y, String url, int hp, double velocityX, double velocityY) {
        super(x, y, url, hp, velocityX, velocityY);
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

