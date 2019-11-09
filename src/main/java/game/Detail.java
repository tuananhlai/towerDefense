package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.FileInputStream;

public class Detail {
    private double x;
    private double y;
    private double width;
    private Image damageImg;
    private Image priceImg;
    private double height;
    private int damage;
    private int towerType;
    private int fireRange;
    public Detail(double x, double y, int damage, int type, int fireRange){
        try{
            damageImg = new Image(new FileInputStream("assets/Retina/dollarSign.png"));
            priceImg = new Image(new FileInputStream("assets/Retina/gold.png"));
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
        this.fireRange = fireRange;
        towerType = type;
        this.damage = damage;
        width = 200;
        height = 100;
        this.x = x - width;
        this.y = y - height;
    }
    public void render(GraphicsContext gc){
        gc.setFill(Color.BLUEVIOLET);
        gc.setGlobalAlpha(0.3);
        gc.fillOval((int) ((GameField.mouseEvent.getX()) / Settings.TILE_WIDTH)*Settings.TILE_WIDTH + Settings.TILE_WIDTH/2 - fireRange,
                (int) ((GameField.mouseEvent.getY()) / Settings.TILE_HEIGHT)*Settings.TILE_HEIGHT + Settings.TILE_HEIGHT/2 - fireRange,
                fireRange*2, fireRange*2);
        gc.setGlobalAlpha(1);
        gc.setFill(Color.GRAY);
        gc.fillRect(x, y, width, height);
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(5);
        gc.strokeLine(x, y, x + width, y);
        gc.strokeLine(x, y, x, y + height);
        gc.strokeLine(x + width, y, x + width, y + height);
        gc.strokeLine(x, y + height, x + width, y + height);
        //draw damage:
        gc.drawImage(damageImg ,x + 20, y + 15, 30, 30);
        Settings.drawNumber(gc, damage,  x, y + 15, 30, 30);
        //draw price:
        gc.drawImage(priceImg ,x + 20, y + 60, 30, 30);
        Settings.drawNumber(gc, 15,  x, y + 60, 20, 30);
        //draw Tower Image
        try {
            Image image = null;
            switch (towerType){
                case Settings.NORMAL_TOWER_ITEM:{
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile250.png"));
                    break;
                }
                case Settings.MACHINE_GUN_TOWER_ITEM:{
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile298.png"));
                    break;
                }
                case Settings.SNIPER_TOWER_ITEM:{
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile249.png"));
                    break;
                }
                default: break;
            }
            gc.drawImage(image, x + 100, y + 15, 70, 70);
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
