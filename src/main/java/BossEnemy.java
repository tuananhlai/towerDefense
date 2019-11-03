public class BossEnemy extends AbstractEnemy {

    public BossEnemy(double x, double y) {
        super(x, y, "assets/enemy/1_ORK/RUN/RUN_000.png");
        this.setHp(Settings.BOSS_HP);
        this.setVelocity(5, 0);
        this.setActive(false);
    }
}
