package game;

import game.screen.PlayScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contain static constants.
 */
public class Settings {

    public static final int PLAYER_START_MONEY = 50;
    public static final int PLAYER_START_HP = 1;
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
    public static final Image ROAD_IMAGE = loadImage("assets/Sprites/sand_tile.png");
    public static final int MOUNTAIN = 1;
    public static final Image MOUNTAIN_IMAGE = loadImage("assets/Sprites/grass_tile_1.png");
    public static final int TOWER = 2;

    // ENEMY RESOLUTION
    public static final int ENEMY_WIDTH = TILE_WIDTH;
    public static final int ENEMY_HEIGHT = TILE_HEIGHT;

    // ENEMY
    public static final int NORMAL_HP = 10;
    public static final int NORMAL_VELOCITY = 2;
    public static final int NORMAL_ENEMY_DEFENSE = 0;
    public static final int NORMAL_ENEMY_DROP = 3;
    public static final Image NORMAL_ENEMY_IMAGE = loadImage("assets/enemies/towerDefense_tile245.png");

    public static final int TANKER_HP = 12;
    public static final int TANKER_VELOCITY = 1;
    public static final int TANKER_ENEMY_DEFENSE = 1;
    public static final int TANKER_ENEMY_DROP = 5;
    public static final Image TANKER_ENEMY_IMAGE = loadImage("assets/enemies/tanker_enemy.png");


    public static final int SMALLER_HP = 2;
    public static final int SMALLER_VELOCITY = 3;
    public static final Image SMALLER_ENEMY_IMAGE = loadImage("assets/enemies/towerDefense_tile292.png");

    public static final int BOSS_HP = 13;
    public static final int BOSS_VELOCITY = 1;

    // TOWER
    public static final Image NORMAL_TOWER_IMAGE = loadImage("assets/towers/item_1.png");
    public static final String NORMAL_TOWER_ITEM = "0";
    public static final double NORMAL_TOWER_DAMAGE = 2.5;
    public static final int NORMAL_TOWER_RANGE = (int) 2.5 * TILE_WIDTH;
    public static final int NORMAL_TOWER_PRICE = 15;
    public static final double NORMAL_TOWER_FIRE_RATE = 3;
    public static final Image NORMAL_BASE = loadImage("assets/towers/towerDefense_tile180.png");
    public static final Image NORMAL_GUN = loadImage("assets/towers/towerDefense_tile250.png");
    public static final Image NORMAL_BULLET_IMAGE = loadImage("assets/towers/towerDefense_tile295.png", 15, 0.0D, true, false);

    public static final Image SNIPER_TOWER_IMAGE = loadImage("assets/towers/item_2.png");
    public static final String SNIPER_TOWER_ITEM = "1";
    public static final double SNIPER_TOWER_DAMAGE = 4;
    public static final int SNIPER_TOWER_RANGE = 4 * TILE_WIDTH;;
    public static final int SNIPER_TOWER_PRICE = 30;
    public static final double SNIPER_TOWER_FIRE_RATE = 1.5;
    public static final Image SNIPER_BASE = loadImage("assets/towers/towerDefense_tile183.png");
    public static final Image SNIPER_GUN = loadImage("assets/towers/towerDefense_tile249.png");
    public static final Image SNIPER_BULLET_IMAGE = loadImage("assets/towers/towerDefense_tile297.png", 0.0D, 20, true, false);

    public static final Image MACHINE_GUN_TOWER_IMAGE = loadImage("assets/towers/item_3.png");
    public static final String MACHINE_GUN_TOWER_ITEM = "2";
    public static final double MACHINE_GUN_TOWER_DAMAGE = 2;
    public static final int MACHINE_GUN_TOWER_RANGE = (int) (1.5 * TILE_WIDTH);
    public static final int MACHINE_GUN_TOWER_PRICE = 25;
    public static final double MACHINE_GUN_TOWER_FIRE_RATE = 7;
    public static final Image MACHINE_GUN_BASE = loadImage("assets/towers/towerDefense_tile180.png");
    public static final Image MACHINE_GUN_GUN = loadImage("assets/towers/machine_gun_tower.png");
    public static final Image MACHINE_GUN_BULLET_IMAGE = loadImage("assets/towers/towerDefense_tile295.png", 0.0D, 15, true, false);

    public static final Image SPREAD_TOWER_IMAGE = loadImage("assets/towers/item_4.png");
    public static final String SPREAD_TOWER_ITEM = "3";
    public static final double SPREAD_TOWER_DAMAGE = 1.5;
    public static final int SPREAD_TOWER_RANGE = (int) 2.5 * TILE_WIDTH;
    public static final int SPREAD_TOWER_PRICE = 25;
    public static final double SPREAD_TOWER_FIRE_RATE = 2.5;
    public static final Image SPREAD_BASE = loadImage("assets/towers/towerDefense_tile182.png");
    public static final Image SPREAD_GUN = loadImage("assets/towers/spread_gun.png");
    public static final Image SPREAD_BULLET_IMAGE = loadImage("assets/towers/towerDefense_tile295.png", 15, 0.0D, true, false);

    public static final Image COIN_STACK_IMG = Settings.loadImage("assets/Retina/coin_stack.png", 25, 0.0D, true, false);
    public static final Image HEART_IMG = Settings.loadImage("assets/Retina/heart.png", 25, 0.0D, true, false);

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

    public static ArrayList<Image> loadImages(String url) {
        return loadImages(url, 0.0D, 0.0D, false, false);
    }

    public static ArrayList<Image> loadImages(String url, double width, double height, boolean preservedRatio, boolean smooth) {
        ArrayList<Image> listImages = new ArrayList<>();
        File assetFolder = new File(url);
        if (assetFolder.exists() && assetFolder.isDirectory()) {
            String[] fileNames = assetFolder.list();
            Arrays.sort(fileNames);
            for (int i = 0; i < fileNames.length; i++) {
                String fileName = fileNames[i];
                if(fileName.toLowerCase().endsWith(".png")) {
                    Image newImage = loadImage(url + "/" + fileName, width, height, preservedRatio, smooth);
                    listImages.add(newImage);
                }
            }
            return listImages;
        }
        return null;
    }

    public static void drawRange(GraphicsContext gc, double range, Color color){
        gc.setFill(color);
        gc.setGlobalAlpha(0.3);
        gc.fillOval(PlayScreen.mouse.getX() - range,
                PlayScreen.mouse.getY() - range,
                range*2, range*2);
        gc.setGlobalAlpha(1);
    }
}
