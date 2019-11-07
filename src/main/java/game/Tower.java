package game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Tower extends AbstractEntity {
    protected int damage;
    protected int fireRate;
    protected int fireRange;
    int timeToFire = 0;
    protected Image gunImg; // super.image is game.base image.

    public Tower(double x, double y, String baseImageURL, String gunImageURL) {
        super(x, y, baseImageURL);
        this.gunImg = loadImage(gunImageURL);
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

    SnapshotParameters params = new SnapshotParameters();
    ImageView iv2 = new ImageView();
    @Override
    public void render(GraphicsContext gc) {
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
}
