package com.parth.generics;

import java.util.ArrayList;
import java.util.List;

public class Main {
   public static void main(String[] args) {
      System.out.println();

      List<Integer> intList = new ArrayList<>();
      intList.add(423);
      intList.add(423);
      printLists(intList);
   }

   public static void printLists(List<?> myList) {
      System.out.println(myList);
   }
}
