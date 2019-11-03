public class SmallerEnemy extends AbstractEnemy{
    public SmallerEnemy(double x, double y) {
        super(x, y, "assets/enemy/1_ORK/RUN/RUN_000.png");
        this.setHp(Settings.SMALLER_HP);
        this.setVelocity(13, 0);
        this.setActive(true);
    }

}
