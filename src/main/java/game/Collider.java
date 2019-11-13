package game;
/**
 * Implement by colliding objects. Provide ways to get boundary and check intersection more easily.
 */

import javafx.geometry.Rectangle2D;

public interface Collider {

    /**
     * return collider's hitbox.
     * @return Rectangle2D object representing collider's hitbox
     */
    Rectangle2D getBoundary();

    /**
     * Check if this collider's hitbox intersect with any other collider's hitbox.
     * @param other other collider
     * @return true if they intersect, false otherwise.
     */
    boolean intersects(Collider other);
}
