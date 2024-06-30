package com.parth.generics;

public class Printer<T> {

   T thingsToPrint;

   public Printer() {
   }

   public Printer(T thingsToPrint) {
      this.thingsToPrint = thingsToPrint;
   }

   public T value(T thingsToPrint) {
      this.thingsToPrint = thingsToPrint;
      return thingsToPrint;
   }

   public void print() {
      System.out.println(thingsToPrint);
   }
}