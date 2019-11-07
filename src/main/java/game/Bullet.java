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

    /**
     * Create a bullet with this bullet configuration (damage, maxDistance, ...), set the velocity toward the enemies,
     * and append the bullet to GameField.entities
     * @param startX bullet start x-position
     * @param startY bullet start y-position
     */
    public void createBullet(double startX, double startY) {
        Bullet newBullet = this.clone();
        if (newBullet.getTarget() != null) {
            newBullet.active = true;
            newBullet.setPosition(new Vector2D(startX + Settings.TILE_WIDTH * 0.5, startY + Settings.TILE_HEIGHT * 0.5));
            Vector2D towerToTarget = new Vector2D(newBullet.getTarget().getPosition().x - startX, newBullet.getTarget().getPosition().y - startY);
            newBullet.getVelocity().setAngle(towerToTarget.getAngle()); // to preserve speed
            GameField.gameEntities.add(newBullet);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.x, position.y, Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);

        gc.setStroke(Color.MAGENTA);
        gc.strokeRect(position.x, position.y, Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);
    }

    private double distanceTraveled = 0;
    @Override
    public void run() {
        position.add(velocity.x, velocity.y);
        distanceTraveled += velocity.getLength();
        if (distanceTraveled > maxDistance) {
            this.deactivate();
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        GameField.gameEntities.remove(this);
    }

    public Bullet clone() {
        Bullet clone = new Bullet(this.damage, this.maxDistance, "unknown", this.velocity.x, this.velocity.y);
        clone.setImage(this.image);
        clone.setTarget(this.target);
        return clone;
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
