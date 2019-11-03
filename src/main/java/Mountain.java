public class Mountain extends AbstractTile {
    public Mountain(double x, double y) {
        this(-1, x, y, "assets/Sprites/mountain_1.png");
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
    @Override
    public void reset() {

    }
}