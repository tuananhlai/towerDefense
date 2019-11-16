package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Contain static constants.
 */
public class Settings {
    // WINDOW CONFIGURATION
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 480;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;

    // MAP CONFIGURATION
    public static final int TILE_WIDTH = 40;
    public static final int TILE_HEIGHT = 40;
    public static final int MAP_WIDTH = 800;
    public static final int MAP_WIDTH_IN_TILES = MAP_WIDTH/TILE_WIDTH;
    public static final int MAP_HEIGHT = 480;
    public static final int MAP_HEIGHT_IN_TILES = MAP_HEIGHT/TILE_HEIGHT;

    // TILE TYPE ID
    public static final int ROAD = 0;
    public static final int MOUNTAIN = 1;
    public static final int TOWER = 2;

    // ENEMY RESOLUTION
    public static final int ENEMY_WIDTH = TILE_WIDTH;
    public static final int ENEMY_HEIGHT = TILE_HEIGHT;

    // ENEMY HP
    public static final int NORMAL_HP = 50;
    public static final int TANKER_HP = 50;
    public static final int SMALLER_HP = 50;
    public static final int BOSS_HP = 13;

    //ENEMY VELOCITY
    public static final int NORMAL_VELOCITY = 2;
    public static final int TANKER_VELOCITY = 1;
    public static final int SMALLER_VELOCITY = 3;
    public static final int BOSS_VELOCITY = 1;

    public static final int NORMAL_ENEMY_DEFENSE = 0;
    public static final Image NORMAL_ENEMY_IMAGE = loadImage("assets/enemies/towerDefense_tile245.png");
    public static final Image TANKER_ENEMY_IMAGE = loadImage("assets/enemies/towerDefense_tile268.png");
    public static final Image SMALLER_ENEMY_IMAGE = loadImage("assets/enemies/towerDefense_tile292.png");

    // TOWER
    public static final Image NORMAL_TOWER_IMAGE = loadImage("assets/towers/item_1.png");
    public static final String NORMAL_TOWER_ITEM = "0";
    public static final int NORMAL_TOWER_DAMAGE = 1;
    public static final int NORMAL_TOWER_RANGE = (int) 2.5 * TILE_WIDTH;
    public static final int NORMAL_TOWER_PRICE = 20;
    public static final int NORMAL_TOWER_FIRE_RATE = 6;
    public static final Image NORMAL_BULLET_IMAGE = loadImage("assets/towers/towerDefense_tile295.png", 15, 0, true, false);

    public static final Image SNIPER_TOWER_IMAGE = loadImage("assets/towers/item_2.png");
    public static final String SNIPER_TOWER_ITEM = "1";
    public static final int SNIPER_TOWER_DAMAGE = 5;
    public static final int SNIPER_TOWER_RANGE = 4 * TILE_WIDTH;;
    public static final int SNIPER_TOWER_PRICE = 30;
    public static final int SNIPER_TOWER_FIRE_RATE = 2;
    public static final Image SNIPER_BULLET_IMAGE = loadImage("assets/towers/towerDefense_tile297.png", 0.0D, 20, true, false);

    public static final Image MACHINE_GUN_TOWER_IMAGE = loadImage("assets/towers/item_2.png");
    public static final String MACHINE_GUN_TOWER_ITEM = "1";
    public static final int MACHINE_GUN_TOWER_DAMAGE = 5;
    public static final int MACHINE_GUN_TOWER_RANGE = 4 * TILE_WIDTH;;
    public static final int MACHINE_GUN_TOWER_PRICE = 25;
    public static final int MACHINE_GUN_TOWER_FIRE_RATE = 2;
    public static final Image MACHINE_GUN_BULLET_IMAGE = loadImage("assets/towers/towerDefense_tile297.png", 0.0D, 20, true, false);


    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_HEIGHT = 10;


    public static Image loadImage(String url) {
        return Settings.loadImage(url, 0.0D, 0.0D, false, false);
    }

    public static Image loadImage(String url, double width, double height, boolean preservedRatio, boolean smooth) {
        Image loadedImage = null;
        try {
            FileInputStream inputStream = new FileInputStream(url);
            loadedImage = new Image(inputStream, width, height, preservedRatio, smooth);
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Cannot load image at [" + url + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedImage;
    }
}
