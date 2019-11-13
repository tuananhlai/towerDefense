package game;

public class Spawner extends AbstractTile {
    private double spawnRate = 0.5;
    private int spawnNumber;
    private int betweenSpawnCount = 0;
    @Override
    public void run() {
        betweenSpawnCount++;
        if (betweenSpawnCount > 60/spawnRate) {
            new NormalEnemy();
            betweenSpawnCount = 0;
        }
    }
}
