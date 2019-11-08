package game.tower;

import game.*;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Tower extends AbstractTile {
    protected int fireRate = 0;
    protected int fireRange = 0;
    protected Bullet bullet = null;
    protected Image gunImg = null; // super.image is game.base image.

    public Tower(double x, double y, String baseImageURL, String gunImageURL) {
        super(Settings.TOWER, x, y, baseImageURL);
        this.gunImg = loadImage(gunImageURL);
    }

    public AbstractEnemy getNearestEnemy() { // TODO: change how towers choose target
        AbstractEnemy nearestEnemy = null;
        double minDistance = Double.MAX_VALUE;
        for (AbstractEntity entity : GameField.gameEntities) {
            double distanceToEnemy = this.position.distanceTo(entity.getPosition());
            if (entity instanceof AbstractEnemy && entity.isActive() && distanceToEnemy < minDistance && distanceToEnemy <= fireRange) {
                nearestEnemy = (AbstractEnemy) entity;
                minDistance = this.position.distanceTo(entity.getPosition());
            }
        }
        return nearestEnemy;
    }

    @Override
    public void run() {
        pickTarget();
        fire();
    }

    SnapshotParameters params = new SnapshotParameters();
    ImageView gunView = new ImageView();
    @Override
    public void render(GraphicsContext gc) {
        params.setFill(Color.TRANSPARENT);
        // Rotate gun to nearest enemy location
        gunView.setImage(gunImg);
        AbstractEnemy target = bullet.getTarget();
        if (target != null) {
            Vector2D towerToEnemy = new Vector2D(target.getPosition().x - this.position.x,
                    target.getPosition().y - this.position.y);
            gunView.setRotate(towerToEnemy.getAngle() + 90); // we need to +90 degree because of the original orientation of gunImg.
        }
        Image gun = gunView.snapshot(params, null);

        // Draw gun and base
        gc.drawImage(image, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
        // TODO: if you want to draw a perfectly rotating gun. with no resizing... You must render at center, and use original resolution.
        gc.drawImage(gun, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);

        // Render bullet trajectory (center to center)
//        gc.setStroke(Color.RED);
//        if (target != null) {
//            gc.strokeLine(this.position.x + 0.5 * Settings.TILE_WIDTH,
//                    this.position.y + 0.5 * Settings.TILE_HEIGHT,
//                    target.getPosition().x + 0.5 * Settings.TILE_WIDTH,
//                    target.getPosition().y + 0.5 * Settings.TILE_HEIGHT);
//        }

        //render fire range
        gc.setStroke(Color.RED);

    }

    private int fireRateCount = 0;
    public void fire() {
        fireRateCount++;
        if (bullet.getTarget() != null && fireRateCount > 30) {
            createBullet(this.position.x, this.position.y);
            fireRateCount = 0;
        }
    }

    public void pickTarget() {
        if (bullet.getTarget() == null) {
            bullet.setTarget(getNearestEnemy());
        } else if (isOutOfRange(bullet.getTarget())){
            bullet.setTarget(null);
        } else if (bullet.getTarget().getHp() <= 0) { // even if the enemy is dead, we still have its reference, so we can check if its hp is below 0.
            bullet.setTarget(null);
        }
    }

    /**
     * Create a bullet with this bullet configuration (damage, maxDistance, ...), set the velocity toward the enemies,
     * and append the bullet to GameField.entities
     * @param startX bullet start x-position
     * @param startY bullet start y-position
     */
    public void createBullet(double startX, double startY) {
        Bullet newBullet = bullet.clone();
        if (newBullet.getTarget() != null) {
            newBullet.activate();
            newBullet.setPosition(new Vector2D(startX + Settings.TILE_WIDTH * 0.5, startY + Settings.TILE_HEIGHT * 0.5));
            Vector2D towerToTarget = new Vector2D(newBullet.getTarget().getPosition().x - startX, newBullet.getTarget().getPosition().y - startY);
            newBullet.getVelocity().setAngle(towerToTarget.getAngle()); // to preserve speed
            GameField.gameEntities.add(newBullet);
        }
    }

    public Bullet getBullet() {
        return bullet;
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

    public boolean isOutOfRange(AbstractEnemy target) {
        return this.position.distanceTo(bullet.getTarget().getPosition()) > fireRange;
    }
}