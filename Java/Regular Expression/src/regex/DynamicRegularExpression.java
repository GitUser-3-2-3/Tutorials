package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicRegularExpression {

   public static void checkStringAgainstRe(String re, String str) {
      Pattern pattern = Pattern.compile(re);
      Matcher matcher = pattern.matcher(str);
      System.out.println(matcher.find());
   }

   public static void main(String[] args) {
      System.out.println();

      while (true) {
         System.out.println("Enter regular expression: ");
         Scanner sc = new Scanner(System.in);
         String re = sc.nextLine();

         System.out.println("Enter your Char: ");
         String str = sc.nextLine();
         checkStringAgainstRe(re, str);

         System.out.println("Continue?");
         String choice = sc.nextLine();
         if (choice.equalsIgnoreCase("n")) {
            break;
         }
      }
   }
}
