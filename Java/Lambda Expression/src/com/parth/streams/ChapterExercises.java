package com.parth.streams;

import java.util.function.Consumer;
import java.util.function.Function;

public class ChapterExercises {

    public void methodOne() {
        Runnable r = () -> System.out.println("hi");
        Consumer<String> c = System.out::println;
        // Consumer<String> c1 = (s1, s2) -> System.out.println(s1 + s2);
        // Runnable r2 = (String str) -> System.out.println(str);
        Function<String, Integer> f = String::length;
        // Supplier<String> stringSupplier = "Some string";
        // Consumer<String> consumer = s -> "String" + s;
        Function<String, Integer> function = String::length;
    }
}
