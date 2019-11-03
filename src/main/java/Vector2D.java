public class Vector2D {
    public double x;
    public double y;
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x+=x;
        this.y+=y;
    }

    public void set(double velocityX, double velocityY) {
        this.x = velocityX;
        this.y = velocityY;
    }
}
