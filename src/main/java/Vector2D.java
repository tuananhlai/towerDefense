public class Vector2D {
    public double x;
    public double y;

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
//        Vector2D newVec = new Vector2D(this.x, this.y);
//        return newVec;
        return new Vector2D(this.x, this.y);
    }

    public double getLength() {
        //return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        return Math.hypot(this.x, this.y);
    }

    public void setLength(double length) {
        double ratio = 0; //initialize variable
        if (getLength() != 0 && length >= 0)
        {
            ratio = length/getLength();
            this.x *= ratio;
            this.y *= ratio;
        }

    }

    public double distanceTo(Vector2D other) {
        //return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        return Math.hypot(this.x - other.x, this.y - other.y); //calculate hypotenuse
    }

    public double getAngle() {
//        if (this.x != 0) {
//            return Math.atan(this.y/this.x);
//        }
//        else return Math.PI/2;
        return Math.atan2(this.y, this.x); //more efficient
    }
    public void setAngle(double angle) {
        this.x = getLength()*Math.cos(angle);
        this.y = getLength()*Math.sin(angle);
    }
//    public static void main(String[] args) {
//        Vector2D newvec = new Vector2D(200, 100);
//        System.out.println(Math.toDegrees(newvec.getAngle()));
//    }
}
