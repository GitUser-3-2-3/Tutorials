package com.parth.streams;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        secondMethod();
    }

    public static void firstMethod() {
        List<String> colors = List.of("Red", "Blue", "Green");

        // First method
        for (String color : colors) {
            System.out.println(color);
        }

        // Second method, using Iterable
        // colors.forEach(color -> System.out.println(color));
        colors.forEach(System.out::println);
    }

    public static void secondMethod() {
        List<String> strings = List.of("I", "am", "a", "List", "of", "Strings");

        /*
        Stream<String> stream = strings.stream();
        Stream<String> limit = stream.limit(4);

        long count = limit.count();
        System.out.println("count = " + count);

        List<String> result = limit.toList();
        System.out.println(result);
       */

        List<String> list = strings.stream()
            .limit(4)
            .toList();
        System.out.println(list);

        List<String> list1 = strings.stream()
            .sorted()
            .limit(4)
            .toList();
        System.out.println(list1);

        List<String> list2 = strings.stream()
            // .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
            .sorted(String::compareToIgnoreCase)
            .limit(4)
            .toList();
        System.out.println(list2);

        List<String> list3 = strings.stream()
            // .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
            .sorted(String::compareToIgnoreCase)
            .skip(2)
            .limit(4)
            .toList();
        System.out.println(list3);
    }
}
