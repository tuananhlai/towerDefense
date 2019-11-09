package game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.FileInputStream;
import java.util.Set;

public abstract class Tower extends AbstractEntity {
    protected int id;
    protected boolean isClicked = false;
    protected boolean isHover = false;
    protected int damage;
    protected int fireRate;
    protected int fireRange;
    protected int level = 0; //level tower, max 3, after update, price x double, damage x 3
    protected Rectangle sellRect;
    protected Rectangle upgradeRect;
    protected Image sellImage = null;
    protected Image upgradeImage = null;
    int timeToFire = 0;
    protected int type = Settings.MACHINE_GUN_TOWER_ITEM;
    protected Image gunImg; // super.image is game.base image.

    public Tower(double x, double y, String baseImageURL, String gunImageURL) {
        super(x, y, baseImageURL);
        this.rect.setX(x);
        this.rect.setY(y);
        this.rect.setWidth(Settings.TILE_WIDTH);
        this.rect.setHeight(Settings.TILE_HEIGHT);
        this.gunImg = loadImage(gunImageURL);
        id = this.hashCode();
        sellRect = new Rectangle((int)position.x + Settings.TILE_WIDTH,
                (int)position.y - 30, 50, 30);
        upgradeRect = new Rectangle((int)position.x + Settings.TILE_WIDTH,
                (int)position.y - 65, 50, 30);
        try{
            sellImage = new Image(new FileInputStream("assets/Retina/sell.png"));
            upgradeImage = new Image(new FileInputStream("assets/Retina/upgrade.png"));
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
    }

    public AbstractEnemy getNearestEnemy() {
        AbstractEnemy nearestEnemy = null;
        double minDistance = Double.MAX_VALUE;
        for (AbstractEntity entity : GameField.gameEntities) {
            double distanceToEnemy = this.position.distanceTo(entity.getPosition());
            if (entity instanceof AbstractEnemy && entity.isActive() && distanceToEnemy < minDistance && distanceToEnemy < fireRange) {
                nearestEnemy = (AbstractEnemy) entity;
                minDistance = this.position.distanceTo(entity.getPosition());
            }
        }
        return nearestEnemy;
    }

    @Override
    public void run() {

    }
    public void click(MouseEvent button){
        //set isClicked to false of all other Tower Object
            //code here

        if(isClicked && sellRect.contains(button.getX(), button.getY()) && this.id == GameField.id){
            isClicked = false;
            setActive(false);
            GameField.score += (int)type/2;
            return;
        }
        else if(isClicked && upgradeRect.contains(button.getX(), button.getY()) && this.id == GameField.id){
            if(level==2)    return;
            isClicked = false;
            level++;
            this.damage = 3*this.damage;
            this.fireRange += 100;
            GameField.score -= level*this.type;
            return;
        }
        if(this.rect.contains(button.getX(), button.getY())){
            isClicked = !isClicked;
            GameField.id = this.id;
        }
    }
    public void hover(MouseEvent button){
        if(this.rect.contains(button.getX(), button.getY())){
            isHover = true;
//            System.err.println("Da click tower");
        }
        else{
            isHover = false;
        }

    }

    SnapshotParameters params = new SnapshotParameters();
    ImageView iv2 = new ImageView();
    @Override
    public void render(GraphicsContext gc) {
        //render detail
        if(isHover){
            new Detail(position.x, position.y, this.damage, this.type, this.fireRange).render(gc);
//            System.err.println("Da ve tower detail");
        }
        if(isClicked && this.id == GameField.id){
//            System.err.println("Clicked");
            //draw sell button
//            gc.fillRect((int)position.x + Settings.TILE_WIDTH, (int)position.y - 30, 50, 30);
            gc.drawImage(sellImage,(int)position.x + Settings.TILE_WIDTH, (int)position.y - 30, 50, 30);
            //draw upgrade button
//            gc.setFill(Color.PINK);
//            gc.fillRect((int)position.x + Settings.TILE_WIDTH, (int)position.y - 65, 50, 30);
            gc.drawImage(upgradeImage,(int)position.x + Settings.TILE_WIDTH, (int)position.y - 65, 50, 30);
        }
        else{
            isClicked = false;
        }
        params.setFill(Color.TRANSPARENT);
        AbstractEnemy nearestEnemy = getNearestEnemy();
        // Rotate gun to nearest enemy location
        iv2.setImage(gunImg);
        if (nearestEnemy != null) iv2.setRotate(new Vector2D(-nearestEnemy.getPosition().x + this.position.x, -nearestEnemy.getPosition().y + this.position.y).getAngle() - 90);
        Image gun = iv2.snapshot(params, null);

        gc.drawImage(image, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
        // TODO: if you want to draw a perfectly rotating gun. with no resizing... You must render at center, and use
        // original resolution.
        gc.drawImage(gun, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);

        // Render bullet trajectory
        gc.setStroke(Color.RED);
        if (nearestEnemy != null) {
            if(timeToFire%30 == 0){
                CuongBullet bullet = new CuongBullet(position.x + Settings.TILE_WIDTH/2, position.y + Settings.TILE_HEIGHT/2);
                bullet.setTarget(new Vector2D(nearestEnemy.getPosition().x + Settings.TILE_WIDTH/2, nearestEnemy.getPosition().y + Settings.TILE_HEIGHT/2));
                bullet.setDamage(5);
            }
            timeToFire++;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public int getFireRange() {
        return fireRange;
    }

    public void setFireRange(int fireRange) {
        this.fireRange = fireRange;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
