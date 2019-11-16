package game;

import game.Enemy.AbstractEnemy;
import game.tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Manage all GameEntity objects on play field
 */
public class GameField {
    public static List<AbstractEntity> gameEntities = new ArrayList<>();
    public static Set<Vector2D> unusablePositions = new HashSet<>();
    public static List<Vector2D> listPoint = new ArrayList<>(); //danh cho phan AI
    public static int[][] trainning_result = new int[80][12];

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

    public static int[][] map = new int[][] {
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
        readMap("assets/tiles/mapdata.txt");
    }

    /**
     * Read map's information from txt file in mapURL and add tiles to list objects gameEntities.
     * @param mapURL link to map's file.
     */
    public void readMap(String mapURL) {     // TODO: Read map from txt file
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(mapURL)));
            int rows = 12;
            int columns = 20;
            int [][] myArray = new int[rows][columns];
            while(sc.hasNextLine()) {
                for (int i=0; i<myArray.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j=0; j<line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                        map[i][j] = myArray[i][j];
                    }
                }
            }
            for (int i = 0; i < Settings.MAP_HEIGHT_IN_TILES; i++) {
                for (int j = 0; j < Settings.MAP_WIDTH_IN_TILES; j++) {
                    if (myArray[i][j] == Settings.ROAD) {
                        new Road(Settings.TILE_HEIGHT * j, Settings.TILE_WIDTH * i);
//                        GameManagement.towerPosition.add(new Vector2D(j, i));
                    }
                    else {
                        new Mountain(Settings.TILE_HEIGHT * j, Settings.TILE_WIDTH * i);
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e.toString());
        }

        // TODO: Create way points array here
    }

    /**
     * Render all elements in list objects gameEntities
     * @param gc
     */
    public void renderAll(GraphicsContext gc) {
        int size = gameEntities.size();
        for (int i = 0; i < size ; i++) {
            AbstractEntity entity = gameEntities.get(i);
            if (entity.isActive()) {
                entity.render(gc);
            }
        }
//        renderMap(gc);
//        renderEnemy(gc);
//        renderBullet(gc);
//        renderTower(gc);
    }

//    public void renderMap(GraphicsContext gc){
//        int size = gameEntities.size();
//        for (int i = 0; i < size ; i++) {
//            AbstractEntity entity = gameEntities.get(i);
//            if(entity instanceof Road || entity instanceof Mountain){
//                if (entity.isActive()) {
//                    entity.render(gc);
//                }
//            }
//        }
//    }
//    public void renderTower(GraphicsContext gc){
//        int size = gameEntities.size();
//        for (int i = 0; i < size ; i++) {
//            AbstractEntity entity = gameEntities.get(i);
//            if(entity instanceof Tower){
//                if (entity.isActive()) {
//                    entity.render(gc);
//                }
//            }
//        }
//    }
//    public void renderEnemy(GraphicsContext gc){
//        int size = gameEntities.size();
//        for (int i = 0; i < size ; i++) {
//            AbstractEntity entity = gameEntities.get(i);
//            if(entity instanceof AbstractEnemy){
//                if (entity.isActive()) {
//                    entity.render(gc);
//                }
//            }
//        }
//    }
//    public void renderBullet(GraphicsContext gc){
//        int size = gameEntities.size();
//        for (int i = 0; i < size ; i++) {
//            AbstractEntity entity = gameEntities.get(i);
//            if(entity instanceof Bullet){
//                if (entity.isActive()) {
//                    entity.render(gc);
//                }
//            }
//        }
//    }

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
