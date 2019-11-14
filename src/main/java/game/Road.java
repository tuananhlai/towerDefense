package game;

public class Road extends AbstractTile {

    public Road(double x, double y) {
        super(Settings.ROAD, x, y, "assets/Sprites/sand_tile.png");
        int rowIndex = (int) y / Settings.TILE_HEIGHT;
        int colIndex = (int) x / Settings.TILE_WIDTH;
        GameField.unusablePositions.add(new Vector2D(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT));
    }

    @Override
    public void run() {

    }
}
