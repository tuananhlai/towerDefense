public interface Enemy extends GameEntity {
    void takeDamage();
    void checkIntersectBullet();
    void deactivate();
}
