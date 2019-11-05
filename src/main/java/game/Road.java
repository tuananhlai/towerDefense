package game;

public class Road extends AbstractTile {
    public Road(double x, double y) {
        this(Settings.ROAD, x, y, "assets/Sprites/sand_tile.png");
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
}
