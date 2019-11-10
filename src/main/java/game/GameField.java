package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Manage all GameEntity objects on play field
 */
public class GameField {
    public static List<AbstractEntity> gameEntities = new ArrayList<>();
    public static List<Store> storeItems = new ArrayList<>();
    public static MouseEvent mouseEvent;
    public static int score = 100;
    public static int HP = 20;
    public static int id;
    public static Map<Map<Integer, Integer>, Boolean> towerPosition = new HashMap<>();
    // TODO: Add way points automatically when read map.
    public static Vector2D[] wayPoints = new Vector2D[] {
            new Vector2D(0 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT),
            new Vector2D(4 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT),
            new Vector2D(4 * Settings.TILE_WIDTH, 4 * Settings.TILE_HEIGHT),
            new Vector2D(6 * Settings.TILE_WIDTH, 4 * Settings.TILE_HEIGHT),
            new Vector2D(6 * Settings.TILE_WIDTH, 0 * Settings.TILE_HEIGHT),
            new Vector2D(8 * Settings.TILE_WIDTH, 0 * Settings.TILE_HEIGHT),
            new Vector2D(8 * Settings.TILE_WIDTH, 5 * Settings.TILE_HEIGHT),
            new Vector2D(10 * Settings.TILE_WIDTH, 5 * Settings.TILE_HEIGHT),
    };

    int[][] map = new int[][] {
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
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
            int rows = 6;
            int columns = 10;
            int [][] myArray = new int[rows][columns];
            while(sc.hasNextLine()) {
                for (int i=0; i<myArray.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j=0; j<line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
            for (int i = 0; i < Settings.MAP_HEIGHT_IN_TILES; i++) {
                for (int j = 0; j < Settings.MAP_WIDTH_IN_TILES; j++) {
                    if (myArray[i][j] == Settings.ROAD) {
                        new Road(Settings.TILE_HEIGHT * j, Settings.TILE_WIDTH * i);
                        HashMap<Integer, Integer> tempHashMap = new HashMap<>();
                        tempHashMap.put(j, i);
                        towerPosition.put(tempHashMap, true);
                        System.err.println("[" + j + ", " + i + "]");
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
        for(Store i : storeItems){
            i.render(gc);
        }
        // render way points
//        gc.setFill(Color.BLUE);
//        for (int i = 0; i < wayPoints.length; i++) {
//            gc.fillOval(wayPoints[i].x, wayPoints[i].y, 7, 7);
//        }
    }

    /**
     * Run all elements in list objects gameEntities
     */
    public void runAll() {
        int size = gameEntities.size();
        for (int i = size - 1; i >= 0 ; i--) {
            AbstractEntity entity = null;
            try{
                entity = gameEntities.get(i);
            }catch (Exception e){
                entity = gameEntities.get(i-1);
            }
            if (entity.isActive()) {
                if(entity instanceof CuongBullet){
                    for(int j = 0; j < gameEntities.size() ; j++){
                        AbstractEntity alien = gameEntities.get(j);
                        if(alien instanceof AbstractEnemy){
                            if(((CuongBullet) entity).isCollision(alien)){
                                ((AbstractEnemy) alien).takeDamage(((CuongBullet) entity).getDamage());
//                                entity.deactivate();
//                                System.out.println("Yeah!! Ban trung roi");
                            }
                        }
                    }
                }
                entity.run();
            }
        }
    }

    /**
     * delete nhung doi tuong unActive
     */
    public void deleteUnActiveObj(){
        int size = gameEntities.size();
        for(int i = 0; i < size; i++){
            if(!gameEntities.get(i).isActive()){
                gameEntities.remove(i);
                i--;
                size--;
            }
        }
    }

    /**
     * check collision enemy vs bullet, then set non-active bullet if is collision
     */
    public void checkCollision(){
        for(AbstractEntity alien : gameEntities){
            if(alien.isActive()){
                if(alien instanceof AbstractEnemy){
                    for(AbstractEntity bullet : gameEntities){
                        if(bullet instanceof CuongBullet){
                            if(bullet.isActive()){
                                if(((CuongBullet) bullet).isCollision(alien)){
                                    bullet.setActive(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Add store items:
    public void addStoreItems() {
        storeItems.add(new Store(710, 100, Settings.NORMAL_TOWER_ITEM));
        storeItems.add(new Store(710, 170, Settings.SNIPER_TOWER_ITEM));
        storeItems.add(new Store(710, 240, Settings.MACHINE_GUN_TOWER_ITEM));
    }
    public void clickItems(MouseEvent mouseEvent){
        for(int i = 0; i < 3; i++){
            storeItems.get(i).click(mouseEvent);
        }
    }
    public void hoverItems(MouseEvent mouseEvent){
        for(int i = 0; i < 3; i++){
            storeItems.get(i).hover(mouseEvent);
        }
    }
    //hover tower
    public void hoverTowers(MouseEvent mouseEvent){
        for(AbstractEntity i : gameEntities){
            if(i instanceof Tower){
               ((Tower) i).hover(mouseEvent);
            }
        }
    }
    public void clickTowers(MouseEvent mouseEvent){
        for(AbstractEntity i : gameEntities){
            if(i instanceof Tower){
                ((Tower) i).click(mouseEvent);
            }
        }
    }
    public static void clear() {
        gameEntities.clear();
    }

    public static void main(String[] args) {

    }
}
