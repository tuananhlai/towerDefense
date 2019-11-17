package game.enemy;

import game.*;
import game.screen.PlayScreen;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class AbstractEnemy extends AbstractEntity implements Collider {
    protected double hp;
    protected double maxHP;
    protected double defense = 0;
    protected Vector2D velocity;
    protected int dropReward;
    protected double v_max; //max(velocityX, velocityY)

    public AbstractEnemy(double x, double y, String imageURL) {
        this(x, y, imageURL, 0, 0, 0);
    }
    public AbstractEnemy(double x, double y, String url, int maxHP, double velocityX, double velocityY) {
        super(x, y, url);
        this.hp = maxHP;
        velocity = new Vector2D(velocityX, velocityY);
    }
    public AbstractEnemy(double x, double y, Image img) {
        super(x, y, img, true);
        velocity = new Vector2D();
    }

    private SnapshotParameters params = new SnapshotParameters();
    private ImageView rotateView = new ImageView();
    @Override
    public void render(GraphicsContext gc) {
        params.setFill(Color.TRANSPARENT);

        rotateView.setImage(image);
        rotateView.setRotate(this.velocity.getAngle());
        Image img = rotateView.snapshot(params, null);

        gc.drawImage(img, position.x, position.y, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);

        //render health bar
        double percentHealth = hp * 1.0 / maxHP;
        if (percentHealth < 0.2) {
            gc.setFill(Color.RED);
        } else if (percentHealth < 0.7) {
            gc.setFill(Color.ORANGE);
        } else {
            gc.setFill(Color.LIMEGREEN);
        }
        gc.fillRect(this.position.x, this.position.y, 40 * percentHealth, 5);
    }

    /**
     * Register damage caused by game.tower, etc.
     * @param damage damage inflicted by towers.
     */
    public void takeDamage(double damage) {
        if (damage - defense > 0) {
            hp -= (damage - defense);
        }
    }

    /**
     * Calulate direction and set velovity
     */
    public void findPath(){
        int me = 2, r = 3, l = 4, u = 5, d = 6, a = 7, b = 8;
        int x = (int)(position.x/Settings.TILE_WIDTH);
        int y = (int)(position.y/Settings.TILE_HEIGHT);
        if(position.x <= 0){
            a = 1;
        }
        else{
            a = GameField.map[(int)(position.y/Settings.TILE_WIDTH)][(int)((position.x - v_max)/Settings.TILE_HEIGHT)];
        }
        if(position.y <= 0){
            b = 1;
        }
        else{
            b = GameField.map[(int)((position.y - v_max)/Settings.TILE_WIDTH)][(int)((position.x)/Settings.TILE_HEIGHT)];
        }

        me = GameField.map[(int)(position.y/Settings.TILE_WIDTH)][(int)((position.x)/Settings.TILE_HEIGHT)];
        if(x == Settings.MAP_WIDTH_IN_TILES - 1){
            r = 3;
        }else{
            r = GameField.map[(int)(position.y/Settings.TILE_WIDTH)][(int)((position.x)/Settings.TILE_HEIGHT) + 1];
        }
        if(x == 0){
            l = 4;
        }else{
            l = GameField.map[(int)(position.y/Settings.TILE_WIDTH)][(int)((position.x)/Settings.TILE_HEIGHT) - 1];
        }
        if(y == Settings.MAP_HEIGHT_IN_TILES - 1){
            d = 6;
        }else{
            d = GameField.map[(int)(position.y/Settings.TILE_WIDTH) + 1][(int)((position.x)/Settings.TILE_HEIGHT)];
        }
        if(y == 0){
            u = 5;
        }else{
            u = GameField.map[(int)((position.y)/Settings.TILE_WIDTH) - 1][(int)((position.x)/Settings.TILE_HEIGHT)];
        }

        ////
        if(velocity.x == v_max){
            if(me == r){
                velocity.set(v_max, 0);
            }
            else if(me == u){
                velocity.set(0, -v_max);
            }
            else if(me == d){
                velocity.set(0, v_max);
            }
            else{
                velocity.set(0, 0);
            }
        }
        else if(velocity.x == -v_max){
            if(a == me){
                velocity.set(-v_max, 0);
            }
            else if(me == u){
                velocity.set(0, -v_max);
            }
            else if(me == d){
                velocity.set(0, v_max);
            }
            else{
                velocity.set(0, 0);
            }
        }
        else if(velocity.y == v_max){
            if(me == d){
                velocity.set(0, v_max);
            }
            else if(me == r){
                velocity.set(v_max, 0);
            }
            else if(me == l){
                velocity.set(-v_max, 0);
            }
            else{
                velocity.set(0, 0);
            }
        }
        else if(velocity.y == -v_max){
            if(b == me){
                velocity.set(0, -v_max);
            }
            else if(me == r){
                velocity.set(v_max, 0);
            }
            else if(me == l){
                velocity.set(-v_max, 0);
            }
            else{
                velocity.set(0, 0);
            }
        }
        else{
            velocity.set(0, 0);
        }
        ///
    }

    @Override
    public void deactivate() {
        active = false;
        GameField.gameEntities.remove(this);
    }

    @Override
    public void run() {
        if (hp <= 0) {
            this.deactivate();
            PlayScreen.rewardPlayer(dropReward);
            return;
        }
        findPath();
        if(velocity.x == 0 && velocity.y == 0){
            deactivate();
            PlayScreen.playerTakeDamage();
        }
        position.add(velocity.x, velocity.y);
    }

    @Override
    public void reset() {
        active = true;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getSpeed() {
        return velocity.getLength();
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setDirection(int angle) {
        velocity.setAngle(angle);
    }

    public void setVelocity(double velocityX, double velocityY) {
        v_max = Math.max(velocityX, velocityY);
        velocity.set(velocityX, velocityY);
    }

    public boolean isActive() {
        return active;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setDropReward(int dropReward) {
        this.dropReward = dropReward;
    }

    @Override
    public double getCenterX() {
        return position.x + Settings.ENEMY_WIDTH * 0.5;
    }

    @Override
    public double getCenterY() {
        return position.y + Settings.ENEMY_HEIGHT * 0.5;
    }
}
