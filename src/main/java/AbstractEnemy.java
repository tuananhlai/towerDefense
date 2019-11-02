import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AbstractEnemy extends AbstractEntity {
    protected int hp;
    protected Vector2D velocity;
    protected boolean active;

    public AbstractEnemy(double x, double y, String imageURL) {
        this(x, y, imageURL, 0, 0, 0, false);
    }
    public AbstractEnemy(double x, double y, String url, int hp, double velocityX, double velocityY, boolean active) {
        super(x, y, url);
        this.hp = hp;
        velocity = new Vector2D(velocityX, velocityY);
        this.active = active;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.x, position.y, 200, 160);
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            this.deactivate();
        }
    }

    public void deactivate() {
        active = false;
    }

    @Override
    public void run() {
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

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(double velocityX, double velocityY) {
        velocity.set(velocityX, velocityY);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
