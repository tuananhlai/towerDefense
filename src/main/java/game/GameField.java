package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * Manage all GameEntity objects on play field
 */
public class GameField {
    public static List<AbstractEntity> gameEntities = new ArrayList<>();
    public static Set<Vector2D> unusablePositions = new HashSet<>();

    // TODO: Use algorithm.
    public static Vector2D[] wayPoints = new Vector2D[] {
            new Vector2D(0 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT),
            new Vector2D(4 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT),
            new Vector2D(4 * Settings.TILE_WIDTH, 4 * Settings.TILE_HEIGHT),
            new Vector2D(6 * Settings.TILE_WIDTH, 4 * Settings.TILE_HEIGHT),
            new Vector2D(6 * Settings.TILE_WIDTH, 0 * Settings.TILE_HEIGHT),
            new Vector2D(8 * Settings.TILE_WIDTH, 0 * Settings.TILE_HEIGHT),
            new Vector2D(8 * Settings.TILE_WIDTH, 5 * Settings.TILE_HEIGHT),
            new Vector2D(13 * Settings.TILE_WIDTH, 5 * Settings.TILE_HEIGHT),
            new Vector2D(13 * Settings.TILE_WIDTH, 8 * Settings.TILE_HEIGHT),
            new Vector2D(16 * Settings.TILE_WIDTH, 8 * Settings.TILE_HEIGHT),
            new Vector2D(16 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT),
            new Vector2D(14 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT),
            new Vector2D(14 * Settings.TILE_WIDTH, 0 * Settings.TILE_HEIGHT),
            new Vector2D(20 * Settings.TILE_WIDTH, 0 * Settings.TILE_HEIGHT),
    };

    int[][] map = new int[][] {
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public GameField() {
        readMap("link/to/map");
    }

    /**
     * Read map's information from txt file in mapURL and add tiles to list objects gameEntities.
     * @param mapURL link to map's file.
     */
    public void readMap(String mapURL) {
        for (int i = 0; i < Settings.MAP_HEIGHT_IN_TILES; i++) {
            for (int j = 0; j < Settings.MAP_WIDTH_IN_TILES; j++) {
                if (map[i][j] == Settings.ROAD) {
                    new Road(Settings.TILE_HEIGHT * j, Settings.TILE_WIDTH * i);
                }
                else {
                    new Mountain(Settings.TILE_HEIGHT * j, Settings.TILE_WIDTH * i);
                }
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
        for (int i = gameEntities.size() - 1; i >= 0 ; i--) {
            AbstractEntity entity = gameEntities.get(i);
            if (entity.isActive()) {
                entity.run();
            }
        }
    }

    public static void clear() {
        gameEntities.clear();
        unusablePositions.clear();
    }

    public static void main(String[] args) {

    }
}
