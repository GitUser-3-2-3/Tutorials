import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

   private static final String url = "jdbc:mysql://localhost:3306/mydb";
   private static final String name = "root";
   private static final String password = "Q-w-e-r-t-y1,0*";

   public static void main(String[] args) {
      System.out.println();

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Exception: " + e);
      }

      deleteValues();
   }

   static void insertValues() {
      try {
         Connection connection = DriverManager.getConnection(url, name, password);
         Statement statement = connection.createStatement();
         String query = String.format(
              "INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)", "Rahul", 44, 77.4
         );
         int rowsAffected = statement.executeUpdate(query);
         if (rowsAffected > 0) {
            System.out.println("Data Inserted successfully");
         } else {
            System.out.println("Data not Inserted!");
         }
      } catch (SQLException e) {
         System.out.println("Exception: " + e);
      }
   }

   static void updateValues() {
      try {
         Connection connection = DriverManager.getConnection(url, name, password);
         Statement statement = connection.createStatement();
         String query = String.format(
              "UPDATE students SET marks = %f WHERE id = %o", 94.44, 1
         );
         int rowsAffected = statement.executeUpdate(query);
         if (rowsAffected > 0) {
            System.out.println("Data Updated successfully");
         } else {
            System.out.println("Data not Updated!");
         }
      } catch (SQLException e) {
         System.out.println("Exception: " + e);
      }
   }

   static void deleteValues() {
      try {
         Connection connection = DriverManager.getConnection(url, name, password);
         Statement statement = connection.createStatement();
         String query = String.format(
              "DELETE FROM students WHERE id = %o", 1
         );
         int rowsAffected = statement.executeUpdate(query);
         if (rowsAffected > 0) {
            System.out.println("Data Deleted successfully");
         } else {
            System.out.println("Data not Deleted!");
         }
      } catch (SQLException e) {
         System.out.println("Exception: " + e);
      }
   }
}
