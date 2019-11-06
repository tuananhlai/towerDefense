package game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Tower extends AbstractTile {
    protected int fireRate;
    protected int fireRange;
    protected Bullet bullet;
    protected Image gunImg; // super.image is game.base image.

    public Tower(double x, double y, String baseImageURL, String gunImageURL) {
        super(Settings.TOWER, x, y, baseImageURL);
        this.gunImg = loadImage(gunImageURL);
    }

    public AbstractEnemy getNearestEnemy() { // TODO: change how towers choose target
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
        if (nearestEnemy != null) {
            Vector2D towerToEnemy = new Vector2D(nearestEnemy.getPosition().x - this.position.x,
                    nearestEnemy.getPosition().y - this.position.y);
            iv2.setRotate(towerToEnemy.getAngle() + 90); // we need to +90 degree because of the original orientation of gunImg.
        }
        Image gun = iv2.snapshot(params, null);

        gc.drawImage(image, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
        // TODO: if you want to draw a perfectly rotating gun. with no resizing... You must render at center, and use
        // original resolution.
        gc.drawImage(gun, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);

        // Render bullet trajectory (center to center)
        gc.setStroke(Color.RED);
        if (nearestEnemy != null) {
            gc.strokeLine(this.position.x + 0.5 * Settings.TILE_WIDTH,
                    this.position.y + 0.5 * Settings.TILE_HEIGHT,
                    nearestEnemy.getPosition().x + 0.5 * Settings.TILE_WIDTH,
                    nearestEnemy.getPosition().y + 0.5 * Settings.TILE_HEIGHT);
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
}
