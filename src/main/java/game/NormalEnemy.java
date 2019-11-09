package game;

import javafx.geometry.Rectangle2D;

/**
 * Normal Enemy with basic attributes.
 */
public class NormalEnemy extends AbstractEnemy {
    public NormalEnemy() {
        this(0, 2 * Settings.TILE_HEIGHT);
    }

    public NormalEnemy(double x, double y) {
        super(x, y, "assets/enemies/towerDefense_tile245.png");
        this.setHp(Settings.NORMAL_HP);
        this.setVelocity(Settings.NORMAL_VELOCITY, 0);
        this.setDefense(Settings.NORMAL_ENEMY_DEFENSE);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.position.x + 25, this.position.y + 25, 20, 20);
    }

    @Override
    public boolean intersects(Collider other) {
        return this.getBoundary().intersects(other.getBoundary());
    }
}
