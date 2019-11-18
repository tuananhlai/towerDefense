package game.tower;

import game.*;

public class SpreadTower extends Tower {
    public SpreadTower() {
        this(3 * Settings.TILE_WIDTH, 2 * Settings.TILE_HEIGHT);
    }

    public SpreadTower(double x, double y) {
        super(x, y, Settings.SPREAD_BASE, Settings.SPREAD_GUN);
        this.setFireRate(Settings.SPREAD_TOWER_FIRE_RATE);
        this.setFireRange(Settings.SPREAD_TOWER_RANGE);
        this.bullet = new Bullet(Settings.SPREAD_TOWER_DAMAGE, this.fireRange, Settings.SPREAD_BULLET_IMAGE, 10, 0);
        this.setMedia(MediaManager.NORMAL_GUN_SHOT_FX);
    }

    @Override
    protected void createBullet(double startX, double startY) {
        Bullet newBullet = bullet.clone();
        Vector2D towerToTarget = null;
        if (newBullet.getTarget() != null) {
            newBullet.activate();
            newBullet.setPosition(new Vector2D(this.getCenterX() - bullet.getImage().getWidth() * 0.5, this.getCenterY() - bullet.getImage().getHeight() * 0.5));
            towerToTarget = new Vector2D(newBullet.getTarget().getPosition().x - startX, newBullet.getTarget().getPosition().y - startY);
            newBullet.getVelocity().setAngle(towerToTarget.getAngle() - 20); // to preserve speed
            GameField.gameEntities.add(newBullet);
        }
        Bullet newBullet2 = bullet.clone();
        if (newBullet2.getTarget() != null) {
            newBullet2.activate();
            newBullet2.setPosition(new Vector2D(this.getCenterX() - bullet.getImage().getWidth() * 0.5, this.getCenterY() - bullet.getImage().getHeight() * 0.5));
            newBullet2.getVelocity().setAngle(towerToTarget.getAngle() + 20); // to preserve speed
            GameField.gameEntities.add(newBullet2);
        }
        Bullet newBullet3 = bullet.clone();
        if (newBullet3.getTarget() != null) {
            newBullet3.activate();
            newBullet3.setPosition(new Vector2D(this.getCenterX() - bullet.getImage().getWidth() * 0.5, this.getCenterY() - bullet.getImage().getHeight() * 0.5));
            newBullet3.getVelocity().setAngle(towerToTarget.getAngle()); // to preserve speed
            GameField.gameEntities.add(newBullet3);
        }
    }
}
