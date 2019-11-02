import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Manage all GameEntity objects on play field
 */
public class GameField {
    ArrayList<AbstractEntity> gameEntities;
    AbstractTile[][] map;

    // public static<E> E findIntersects(): check all elements of gameEntities and return the first gameEntity that
    // intersects with this object.
    public GameField() {
        map = new AbstractTile[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new Road(-1, Settings.TILE_HEIGHT * i, Settings.TILE_WIDTH * j, "assets/Sprites/grass_tile_1.png");
            }
        }
    }
    public void renderMap(GraphicsContext gc) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j].render(gc);
            }
        }
    }
    public static void main(String[] args) {
        
    }
}
