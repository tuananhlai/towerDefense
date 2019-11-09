package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Store extends AbstractEntity {
    @Override
    public void run() {

    }
    private boolean holdItem = false;
//    private List<Rectangle> storeItems = new ArrayList<>();
    private int price;
    private int type;
    private int range;
    private boolean isHover = false;
    private Image prohibited = null;
    public Store(double a, double b, int type){
        this.type = type;
        this.setPosition(new Vector2D(a, b));
        this.rect.setX(a);
        this.rect.setY(b);
        this.rect.setWidth(Settings.TILE_WIDTH);
        this.rect.setHeight(Settings.TILE_HEIGHT);
        //load image fit type
        try{
            //load prohibited image
            prohibited = new Image( new FileInputStream("assets/Retina/prohibited_buying.png"));
            //
            switch (type){
                case Settings.NORMAL_TOWER_ITEM:{
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile206.png"));
                    range = Settings.NORMAL_TOWER_FIRE_RANGE;
                    price = Settings.NORMAL_TOWER_PRICE;
                    break;
                }
                case Settings.SNIPER_TOWER_ITEM:{
                    price = Settings.SNIPER_TOWER_PRICE;
                    range = Settings.SNIPER_TOWER_FIRE_RANGE;
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile205.png"));
                    break;
                }
                case Settings.MACHINE_GUN_TOWER_ITEM:{
                    price = Settings.MACHINE_GUN_TOWER_PRICE;
                    range = Settings.MACHINE_GUN_TOWER_FIRE_RANGE;
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile204.png"));
                    break;
                }
                default:{
                    break;
                }
            }
        }
        catch (Exception e){
            System.err.println(e.toString());
        }

    }
    //if click to store item will call this func
    public void hover(MouseEvent button){
        if(GameField.score < price){
            if (this.rect.contains(button.getX(), button.getY())) {
                isHover = true;
            }
            else{
                isHover = false;
            }
        }
        else isHover = false;
    }
    public void click(MouseEvent button) {
        if(GameField.score > price){
            if (button.getButton() == MouseButton.SECONDARY) {
                holdItem = false;
            }
            if (this.rect.contains(button.getX(), button.getY())) {
                holdItem = true;
            }
            if (button.getX() > Settings.TILE_WIDTH / 2 && button.getX() < 700 - Settings.TILE_WIDTH / 2 &&
                    button.getY() > Settings.TILE_HEIGHT / 2 && button.getY() < 420 - Settings.TILE_HEIGHT / 2) {
                if (holdItem && button.getButton() == MouseButton.PRIMARY) {
                    int x = (int) (GameField.mouseEvent.getX()) / Settings.TILE_WIDTH;
                    x *= Settings.TILE_WIDTH;
                    int y = (int) (GameField.mouseEvent.getY()) / Settings.TILE_HEIGHT;
                    y *= Settings.TILE_HEIGHT;
                    switch (type) {//set active true tai luc dat xuong da click 1 lan^^
                        case Settings.NORMAL_TOWER_ITEM: {
                            new NormalTower(x, y).setClicked(true);
                            break;
                        }
                        case Settings.SNIPER_TOWER_ITEM: {
                            new SniperTower(x, y).setClicked(true);
                            break;
                        }
                        case Settings.MACHINE_GUN_TOWER_ITEM: {
                            new MachineGunTower(x, y).setClicked(true);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    GameField.score -= this.price;
//                    System.err.println(GameField.score);
                    holdItem = false;
                }
            }
        }
    }
    public void render(GraphicsContext gc){
        if(holdItem){
            //draw circle bounds item
            gc.setFill(Color.GRAY);
            gc.setGlobalAlpha(0.3);
            gc.fillOval(GameField.mouseEvent.getX() - range,
                    GameField.mouseEvent.getY() - range,
                    range*2, range*2);
            gc.setGlobalAlpha(1);
            gc.drawImage(image, GameField.mouseEvent.getX() - Settings.TILE_WIDTH/2,
                    GameField.mouseEvent.getY() - Settings.TILE_HEIGHT/2,
                    this.rect.getWidth(), this.rect.getHeight());
        }
        gc.drawImage(image, this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
        if(isHover){ //draw 1 layer lam mo anh, am chi ko the mua
//            gc.setFill(Color.GRAY);
//            gc.setGlobalBlendMode(BlendMode.SCREEN);
            gc.setGlobalAlpha(0.3);
//            gc.fillRect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
            gc.drawImage(prohibited, this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
            gc.setGlobalAlpha(1);
        }
    }

}
