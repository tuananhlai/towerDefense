package game;

import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Manage all GameEntity objects on play field
 */
public class GameField {
    public static int numberOfEnemy = 0;
    public static List<AbstractEntity> gameEntities = new ArrayList<>();
    public static Set<Vector2D> unusablePositions = new HashSet<>();
    public static List<Vector2D> listPoint = new ArrayList<>(); //danh cho phan AI
    public static int[][] trainning_result = new int[80][12];
    public static Spawner spawner = null;

    public static int[][] map = new int[Settings.MAP_HEIGHT_IN_TILES][Settings.MAP_WIDTH_IN_TILES];

    public GameField() {
        readMap("assets/tiles/mapdata.txt");
        spawner = new Spawner();
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
                    }
                    else {
                        new Mountain(Settings.TILE_HEIGHT * j, Settings.TILE_WIDTH * i);
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e.toString());
        }
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
    }

    /**
     * Run all elements in list objects gameEntities
     */
    public void runAll() {
        for (int i = gameEntities.size() - 1; i >= 0 ; i--) {
            if (i >= gameEntities.size()) {
                i = gameEntities.size() - 1;
                if (i == -1) break;
            }
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
