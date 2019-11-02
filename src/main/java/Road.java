import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;

public class Road extends AbstractTile {
    public Road() {
        super();
    }
    public Road(int tileType, double x, double y, String url) {
        super(tileType, x, y, url);
    }

    public Road(int tileType, Vector2D position, String url) {
        super(tileType, position, url);
    }


    @Override
    public void run() {

    }

    @Override
    public void update() {

    }

    @Override
    public void reset() {

    }
}
