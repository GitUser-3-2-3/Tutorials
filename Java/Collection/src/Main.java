import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class Main {
    public static void main(String[] args) {
        System.out.println();

        List<Integer> linklist1 = new LinkedList<>();
        linklist1.add(2);
        linklist1.add(4);
        linklist1.add(1);
        linklist1.add(9);
        System.out.println(
            Arrays.toString(linklist1.toArray())
        );

        ListIterator<Integer> iterator = linklist1.listIterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.previous());
        System.out.println(iterator.next());
    }
}