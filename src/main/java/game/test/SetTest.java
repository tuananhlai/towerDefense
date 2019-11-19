package game.test;

import game.Vector2D;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<Vector2D> set = new HashSet<>();
        set.add(new Vector2D(1, 2));
        set.add(new Vector2D(1, 2));
        set.add(new Vector2D(1, 2));
        set.add(new Vector2D(2, 3));
        set.add(new Vector2D(3, 1));
        set.remove(new Vector2D(1, 2));
        for ( Vector2D v : set) {
            System.out.println(v);
        }
    }
}
