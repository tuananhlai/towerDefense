public class NormalEnemy extends AbstractEnemy {
    public NormalEnemy() {
        super(100, 100, "assets/enemy/1_ORK/RUN/RUN_000.png");
        this.setHp(Settings.NORMAL_HP);
        this.setVelocity(10, 0);
        this.setActive(true);
    }
    public NormalEnemy(double x, double y, String url, int hp, double velocityX, double velocityY) {
        super(x, y, url, hp, velocityX, velocityY);
    }
}
