import javafx.scene.canvas.GraphicsContext;

/**
 * Interface of Tower, Enemy, Target, Spawner
 */
public interface GameEntity {
    void run();
    void render(GraphicsContext gc);
    void update();
    void reset();
}
