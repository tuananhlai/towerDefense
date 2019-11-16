package game.tower;

import game.*;
import game.enemy.AbstractEnemy;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Tower extends AbstractTile {
    private int fireRate = 0;
    int fireRange = 0;
    Bullet bullet = null;
    private Image gunImg = null; // super.image is base image.

    Tower(double x, double y, String baseImageURL, String gunImageURL) {
        super(Settings.TOWER, x, y, baseImageURL);
        this.gunImg = Settings.loadImage(gunImageURL);
        GameField.unusablePositions.add(new Vector2D(x, y));
    }

    private AbstractEnemy getNearestEnemy() {
        AbstractEnemy nearestEnemy = null;
        double minDistance = Double.MAX_VALUE;
        for (AbstractEntity entity : GameField.gameEntities) {
            if(entity instanceof AbstractEnemy && entity.isActive()){
                Vector2D towerCenterPos = new Vector2D(position.x + Settings.TILE_WIDTH * 0.5, position.y + Settings.TILE_HEIGHT * 0.5);
                Vector2D entityCenterPos = new Vector2D(entity.getPosition().x + Settings.ENEMY_WIDTH * 0.5, entity.getPosition().y + Settings.ENEMY_HEIGHT * 0.5);
                double distanceToEnemy = towerCenterPos.distanceTo(entityCenterPos);
                if (distanceToEnemy <= fireRange && distanceToEnemy < minDistance) {
                    nearestEnemy = (AbstractEnemy) entity;
                    minDistance = this.position.distanceTo(entity.getPosition());
                }
            }
        }
        return nearestEnemy;
    }

    @Override
    public void run() {
        pickTarget();
        fire();
    }

    private SnapshotParameters params = new SnapshotParameters();
    private ImageView gunView = new ImageView();
    @Override
    public void render(GraphicsContext gc) {
        params.setFill(Color.TRANSPARENT);
        // Rotate gun barrel to nearest enemy location
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
        gc.drawImage(gun, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
    }

    private int fireRateCount = 0;
    public void fire() {
        fireRateCount++;
        if (bullet.getTarget() != null && fireRateCount * fireRate > 60) {
            createBullet(this.position.x, this.position.y);
            fireRateCount = 0;
        }
    }

    private void pickTarget() {
        if (bullet.getTarget() == null) {
            bullet.setTarget(getNearestEnemy());
        } else if (isOutOfRange(bullet.getTarget())){
            bullet.setTarget(null);
        } else if (!bullet.getTarget().isActive()) { // even if the enemy is dead, we still have its reference, so we can check if its hp is below 0.
            bullet.setTarget(null);
        }
    }

    /**
     * Create a bullet with this tower's bullet configuration (damage, maxDistance, ...), set the velocity toward the enemies,
     * and append the bullet to GameField.entities
     * @param startX bullet start x-position
     * @param startY bullet start y-position
     */
    private void createBullet(double startX, double startY) {
        Bullet newBullet = bullet.clone();
        if (newBullet.getTarget() != null) {
            newBullet.activate();
            newBullet.setPosition(new Vector2D(this.getCenterX() - bullet.getImage().getWidth() * 0.5, this.getCenterY() - bullet.getImage().getHeight() * 0.5));
            Vector2D towerToTarget = new Vector2D(newBullet.getTarget().getPosition().x - startX, newBullet.getTarget().getPosition().y - startY);
            newBullet.getVelocity().setAngle(towerToTarget.getAngle()); // to preserve speed
            GameField.gameEntities.add(newBullet);
        }
    }

    public Bullet getBullet() {
        return bullet;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = (int) fireRate;
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