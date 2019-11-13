package game.test;

public class SetTest {
    public SetTest(String s) {
        System.out.println("hello");
    }
    public SetTest(Double d) {
        System.out.println("hi");
    }
    public static void main(String[] args) {
        SetTest st = new SetTest((String) null);
    }
}
