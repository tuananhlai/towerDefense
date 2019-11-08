package game;

import javafx.geometry.Rectangle2D;

/**
 * Normal Enemy with basic attributes.
 */
public class NormalEnemy extends AbstractEnemy {
    public NormalEnemy() {
        this(0, 2 * 70);
    }

    public NormalEnemy(double x, double y) {
        super(x, y, "assets/enemies/towerDefense_tile245.png");
        this.setHp(Settings.NORMAL_HP);
        this.setVelocity(Settings.NORMAL_VELOCITY, 0);
        this.setDefense(Settings.NORMAL_ENEMY_DEFENSE);
    }

    // TODO: Every class use only 1 image, so there is no need to input url every time
    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.position.x + 25, this.position.y + 25, 20, 20);
    }

    @Override
    public boolean intersects(Collider other) {
        if (other instanceof NormalEnemy) {
            return this.getBoundary().intersects(other.getBoundary());
        }
        return false;
    }
}
