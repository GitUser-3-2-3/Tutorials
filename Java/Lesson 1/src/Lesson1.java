public class Lesson1 {
    public static void main(String[] args) {
        System.out.println();

        First.first();
        Second.second();
        Third.third();
    }
}

class First {
    static void first() {
        System.out.println();

        int a = 5, b = 0;

        try {
            System.out.println(a / b);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
}

class Second {
    public static void second() {
        //program to print the exception information using toString() method

        int a = 5;
        int b = 0;
        try {
            System.out.println(a / b);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }
}


class Third {
//program to print the exception information using getMessage() method

    public static void third() {
        int a = 5;
        int b = 0;
        try {
            System.out.println(a / b);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
