package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Store extends AbstractEntity {
    @Override
    public void run() {

    }
    private boolean holdItem = false;
    private List<Rectangle> storeItems = new ArrayList<>();
    public Store(double a, double b, int type){
        this.setPosition(new Vector2D(a, b));
        this.rect.setX(a);
        this.rect.setY(b);
        this.rect.setWidth(Settings.TILE_WIDTH);
        this.rect.setHeight(Settings.TILE_HEIGHT);
        //load image fit type
        try{
            switch (type){
                case Settings.NORMAL_TOWER_ITEM:{
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile296.png"));
                    break;
                }
                case Settings.SNIPER_TOWER_ITEM:{
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile180.png"));
                    break;
                }
                case Settings.MACHINE_GUN_TOWER_ITEM:{
                    image = new Image(new FileInputStream("assets/towers/towerDefense_tile203.png"));
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
    public void click(MouseEvent button){
        if(button.getButton() == MouseButton.SECONDARY){
            holdItem = false;
        }
        if(this.rect.contains(button.getX(), button.getY())){
            holdItem = true;
        }
        if(button.getX() > Settings.TILE_WIDTH/2 && button.getX() < 700 - Settings.TILE_WIDTH/2 &&
                button.getY() > Settings.TILE_HEIGHT/2 && button.getY() < 420 - Settings.TILE_HEIGHT/2) {
            if(holdItem && button.getButton() == MouseButton.PRIMARY){
                int x = (int)(GameField.mouseEvent.getX())/Settings.TILE_WIDTH;
                x*=Settings.TILE_WIDTH;
                int y = (int)(GameField.mouseEvent.getY())/Settings.TILE_HEIGHT;
                y*=Settings.TILE_HEIGHT;
                new NormalTower(x, y);
                holdItem = false;
            }
        }

    }
    public void render(GraphicsContext gc){
        if(holdItem){
            gc.drawImage(image, GameField.mouseEvent.getX() - Settings.TILE_WIDTH/2,
                    GameField.mouseEvent.getY() - Settings.TILE_HEIGHT/2,
                    this.rect.getWidth(), this.rect.getHeight());
        }
        gc.drawImage(image, this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
    }

}
