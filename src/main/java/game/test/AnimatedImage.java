package game.test;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class AnimatedImage {
    private ArrayList<Image> images;
    private boolean isOnce;

    public AnimatedImage(ArrayList<Image> images) {
        this.images = images;
    }

    private int renderCount = 0;
    public void render(GraphicsContext gc, double x, double y, double w, double h, boolean isOnce) {
        gc.drawImage(images.get(renderCount++), x, y, w, h);
    }
}
