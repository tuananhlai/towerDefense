package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CuongBullet extends AbstractEntity {
    //private double velocity;
    private Vector2D target;
    double distanceToTarget = 0;
    private int damage = 5;
    double alpha = 0;
    double a = 0;
    public CuongBullet(double x, double y){
        //velocity = Settings.BULLET_VELOCITY;
        super(x, y, "assets/towers/bullets/towerDefense_tile251.png");
        circle = new Circle();
//        setPosition(new Vector2D(x, y));
        circle.setCenterX(position.x);
        circle.setCenterY(position.y);
        circle.setRadius(5);
    }
    @Override
    public void run() {
        if(circle.getCenterX() > Settings.MAP_WIDTH || circle.getCenterX() < 0 || circle.getCenterY() > Settings.MAP_HEIGHT || circle.getCenterY() < 0){
            setActive(false);
            return;
        }
        if(distanceToTarget - a < 0){
            setActive(false);
            return;
        }

        double len = distanceToTarget /6;
        a += distanceToTarget /6;

        if(target.x < circle.getCenterX()){
            if(target.y < circle.getCenterY()){
                circle.setCenterX(circle.getCenterX() - len*Math.cos(alpha));
                circle.setCenterY(circle.getCenterY() - len*Math.sin(alpha));
            }
            else{
                circle.setCenterX(circle.getCenterX() - len*Math.cos(alpha));
                circle.setCenterY(circle.getCenterY() + len*Math.sin(alpha));
            }
        }
        else{
            if(target.y < circle.getCenterY()){
                circle.setCenterX(circle.getCenterX() + len*Math.cos(alpha));
                circle.setCenterY(circle.getCenterY() - len*Math.sin(alpha));
            }
            else{
                circle.setCenterX(circle.getCenterX() + len*Math.cos(alpha));
                circle.setCenterY(circle.getCenterY() + len*Math.sin(alpha));
            }
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isCollision(AbstractEntity alien){
        if(circle.intersects(alien.position.x + 20, alien.position.y + 20, 10, 10)){
            this.deactivate();
            return true;
        }
        return false;
    }
    public void setTarget(Vector2D target) {
        this.target = target;
        distanceToTarget = target.distanceTo(position);
        alpha = Math.atan(Math.abs((circle.getCenterY() - target.y)/(circle.getCenterX() - target.x)));
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.MAGENTA);
        gc.fillOval(circle.getCenterX() - 5, circle.getCenterY() - 5, 10, 10);
//        gc.drawImage(image, circle.getCenterX() - 5, circle.getCenterY() - 5, 50, 50);
    }

    public Circle getCircle(){
        return circle;
    }

    public void deactivate() {
        active = false;
        GameField.gameEntities.remove(this);
    }
}
