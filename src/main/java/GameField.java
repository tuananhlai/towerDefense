import com.sun.prism.Graphics;
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

    //create specified list object: tower, road, mountain, normal enemy, small enemy, tanker enemy, ...
    public static List<Tower> towers = new ArrayList<>();
    public static List<Road> roads = new ArrayList<>();
    public static List<Mountain> mountains = new ArrayList<>();
    public static List<NormalEnemy> normalEnemies = new ArrayList<>();
    public static List<TankerEnemy> tankerEnemies = new ArrayList<>();
    public static List<SmallerEnemy> smallerEnemies = new ArrayList<>();
    BossEnemy bossEnemy = new BossEnemy(Settings.startPoint.x, Settings.startPoint.y);

    // public static<E> E findIntersects(): check all elements of gameEntities and return the first gameEntity that
    // intersects with this object.
    // prepare, init object
    // TODO: Read map from txt file
    public GameField() {
        readMap();
//        for (int i = 0; i < Settings.MAP_HEIGHT_IN_TILES; i++) {
//            for (int j = 0; j < Settings.MAP_WIDTH_IN_TILES; j++) {
//                gameEntities.add(new Road(-1, Settings.TILE_HEIGHT * j, Settings.TILE_WIDTH * i, "assets/Sprites/grass_tile_1.png"));
//            }
//        }
    }

    /**
     * Read map's information from txt file in mapURL and add tiles to list objects gameEntities.
     * 0 is road
     * 1 is mountain
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
    public void readMap() {
        int x = 0, y = 0;//toa do de tao map
        for (int i = 0; i < Settings.MAP_HEIGHT_IN_TILES; i++) {
            for (int j = 0; j < Settings.MAP_WIDTH_IN_TILES; j++) {
                if(Settings.map[i][j] == 0){
                    roads.add(new Road(x, y));
                }
                else if(Settings.map[i][j] == 1){
                    mountains.add(new Mountain(x, y));
                }
                x += Settings.TILE_WIDTH;
            }
            y += Settings.TILE_HEIGHT;
        }
    }

    /**
     * Render all elements in list objects gameEntities
     * @param gc
     */
    public void renderMap(GraphicsContext gc){
        for(Road i : roads){
            i.render(gc);
        }
        for(Mountain i : mountains){
            i.render(gc);
        }
    }
    public void renderAll(GraphicsContext gc) { //render all active entity
        //first, render map, store
        for(Road i : roads){
            i.render(gc);
        }
        for(Mountain i : mountains){
            i.render(gc);
        }
        //render store


        //second, render tower
        for(Tower i : towers){
            i.render(gc);
        }
        //third, render enemy
        for(NormalEnemy i : normalEnemies){
            i.render(gc);
        }
        for(TankerEnemy i : tankerEnemies){
            i.render(gc);
        }
        for(SmallerEnemy i : smallerEnemies){
            i.render(gc);
        }
        bossEnemy.render(gc);
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
