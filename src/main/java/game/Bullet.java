package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends AbstractEntity implements Collider{
    private AbstractEnemy target = null;
    private Vector2D velocity = null;
    private int damage;
    private double maxDistance;

    public Bullet(int damage, double maxDistance, String bulletImgURL, double velocityX, double velocityY) { //set up bullet
        super(0, 0, bulletImgURL, false); // set active = false to prevent the super constructor from adding this to gameEntities
        this.damage = damage;
        this.maxDistance = maxDistance;
        this.velocity = new Vector2D(velocityX, velocityY);
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
            return;
        }
        if (checkEnemies() != null ) {
            checkEnemies().takeDamage(this.damage);
            this.deactivate();
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        GameField.gameEntities.remove(this);
    }

    public void activate() {
        active = true;
    }

    public Bullet clone() {
        Bullet clone = new Bullet(this.damage, this.maxDistance, "unknown", this.velocity.x, this.velocity.y);
        clone.setImage(this.image);
        clone.setTarget(this.target);
        return clone;
    }

    public AbstractEnemy checkEnemies() {
        for (AbstractEntity entity : GameField.gameEntities) {
            if (entity instanceof AbstractEnemy) {
                if ((this.intersects((AbstractEnemy) entity))) {
                    return (AbstractEnemy) entity;
                }
            }
        }
        return null;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.position.x, this.position.y, Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);
    }

    @Override
    public boolean intersects(Collider other) {
        return this.getBoundary().intersects(other.getBoundary());
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

    public int getDamage() {
        return damage;
    }
}
