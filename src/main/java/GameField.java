import javafx.scene.canvas.GraphicsContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage all GameEntity objects on play field
 */
public class GameField {
    public static List<AbstractEntity> gameEntities = new ArrayList<>();

    // public static<E> E findIntersects(): check all elements of gameEntities and return the first gameEntity that
    // intersects with this object.
    // TODO: Read map from txt file
    public GameField() {
        for (int i = 0; i < Settings.MAP_HEIGHT_IN_TILES; i++) {
            for (int j = 0; j < Settings.MAP_WIDTH_IN_TILES; j++) {
                gameEntities.add(new Road(-1, Settings.TILE_HEIGHT * j, Settings.TILE_WIDTH * i, "assets/Sprites/grass_tile_1.png"));
            }
        }
    }

    /**
     * Read map's information from txt file in mapURL and add tiles to list objects gameEntities.
     *
     * @param mapURL link to map's file.
     */
    public void readMap(String mapURL) {
        try {
            FileInputStream inputMap = new FileInputStream("assets/map0.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read map");
        }

        for (int i = 0; i < Settings.MAP_HEIGHT_IN_TILES; i++) {
            for (int j = 0; j < Settings.MAP_WIDTH_IN_TILES; j++) {

            }
        }
    }

    /**
     * Render all elements in list objects gameEntities
     * @param gc
     */
    public void renderAll(GraphicsContext gc) {
        for (AbstractEntity entity : gameEntities) {
            if (entity.isActive()) {
                entity.render(gc);
            }
        }
    }

    /**
     * Run all elements in list objects gameEntities
     */
    public void runAll() {
        for (AbstractEntity entity : gameEntities) {
            if (entity.isActive()) {
                entity.run();
            }
        }
    }

    public static void main(String[] args) {

    }
}
