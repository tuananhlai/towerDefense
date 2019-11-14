package game.Enemy;

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
        super(x, y, "assets/enemies/towerDefense_tile269.png");
        this.setHp(Settings.TANKER_HP);
        this.setVelocity(1, 0);
        this.setDefense(3);
    }
    // TODO: Every class use only 1 image, so there is no need to input url every time
    public TankerEnemy(double x, double y, String url, int hp, double velocityX, double velocityY) {
        super(x, y, url, hp, velocityX, velocityY);
    }

    @Override
    public String toString() {
        return super.toString();
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

