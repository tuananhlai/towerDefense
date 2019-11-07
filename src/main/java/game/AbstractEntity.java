package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractEntity {
    protected Vector2D position;
    protected Image image = null;
    protected boolean active;
    protected Circle circle = new Circle();
    protected Rectangle rect = new Rectangle();
    protected int rowIndex; // i
    protected int colIndex; // j

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
        // TODO: Avoid loading image every time the program initialize a new object
        this.image = loadImage(url);
        position = new Vector2D(x, y);
        active = true;
        GameField.gameEntities.add(this);
        System.out.println(this.getClass().getName() + " " + GameField.gameEntities.size());
    }
    //add bullet to list
    public void addBullet(CuongBullet e){
        GameField.gameEntities.add(e);
    }
    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.x, position.y, image.getWidth(), image.getHeight());
    }

    public abstract void run();

//    public static <E> E findIntersects(Class <E> cls, AbstractEntity source) {
//        for (int i = 0; i < GameField.gameEntities.size(); i++) {
//            AbstractEntity entity = GameField.gameEntities.get(i);
//            if (entity.active && cls.isAssignableFrom(entity.getClass()) && entity.hitBox != null && entity.hitBox.intersect(source.hitBox)) {
//                return (E) entity;
//            }
//        }
//        return null;
//    }

    public void reset() {
        active = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image loadImage(String url) {
        Image loadedImage = null;
        try {
            FileInputStream inputStream = new FileInputStream(url);
            loadedImage = new Image(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Cannot load image at [" + url + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedImage;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void deactivate() {
        active = false;
        GameField.gameEntities.remove(this);
    }
}
