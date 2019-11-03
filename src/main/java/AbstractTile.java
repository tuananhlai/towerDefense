
import javafx.scene.canvas.GraphicsContext;

public abstract class AbstractTile extends AbstractEntity {
    private int tileType;
    protected int rowIndex; // i
    protected int colIndex; // j

    public AbstractTile() {
        this(-1, -100, -100, "unknown");
    }

    public AbstractTile(int tileType, double x, double y, String url) {
        super(x, y, url);
        this.tileType = tileType;
    }

    public AbstractTile(int tileType, Vector2D position, String url) {
        this(tileType, position.x, position.y, url);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
    }

    public int getTileType() {
        return tileType;
    }

    public void setTileType(int tileType) {
        this.tileType = tileType;
    }

}
