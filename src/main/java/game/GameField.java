package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage all GameEntity objects on play field
 */
public class GameField {
    public static List<AbstractEntity> gameEntities = new ArrayList<>();
    public static List<Store> storeItems = new ArrayList<>();
    public static MouseEvent mouseEvent;
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
        readMap("link/to/map");
    }

    /**
     * Read map's information from txt file in mapURL and add tiles to list objects gameEntities.
     * @param mapURL link to map's file.
     */
    public void readMap(String mapURL) {     // TODO: Read map from txt file
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
            AbstractEntity entity = gameEntities.get(i);
            if (entity.isActive()) {
                if(entity instanceof CuongBullet){
                    for(int j = 0; j < gameEntities.size() ; j++){
                        AbstractEntity alien = gameEntities.get(j);
                        if(alien instanceof AbstractEnemy){
                            if(((CuongBullet) entity).isCollision(alien)){
                                ((AbstractEnemy) alien).takeDamage(((CuongBullet) entity).getDamage());
                                entity.deactivate();
                                System.out.println("Yeah!! Ban trung roi");
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
        storeItems.add(new Store(730, 30, Settings.NORMAL_TOWER_ITEM));
        storeItems.add(new Store(730, 100, Settings.SNIPER_TOWER_ITEM));
        storeItems.add(new Store(730, 170, Settings.MACHINE_GUN_TOWER_ITEM));
    }
    public void clickItems(MouseEvent mouseEvent){
        for(int i = 0; i < 3; i++){
            storeItems.get(i).click(mouseEvent);
        }
    }
    public static void clear() {
        gameEntities.clear();
    }

    public static void main(String[] args) {

    }
}
