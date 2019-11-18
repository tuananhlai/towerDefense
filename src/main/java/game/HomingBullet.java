package game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class HomingBullet extends Bullet {
    public HomingBullet(double damage, double maxDistance, Image image, double velocityX, double velocityY) { //set up bullet
        super(damage, maxDistance, image, velocityX, velocityY);
    }

    SnapshotParameters params = new SnapshotParameters();
    ImageView bulletView = new ImageView();
    @Override
    public void render(GraphicsContext gc) {
        params.setFill(Color.TRANSPARENT);
        bulletView.setImage(image);
        Vector2D towerToEnemy = new Vector2D(getTarget().getPosition().x - this.position.x,
                getTarget().getPosition().y - this.position.y);
        bulletView.setRotate(towerToEnemy.getAngle() - 90); // we need to +90 degree because of the original orientation of bullet.
        Image bullet = bulletView.snapshot(params, null);
        gc.drawImage(bullet, position.x, position.y, bullet.getWidth(), bullet.getHeight());

        // render hitbox
//        gc.setStroke(Color.MAGENTA);
//        gc.strokeRect(getBoundary().getMinX(), getBoundary().getMinY(), getBoundary().getWidth(), getBoundary().getHeight());
    }

    @Override
    public void run() {
        Vector2D towerToTarget = new Vector2D(this.getTarget().getCenterX() - this.getCenterX(), this.getTarget().getCenterY() - this.getCenterY());
        this.getVelocity().setAngle(towerToTarget.getAngle());
        super.run();
    }

    @Override
    public HomingBullet clone() {
        HomingBullet clone = new HomingBullet(this.getDamage(), this.getMaxDistance(), this.image, this.getVelocity().x, this.getVelocity().y);
        clone.setImage(this.image);
        clone.setTarget(this.getTarget());
        return clone;
    }
}
