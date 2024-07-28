package com.parth;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameSaverTest {
    public static void main(String[] args) {
        System.out.println();

        GameCharacter one = new GameCharacter(50, "Human",
            new String[]{"bow", "sword", "dust"});

        GameCharacter two = new GameCharacter(200, "Troll",
            new String[]{"big hands", "big axe"});

        GameCharacter three = new GameCharacter(120, "Magician",
            new String[]{"spells", "invisibility"});

        Path filePath = Paths.get("Game.ser");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(filePath)
            );
            oos.writeObject(one);
            oos.writeObject(two);
            oos.writeObject(three);
            oos.close();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(
                Files.newInputStream(filePath)
            );

            GameCharacter oneRestore = (GameCharacter) ois.readObject();
            GameCharacter twoRestore = (GameCharacter) ois.readObject();
            GameCharacter threeRestore = (GameCharacter) ois.readObject();

            System.out.println("One's type: " + oneRestore.getType());
            System.out.println("Two's type: " + twoRestore.getType());
            System.out.println("Three's type: " + threeRestore.getType());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception: " + e);
        }
    }
}
