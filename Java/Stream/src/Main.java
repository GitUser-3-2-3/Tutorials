import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        Stream<Integer> stream = Stream.of(2, 3, 4, 52, 4, 5, 2, 4, 2, 34, 23, 2, 3, 23);

        List<Integer> filteredList = stream
            .map(n -> n * n).distinct()
            .toList();
        System.out.println(filteredList);



        List<Integer> list = filteredList.stream()
            .map(n -> n / 2)
            .distinct()
            .sorted((a, b) -> (b - a))
            .limit(4)
            .skip(1)
            .toList();
        System.out.println(list);
    }

    
}
