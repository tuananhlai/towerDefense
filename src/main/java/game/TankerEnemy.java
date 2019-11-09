package game;

/**
 * Normal Enemy with basic attributes.
 */
public class TankerEnemy extends AbstractEnemy {
    public TankerEnemy() {
        this(0, 2 * 70);
    }
    public TankerEnemy(double x, double y) {
        super(x, y, "assets/enemies/towerDefense_tile269.png");
        this.setHp(Settings.TANKER_HP);
        this.setVelocity(1, 0);
        this.setDefense(3);
    }
    // TODO: Every class use only 1 image, so there is no need to input url every time
    public TankerEnemy(double x, double y, String url, int hp, double velocityX, double velocityY) {
        super(x, y, url, hp, velocityX, velocityY);
    }

    //    @Override
//    public void subtractHp() {
//        hp -= 1;
//        if(hp < 0){
//            deactivate();
//        }
//    }
}
