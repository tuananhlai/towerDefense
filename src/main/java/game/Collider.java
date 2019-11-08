package game;

import javafx.geometry.Rectangle2D;

public interface Collider {
    public Rectangle2D getBoundary();
    public boolean intersects(Collider other);
}
