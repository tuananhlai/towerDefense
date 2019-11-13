package game;

public class Vector2D {
    public double x;
    public double y;
    public static int UP = -90; // the y coordinate is reversed in window.
    public static int DOWN = 90;
    public static int LEFT = 180;
    public static int RIGHT = 0;
    public Vector2D() {
        x = 0;
        y = 0;
    }
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }
    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }
    public void scale(double rate) {
        this.x *= rate;
        this.y *= rate;
    }
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void set(Vector2D other) {
        this.set(other.x, other.y);
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return Math.hypot(this.x, this.y);
    }

    public void setLength(double length) {
        double ratio = 0;
        if (getLength() != 0 && length >= 0)
        {
            ratio = length/getLength();
            this.x *= ratio;
            this.y *= ratio;
        }

    }

    public double distanceTo(Vector2D other) {
        return Math.hypot(this.x - other.x, this.y - other.y); //calculate hypotenuse
    }

    public double getAngle() {
        return Math.toDegrees(Math.atan2(this.y, this.x)); //more efficient
    }
    public void setAngle(double angle) {
        angle = Math.toRadians(angle);
        double length = getLength();
        this.x = length*Math.cos(angle);
        this.y = length*Math.sin(angle);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2D) {
            return this.getX() == ((Vector2D) obj).getX() && this.getY() == ((Vector2D) obj).getY();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = (int) x;
        result = 31 * result + (int) y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public static void main(String[] args) {
        Vector2D newvec = new Vector2D(200, 100);
        Vector2D v1 = new Vector2D(-200, 100);
        Vector2D v2 = new Vector2D(200, -100);
        Vector2D v3 = new Vector2D(-200, -100);

        System.out.println(newvec.getLength());
        newvec.setAngle(LEFT);
        System.out.println(newvec.x + " " + newvec.y);
        System.out.println(newvec.getAngle());
        System.out.println(v1.getAngle());
        System.out.println(v2.getAngle());
        System.out.println(v3.getAngle());
    }
}
