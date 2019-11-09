package game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class AbstractEnemy extends AbstractEntity {
    protected int hp;
    protected int defense;
    protected Vector2D velocity;

    protected int dropReward;
    int wayPointIndex;

    public AbstractEnemy(double x, double y, String imageURL) {
        this(x, y, imageURL, 0, 0, 0);
    }
    public AbstractEnemy(double x, double y, String url, int hp, double velocityX, double velocityY) {
        super(x, y, url);
        this.hp = hp;
        velocity = new Vector2D(velocityX, velocityY);
    }

    SnapshotParameters params = new SnapshotParameters();
    ImageView rotateView = new ImageView();
    @Override
    public void render(GraphicsContext gc) { // TODO: rotate when turn.
        params.setFill(Color.TRANSPARENT);

        rotateView.setImage(image);
        rotateView.setRotate(this.velocity.getAngle());
        Image img = rotateView.snapshot(params, null);

        gc.drawImage(img, position.x, position.y, 70, 60);
        // draw way point
        gc.setFill(Color.RED);
        gc.fillOval(position.x, position.y, 7, 7);
    }

    /**
     * Register damage caused by game.tower, etc.
     * @param damage damage inflicted by towers.
     */
    public void takeDamage(int damage) {
        if (damage - defense > 0) {
            hp -= (damage - defense);
        }
        if (hp <= 0) {
            GameField.score += 1;
            System.err.println(GameField.score);
            this.deactivate();
        }
    }

    /**
     * Calculate which direction to go to.
     */
    public void calculateDirection() {
        if (wayPointIndex >= GameField.wayPoints.length) {
            return;
        }

        Vector2D currentWP = GameField.wayPoints[wayPointIndex];
        if (this.position.distanceTo(currentWP) <= this.getSpeed()) {
            position.set(currentWP);
            Vector2D nextWayPoint = getNextWayPoint();
            if (nextWayPoint == null) {
                GameField.HP -= 1;
                this.deactivate();
                return;
            }
            double deltaX = nextWayPoint.x - this.position.x;
            double deltaY = nextWayPoint.y - this.position.y;
            if (deltaX > getSpeed()) {
                setDirection(Vector2D.RIGHT);
            } else if (deltaX < -getSpeed()){
                setDirection(Vector2D.LEFT);
            } else if (deltaY > getSpeed()) {
                setDirection(Vector2D.DOWN);
            } else if (deltaY <= -getSpeed()) {
                setDirection(Vector2D.UP);
            }
        }
    }

    public Vector2D getNextWayPoint() {
        if (wayPointIndex < GameField.wayPoints.length - 1)
            return GameField.wayPoints[++wayPointIndex];
        return null;
    }

    public void deactivate() {
        active = false;
        GameField.gameEntities.remove(this);
    }

    @Override
    public void run() {
        calculateDirection();
        position.add(velocity.x, velocity.y);
    }

    @Override
    public void reset() {
        active = true;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public double getSpeed() {
        return velocity.getLength();
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setDirection(int angle) {
        velocity.setAngle(angle);
    }

    public void setVelocity(double velocityX, double velocityY) {
        velocity.set(velocityX, velocityY);
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Enemy[" + this.getClass().getName() + ", (" + this.position.x + ", " + this.position.y + ")]";
    }
}
