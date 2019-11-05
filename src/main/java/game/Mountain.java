package game;

public class Mountain extends AbstractTile {
    public Mountain(double x, double y) {
        this(Settings.MOUNTAIN, x, y, "assets/Sprites/grass_tile_1.png");
    }

    public Mountain(int tileType, double x, double y, String url) {
        super(tileType, x, y, url);
    }

    public Mountain(int tileType, Vector2D position, String url) {
        super(tileType, position, url);
    }

    @Override
    public void run() {

    }
}

