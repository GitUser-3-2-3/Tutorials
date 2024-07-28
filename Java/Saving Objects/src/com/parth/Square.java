package com.parth;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Square implements Serializable {
    private final int width;
    private final int height;

    public Square(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static void main(String[] args) {
        Square square = new Square(50, 20);

        try {
            FileOutputStream fos = new FileOutputStream("foo.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(square);
            oos.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
