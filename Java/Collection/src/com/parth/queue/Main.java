package com.parth.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        List<StudentMarks> list = new ArrayList<>();
        StudentMarks st1 = new StudentMarks.Builder(22, 54).build();
        StudentMarks st2 = new StudentMarks.Builder(53, 13).build();
        StudentMarks st3 = new StudentMarks.Builder(83, 89).build();

        list.add(st1);
        list.add(st2);
        list.add(st3);

        PriorityQueue<StudentMarks> pq = new PriorityQueue<>((s1, s2) -> {
            System.out.println("Comparator's compare() method call");
            return s2.getPhysics() - s1.getPhysics();
        });
        pq.addAll(list);

        List<StudentMarks> highest = new ArrayList<>();
        int index = 0;

        while (!pq.isEmpty()) {
            if (index == 3) {
                break;
            }
            highest.add(pq.poll());
            index++;
        }

        System.out.println(highest);
    }
}
