package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;

/**
 * Contain static constants.
 */
public class Settings {
    // Tower Fire Range:
    public static final int NORMAL_TOWER_FIRE_RANGE = 150;
    public static final int SNIPER_TOWER_FIRE_RANGE = 200;
    public static final int MACHINE_GUN_TOWER_FIRE_RANGE = 100;

    //Store items type = price
    public static final int NORMAL_TOWER_ITEM = 20;
    public static final int SNIPER_TOWER_ITEM = 15;
    public static final int MACHINE_GUN_TOWER_ITEM = 30;
    //Tower Price = price
    public static final int NORMAL_TOWER_PRICE = 20;
    public static final int SNIPER_TOWER_PRICE = 15;
    public static final int MACHINE_GUN_TOWER_PRICE = 30;


    // WINDOW CONFIGURATION
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 600;
    // MAP CONFIGURATION
    public static int TILE_WIDTH = 70;
    public static int TILE_HEIGHT = 70;
    public static int MAP_WIDTH = 700;
    public static int MAP_WIDTH_IN_TILES = MAP_WIDTH/TILE_WIDTH;
    public static int MAP_HEIGHT = 420;
    public static int MAP_HEIGHT_IN_TILES = MAP_HEIGHT/TILE_HEIGHT;

    // TILE TYPE ID
    public static final int ROAD = 0;
    public static final int MOUNTAIN = 1;
    public static final int TOWER = 2;

    // ENEMY HP
    public static final int NORMAL_HP = 10;
    public static final int TANKER_HP = 10;
    public static final int SMALLER_HP = 2;
    public static final int BOSS_HP = 13;

    //ENEMY VELOCITY

    // TOWER
    public static final int NORMAL_TOWER_DAMAGE = 2;
    public static final int SNIPER_TOWER_DAMAGE = 4;
    public static final int MACHINE_GUN_TOWER_DAMAGE = 1;


    //draw number:
    public static void drawNumber(GraphicsContext gc, int num, double x_pos, double y_pos, int width_per_oneNum, int height){
        Image[] numbers = new Image[5];
        String scoreStr = Integer.toString(num);
        for(int i = 0; i < scoreStr.length(); i++){
            char number = scoreStr.charAt(i);
            try{
                numbers[i] = new Image(new FileInputStream("assets/Retina/towerDefense_tile" + number + ".png"));
                gc.drawImage(numbers[i], x_pos + 50 + i*width_per_oneNum, y_pos, width_per_oneNum, height);
            }
            catch (Exception e){
                System.err.println(e.toString());
            }
        }
    }

}
