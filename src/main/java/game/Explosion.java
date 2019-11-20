package game;

import game.AbstractEntity;
import game.GameField;
import game.Settings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Explosion extends AbstractEntity {
    public static ArrayList<Image> ENEMY_EXPLOSION = Settings.loadImages("assets/Retina/Effects/enemy_explosion", Settings.TILE_WIDTH, Settings.TILE_HEIGHT, true, false);
    private ArrayList<Image> images;
    private boolean renderOnce;

    public Explosion(double x, double y) {
        this(x, y, ENEMY_EXPLOSION, true);
    }

    public Explosion(double x, double y, ArrayList<Image> images) {
        this(x, y, images, true);
    }

    public Explosion(double x, double y, ArrayList<Image> images, boolean renderOnce) {
        super(x, y);
        this.images = images;
        this.renderOnce = renderOnce;
    }

    private int frameCount = 0;
    private int currentIndex = 0;
    @Override
    public void render(GraphicsContext gc) {
        if (images == null || images.size() == 0) return;
        frameCount++;
        Image currentImage = images.get(currentIndex);
        if (frameCount > 3) {
            ++currentIndex;
            frameCount = 0;
        }
        gc.drawImage(currentImage, position.x, position.y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
    }

    @Override
    public void run() {
        if (images == null) return;
        if (currentIndex >= images.size()) this.deactivate();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        images = null;
        GameField.gameEntities.remove(this);
    }

    @Override
    public double getCenterX() {
        return 0;
    }

    @Override
    public double getCenterY() {
        return 0;
    }
}
