package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractEntity {
    protected Vector2D position;
    protected Image image = null;
    protected boolean active;
    protected int rowIndex; // i
    protected int colIndex; // j

    public AbstractEntity() {
        this(0, 0, null);
    }

    public AbstractEntity(double x, double y) {
        this(x, y, null);
    }

    public AbstractEntity(String imageURL) {
        this(0, 0, imageURL);
    }

    public AbstractEntity(Vector2D position, String imageURL) {
        this(position.x, position.y, imageURL);
    }

    public AbstractEntity(double x, double y, String imageURL) {
        this(x, y, imageURL, true);
    }

    /**
     * Load image from input url, print error if file not found.
     * Initialize position, set state to active, and add newly created objects to list objects.
     * @param x input x-position
     * @param y input y-position
     * @param imageURL object's image url
     */
    public AbstractEntity(double x, double y, String imageURL, boolean active) {
        if (imageURL != null) this.image = Settings.loadImage(imageURL);
        this.active = active;
        position = new Vector2D(x, y);
        if (this.active) GameField.gameEntities.add(this);
        System.out.println(this.getClass().getName() + " " + GameField.gameEntities.size());
    }

    public AbstractEntity(double x, double y, Image image, boolean active) {
        this.image = image;
        this.active = active;
        position = new Vector2D(x, y);
        if (this.active) GameField.gameEntities.add(this);
        System.out.println(this.getClass().getName() + " " + GameField.gameEntities.size());
    }

    public abstract void render(GraphicsContext gc);

    public abstract void run();

    public void reset() {
        active = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public abstract double getCenterX();

    public abstract double getCenterY();
}
