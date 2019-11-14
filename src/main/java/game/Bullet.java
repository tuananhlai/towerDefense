package game;

import game.Enemy.AbstractEnemy;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bullet extends AbstractEntity implements Collider, Cloneable {
    private AbstractEnemy target = null;
    private Vector2D velocity = null;
    private int damage;
    private double maxDistance;

    public Bullet(int damage, double maxDistance, Image image, double velocityX, double velocityY) { //set up bullet
        super(0, 0, image, false); // set active = false to prevent the super constructor from adding this to gameEntities
        this.damage = damage;
        this.maxDistance = maxDistance;
        this.velocity = new Vector2D(velocityX, velocityY);
    }

    SnapshotParameters params = new SnapshotParameters();
    ImageView bulletView = new ImageView();
    boolean isOnce = true;
    @Override
    public void render(GraphicsContext gc) {
        params.setFill(Color.TRANSPARENT);
        bulletView.setImage(image);
        if (isOnce) {
            Vector2D towerToEnemy = new Vector2D(target.getPosition().x - this.position.x,
                    target.getPosition().y - this.position.y);
            bulletView.setRotate(towerToEnemy.getAngle() - 90); // we need to +90 degree because of the original orientation of bullet.
            isOnce = false;
        }
        Image bullet = bulletView.snapshot(params, null);
        gc.drawImage(bullet, position.x, position.y, bullet.getWidth(), bullet.getHeight());

        // render hitbox
        gc.setStroke(Color.MAGENTA);
        gc.strokeRect(getBoundary().getMinX(), getBoundary().getMinY(), getBoundary().getWidth(), getBoundary().getHeight());
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

    @Override
    public Bullet clone() {
        Bullet clone = new Bullet(this.damage, this.maxDistance, this.image, this.velocity.x, this.velocity.y);
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
        return new Rectangle2D(this.position.x,
                this.position.y + (image.getHeight() - image.getWidth()) / 2,
                image.getWidth(),
                image.getWidth());
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

    @Override
    public double getCenterX() {
        return 0;
    }

    @Override
    public double getCenterY() {
        return 0;
    }
}
