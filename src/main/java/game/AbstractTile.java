package game;

import javafx.scene.canvas.GraphicsContext;

public abstract class AbstractTile extends AbstractEntity {
    // TODO: I don't think we need this, we can always use instaceof to check tile type.
    private int tileType;

    public AbstractTile() {
        this(-1, 0, 0, null);
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

    @Override
    public double getCenterX() {
        return position.x + Settings.TILE_WIDTH * 0.5;
    }

    @Override
    public double getCenterY() {
        return position.y + Settings.TILE_HEIGHT * 0.5;
    }
}
