package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends AbstractEntity {
    private AbstractEnemy target = null;
    private Vector2D velocity = null;
    private double damage;
    private double maxDistance;

    public Bullet(double damage, double maxDistance, String bulletImgURL, double velocityX, double velocityY) { //set up bullet
        super(0, 0, bulletImgURL, false); // set active = false to prevent the super constructor from adding this to gameEntities
        this.damage = damage;
        this.maxDistance = maxDistance;
        this.velocity = new Vector2D(velocityX, velocityY);
    }

    public void createBullet(double startX, double startY, AbstractEnemy target) { // for shooting
        active = true;
        this.position = new Vector2D(startX, startY);
        Vector2D towerToTarget = new Vector2D(target.getPosition().x - startX, target.getPosition().y - startX);
        velocity.setAngle(towerToTarget.getAngle()); // to preserve speed
        this.setTarget(target);
        GameField.gameEntities.add(this);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.x, position.y, Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);

        gc.setStroke(Color.MAGENTA);
        gc.strokeRect(position.x, position.y, Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);
    }

    @Override
    public void run() {
        position.add(velocity.x, velocity.y);
    }

    public AbstractEnemy getTarget() {
        return target;
    }

    public void setTarget(AbstractEnemy target) {
        this.target = target;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
