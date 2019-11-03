public class TankerEnemy extends AbstractEnemy {

    public TankerEnemy(double x, double y, String imageURL) {
        super(x, y, imageURL);
    }

    public TankerEnemy(double x, double y, String url, int hp, double velocityX, double velocityY) {
        super(x, y, url, hp, velocityX, velocityY);
    }
    public TankerEnemy(){
        super(100, 100, "assets/enemy/1_ORK/RUN/RUN_000.png");
        this.setHp(Settings.TANKER_HP);
        this.setVelocity(7, 0);
        this.setActive(true);
    }
}
