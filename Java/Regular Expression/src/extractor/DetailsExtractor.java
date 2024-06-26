package extractor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailsExtractor {
   public static void main(String[] args) {
      System.out.println();
      Scanner sc = new Scanner(System.in);

      String regexName = "\\s*(.*)";
      String regexPassword = "\\s*(.*)";
      String regexEmail = "\\s*([a-zA-Z0-9._]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,})";
      String regexAddress = "\\s*(.*)";

      System.out.println("Enter Name: ");
      String inputNam = sc.nextLine();

      System.out.println("Enter Password: ");
      String inputPas = sc.nextLine();

      System.out.println("Enter Email: ");
      String inputEm = sc.nextLine();

      System.out.println("Enter Address: ");
      String inputAd = sc.nextLine();

      String name = extractFields(inputNam, regexName);
      String password = extractFields(inputPas, regexPassword);
      String email = extractFields(inputEm, regexEmail);
      String address = extractFields(inputAd, regexAddress);

      System.out.println("Extracted Fields");
      System.out.println("Name: " + name);
      System.out.println("Password: " + password);
      System.out.println("Email: " + email);
      System.out.println("Address: " + address);

      sc.close();
   }

   private static String extractFields(String input, String regex) {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(input);
      if (matcher.find()) {
         return matcher.group().trim();
      }
      return "";
   }
}
