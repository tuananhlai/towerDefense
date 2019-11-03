import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AbstractEntity {
    protected Vector2D position;
    protected Image image = null;
    protected boolean active;

    public AbstractEntity() {
        this(-100, -100, "unknown");
    }

    public AbstractEntity(double x, double y) {
        this(x, y, "unknown");
    }

    public AbstractEntity(String url) {
        this(-100, -100, url);
    }

    public AbstractEntity(Vector2D position, String url) {
        this(position.x, position.y, url);
    }

    /**
     * Load image from input url, print error if file not found.
     * Initialize position, set state to active, and add newly created objects to list objects.
     * @param x input x-position
     * @param y input y-position
     * @param url object's image url
     */
    public AbstractEntity(double x, double y, String url) {
        try {
            FileInputStream inputStream = new FileInputStream(url);
            image = new Image(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Cannot load image at [" + url + "]");
        }
        position = new Vector2D(x, y);
        active = true;
        GameField.gameEntities.add(this);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.x, position.y, image.getWidth(), image.getHeight());
    }

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

    public void setImage(String url) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(url);
        this.image = new Image(inputStream);
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


}
