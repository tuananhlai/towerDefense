import java.util.LinkedList;

public class selectionSortLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(15);
        linkedList.add(2);
        linkedList.addFirst(2);
        for (int i : linkedList) {
            System.out.println(i);
        }
    }
}
