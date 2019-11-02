import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AbstractTile implements GameTile {
    private Vector2D position;
    private int tileType;
    private Image image = null;

    public AbstractTile() {
        this(-1, -100, -100, "unknown");
    }
    public AbstractTile(int tileType, double x, double y, String url) {
        try {
            FileInputStream inputStream = new FileInputStream(url);
            image = new Image(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Cannot load image at [" + url + "]");
        }
        position = new Vector2D(x, y);
        this.tileType = tileType;
    }

    public AbstractTile(int tileType, Vector2D position, String url) {
        this(tileType, position.x, position.y, url);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public int getTileType() {
        return tileType;
    }

    public void setTileType(int tileType) {
        this.tileType = tileType;
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
}
