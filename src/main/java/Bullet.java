import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends AbstractEntity {
    private double x, y; //toa do dau tien vien dan
    private double current_pos_x, current_pos_y; //toa do hien tai cua vien dan
    private double target_x, target_y;
    @Override
    public void run() {

    }
    public Bullet(double x, double y, double target_x, double target_y){
        this.x = x;
        this.y = y;
        this.target_x = target_x;
        this.target_y = target_y;
        this.image = new Image("assets/tower/49.png");
        width = 10;
        height = 10;
    }
    private void caculate_current_pos(){

    }
    public void render(GraphicsContext gc){
        gc.drawImage(image, current_pos_x, current_pos_y, width, height);
    }
}
