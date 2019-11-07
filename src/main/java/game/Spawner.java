package game;

public class Spawner extends AbstractTile {
    private int betweenSpawnCount = 0;
    @Override
    public void run() {
        betweenSpawnCount++;
        if (betweenSpawnCount > 100) {
            new NormalEnemy();
            betweenSpawnCount = 0;
        }
    }
}
